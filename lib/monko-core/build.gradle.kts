import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
  id("convention.publishing")
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
        api(npm("mongodb", "^4.0.0"))
      }
    }
  }
}
