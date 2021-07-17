import org.jetbrains.kotlin.gradle.plugin.KotlinTarget
import org.jetbrains.kotlin.konan.target.HostManager
import util.KotlinTargetDetails
import util.NativeHost

plugins {
  id("convention.library")
  id("org.jetbrains.dokka")
  `maven-publish`
  signing
}

tasks {
  register<Jar>("javadocJar") {
    dependsOn(dokkaHtml)
    archiveClassifier.set("javadoc")
    from(dokkaHtml.get().outputDirectory)
  }
  withType<Jar> {
    manifest {
      attributes += sortedMapOf(
        "Built-By" to System.getProperty("user.name"),
        "Build-Jdk" to System.getProperty("java.version"),
        "Implementation-Version" to project.version,
        "Created-By" to "${GradleVersion.current()}",
        "Created-From" to Git.headCommitHash
      )
    }
  }
  named("clean") {
    doLast {
      val groupRepo =
        file("${System.getProperty("user.home")}/.m2/repository/${project.group.toString().replace(".", "/")}")
      publishing.publications.filterIsInstance<MavenPublication>().forEach {
        groupRepo.resolve(it.artifactId).deleteRecursively()
      }
    }
  }
}

signing {
  val signingKey: String? by project
  val signingPassword: String? by project
  if (signingKey != null) {
    useInMemoryPgpKeys(signingKey, signingPassword)
    sign(publishing.publications)
  }
}

publishing {
  publications {
    val ghOwnerId: String = project.properties["gh.owner.id"]!!.toString()
    val ghOwnerName: String = project.properties["gh.owner.name"]!!.toString()
    val ghOwnerEmail: String = project.properties["gh.owner.email"]!!.toString()
    repositories {
      maven("https://maven.pkg.github.com/$ghOwnerId/${project.name}") {
        name = "GitHub"
        credentials {
          username = System.getenv("GH_USERNAME")
          password = System.getenv("GH_PASSWORD")
        }
      }
    }
    withType<MavenPublication> {
      artifact(tasks["javadocJar"])
      pom {
        name by project.name
        url by "https://github.com/$ghOwnerId/${project.name}"
        description by rootProject.description

        licenses {
          license {
            name by "The Apache License, Version 2.0"
            url by "https://www.apache.org/licenses/LICENSE-2.0.txt"
          }
        }

        developers {
          developer {
            id to ghOwnerId
            name to ghOwnerName
            email to ghOwnerEmail
          }
        }

        scm {
          connection by "scm:git:git@github.com:$ghOwnerId/${project.name}.git"
          url by "https://github.com/$ghOwnerId/${project.name}"
          tag by Git.headCommitHash
        }
      }
    }
  }
}

kotlin {
  val claimedTargets = mutableSetOf<KotlinTarget>()
  fun filterTargetsByHost(host: NativeHost?): Collection<KotlinTarget> = KotlinTargetDetails.values()
    .filter { host?.let { h -> h in it.supportedBuildHosts } ?: it.supportedBuildHosts.isEmpty() }
    .map { it.presetName }
    .run { targets.filter { it.preset?.name in this && it !in claimedTargets } }
    .also { claimedTargets.addAll(it) }

  val crossPlatformTargets = filterTargetsByHost(null)

  val windowsHostTargets = filterTargetsByHost(NativeHost.WINDOWS)
  val linuxHostTargets = filterTargetsByHost(NativeHost.LINUX)
  val osxHostTargets = filterTargetsByHost(NativeHost.OSX)
  val mainHostTargets = crossPlatformTargets + Named { "kotlinMultiplatform" }

  fun Collection<Named>.onlyPublishIf(enabled: Spec<in Task>) {
    val publications: Collection<String> = map { it.name }
    afterEvaluate {
      publishing {
        publications {
          matching { it.name in publications }.all {
            val targetPublication = this@all
            tasks.withType<AbstractPublishToMaven>()
              .matching { it.publication == targetPublication }
              .configureEach {
                onlyIf(enabled)
              }
            tasks.withType<GenerateModuleMetadata>()
              .matching { it.publication.get() == targetPublication }
              .configureEach {
                onlyIf(enabled)
              }
            tasks.withType<PublishToMavenRepository>()
              .matching { it.publication == targetPublication }
              .configureEach {
                onlyIf(enabled)
              }
          }
        }
      }
    }
  }

  logger.info("Linux host targets: $linuxHostTargets")
  logger.info("OSX host targets: $osxHostTargets")
  logger.info("Windows host targets: $windowsHostTargets")
  logger.info("Main host targets: $mainHostTargets")
  linuxHostTargets.onlyPublishIf { HostManager.hostIsLinux }
  osxHostTargets.onlyPublishIf { HostManager.hostIsMac }
  windowsHostTargets.onlyPublishIf { HostManager.hostIsMingw }
  mainHostTargets.onlyPublishIf {
    HostManager.simpleOsName().equals("${project.properties["project.mainOS"]}", true)
  }
}
