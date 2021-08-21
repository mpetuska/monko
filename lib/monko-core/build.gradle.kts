import de.fayard.refreshVersions.core.versionFor
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
  id("plugin.library")
  id("plugin.publishing")
}

kotlin {
  targets.filterIsInstance<KotlinNativeTarget>()
    .forEach { target ->
      target.compilations["main"].apply {
        cinterops {
          create("mongoc")
        }
      }
    }
  sourceSets {
    named("jvmMain") {
      dependencies {
        api("org.mongodb:mongodb-driver-reactivestreams:_")
        api("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:_")
      }
    }
    named("jsMain") {
      dependencies {
        api(npm("mongodb", versionFor("version.npm.mongodb")))
      }
    }
  }
}
