import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinTest

plugins {
  id("org.jlleitschuh.gradle.ktlint")
  idea
}

repositories {
  mavenCentral()
  google()
  if (project.properties["project.useSnapshotRepositories"] == "true") {
    maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
  }
}

idea {
  module {
    isDownloadSources = true
    isDownloadJavadoc = true
  }
}

ktlint {
  version to "${project.properties["ktlint.version"]}"
  additionalEditorconfigFile to rootDir.resolve(".editorconfig")
}

tasks {
  withType<Test> {
    useJUnitPlatform()
  }
  register("compile") {
    dependsOn(withType(AbstractKotlinCompile::class))
    group = "build"
  }
  register("test") {
    dependsOn(withType(KotlinTest::class))
    group = "verification"
  }
}
