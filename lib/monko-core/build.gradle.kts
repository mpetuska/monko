plugins {
  id("convention.publishing")
}

kotlin {
  targets.filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>()
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
        api("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:_")
        api("org.mongodb:mongodb-driver-reactivestreams:_")
      }
    }
    named("jsMain") {
      dependencies {
        api(npm("mongodb", "^3"))
        api(devNpm("@types/mongodb", "^3"))
      }
    }
  }
}
