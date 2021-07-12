plugins {
  id("convention.library")
}

description = "Local test utilities"

kotlin {
  explicitApi = null
  sourceSets {
    commonMain {
      dependencies {
        api(kotlin("test"))
        api(kotlin("test-annotations-common"))
      }
    }
    named("jvmMain") {
      dependencies {
        api(kotlin("test-junit5"))
      }
    }
    named("jsMain") {
      dependencies {
        api(kotlin("test-js"))
      }
    }
  }
}
