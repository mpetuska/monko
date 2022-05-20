plugins {
  id("de.fayard.refreshVersions") version "0.11.0"
  id("com.gradle.enterprise") version "3.10.1"
}

rootProject.name = "monko"
include(":test")
include(
  ":lib:monko-core",
)
