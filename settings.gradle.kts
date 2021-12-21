plugins {
  id("de.fayard.refreshVersions") version "0.30.1"
  id("com.gradle.enterprise") version "3.6.4"
}

rootProject.name = "monko"
include(":test")
include(
  ":lib:monko-core",
)
