plugins {
  id("de.fayard.refreshVersions") version "0.23.0"
  id("com.gradle.enterprise") version "3.7"
}

rootProject.name = "monko"
include(":test")
include(
  ":lib:monko-core",
)
