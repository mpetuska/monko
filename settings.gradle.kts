plugins {
  id("de.fayard.refreshVersions") version "0.10.1"
  id("com.gradle.enterprise") version "3.6.3"
}

rootProject.name = "monko"
include(":test")

val libModules = arrayOf(
  ":lib:core"
)
libModules.forEach {
  include(it)
  project(it).apply {
    name = "${rootProject.name}-$name"
  }
}
