plugins {
  id("de.fayard.refreshVersions") version "0.40.0"
  id("com.gradle.enterprise") version "3.6.4"
}

rootProject.name = "monko"
include(":test")
include(
  ":lib:monko-core",
)
