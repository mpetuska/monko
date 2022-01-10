plugins {
  id("de.fayard.refreshVersions") version "0.30.2"
  id("com.gradle.enterprise") version "3.6.4"
}

rootProject.name = "monko"
include(":test")
include(
  ":lib:monko-core",
)
