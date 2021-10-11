import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinNativeCompile
import org.jetbrains.kotlin.gradle.tasks.CInteropProcess
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.konan.target.HostManager
import util.buildHost

plugins {
  kotlin("multiplatform")
  kotlin("plugin.serialization")
  id("plugin.common")
  id("dev.petuska.klip")
}

kotlin {
  explicitApi()
  jvm()
  js {
    useCommonJs()
    nodejs()
  }
  macosX64()
  macosArm64()
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
        implementation(kotlin("test-annotations-common"))
        implementation("dev.petuska:klip:_")
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
  project.properties["org.gradle.project.targetCompatibility"]?.toString()?.let {
    withType<KotlinCompile> {
      kotlinOptions {
        jvmTarget = it
      }
    }
  }
  withType<CInteropProcess> {
    onlyIf {
      konanTarget.buildHost == HostManager.host.family
    }
  }
  withType<AbstractKotlinNativeCompile<*, *>> {
    onlyIf {
      compilation.konanTarget.buildHost == HostManager.host.family
    }
  }
}
