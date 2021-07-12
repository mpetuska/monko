package util

import currentOS
import gradle.kotlin.dsl.accessors._9f6608b3dfd4004de5dd12148297261b.publishing
import org.gradle.api.Named
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.publish.maven.tasks.AbstractPublishToMaven
import org.gradle.api.publish.maven.tasks.PublishToMavenRepository
import org.gradle.api.publish.tasks.GenerateModuleMetadata
import org.gradle.api.specs.Spec
import org.gradle.internal.os.OperatingSystem
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.plugin.KotlinTarget


internal val Project.mainOS get() = OperatingSystem.forName(properties["project.mainOS"] as String)
internal val Project.isMainOS get() = currentOS == mainOS

fun Project.controlPublications(kotlinTargets: Collection<KotlinTarget>) {
  val claimedTargets = mutableSetOf<KotlinTarget>()
  fun filterTargetsByHost(host: NativeHost?): Collection<KotlinTarget> = KotlinTargetDetails.values()
    .filter { host?.let { h -> h in it.supportedBuildHosts } ?: it.supportedBuildHosts.isEmpty() }
    .map { it.presetName }
    .run { kotlinTargets.filter { it.preset?.name in this && it !in claimedTargets } }
    .also { claimedTargets.addAll(it) }

  val crossPlatformTargets = filterTargetsByHost(null)

  val windowsHostTargets = filterTargetsByHost(NativeHost.WINDOWS)
  val linuxHostTargets = filterTargetsByHost(NativeHost.LINUX)
  val osxHostTargets = filterTargetsByHost(NativeHost.OSX)
  val mainHostTargets = crossPlatformTargets + Named { "kotlinMultiplatform" }

  fun Collection<Named>.onlyPublishIf(enabled: Spec<in Task>) {
    val publications: Collection<String> = map { it.name }
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

  linuxHostTargets.onlyPublishIf { currentOS.isLinux }
  osxHostTargets.onlyPublishIf { currentOS.isMacOsX }
  windowsHostTargets.onlyPublishIf { currentOS.isWindows }
  mainHostTargets.onlyPublishIf { isMainOS }
}
