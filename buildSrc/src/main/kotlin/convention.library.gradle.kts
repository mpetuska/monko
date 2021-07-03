import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import util.nativeTargetGroup

plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
  id("convention.common")
}

kotlin {
  explicitApi()
  jvm()
  js {
    useCommonJs()
    nodejs()
  }

  nativeTargetGroup(
    "ios",
    iosArm32(),
    iosArm64(),
    iosX64(),
  )

  nativeTargetGroup(
    "watchos",
    watchosArm32(),
    watchosArm64(),
    watchosX86(),
    watchosX64(),
  )

  nativeTargetGroup(
    "tvos",
    tvosArm64(),
    tvosX64(),
  )

  linuxX64()
  macosX64()
  mingwX64()

  sourceSets {
    commonMain {
      dependencies {
        api("org.jetbrains.kotlinx:kotlinx-coroutines-core:_")

      }
    }
    commonTest {
      dependencies {
        implementation(project(":test"))
      }
    }
  }
}

tasks {
  withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = project.properties["org.gradle.project.targetCompatibility"]!!.toString()
    }
  }
}
