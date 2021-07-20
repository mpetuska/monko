import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinCompile
import org.jetbrains.kotlin.gradle.tasks.KotlinTest

plugins {
  id("org.jlleitschuh.gradle.ktlint")
  idea
}

repositories {
  mavenCentral()
  google()
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
  tasks.withType(AbstractTestTask::class).configureEach {
    testLogging {
      showExceptions = true
      exceptionFormat = TestExceptionFormat.FULL
    }
  }
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
