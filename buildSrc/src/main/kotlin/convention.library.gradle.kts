import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinNativeCompile
import org.jetbrains.kotlin.gradle.tasks.CInteropProcess
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.konan.target.HostManager

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
  macosX64()
  linuxX64()
  mingwX64()

  sourceSets {
    val commonMain by getting {
      dependencies {
        api("org.jetbrains.kotlinx:kotlinx-coroutines-core:_")
      }
    }
    val commonTest by getting {
      dependencies {
        implementation(project(":test"))
      }
    }
    val nativeMain by creating {
      dependsOn(commonMain)
    }
    val nativeTest by creating {
      dependsOn(commonTest)
    }
    targets.withType<KotlinNativeTarget> {
      compilations["main"].defaultSourceSet.dependsOn(nativeMain)
      compilations["test"].defaultSourceSet.dependsOn(nativeTest)
    }
  }
}

tasks {
  withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = project.properties["org.gradle.project.targetCompatibility"]!!.toString()
    }
  }
  withType<CInteropProcess> {
    onlyIf {
      System.getenv("CI") != "true" || konanTarget == HostManager.host
    }
  }
  withType<AbstractKotlinNativeCompile<*, *>> {
    onlyIf {
      System.getenv("CI") != "true" || compilation.konanTarget == HostManager.host
    }
  }
}
