import org.jetbrains.kotlin.gradle.tasks.AbstractKotlinNativeCompile
import org.jetbrains.kotlin.gradle.tasks.CInteropProcess
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
    "desktop",
    macosX64(),
    linuxX64(),
    mingwX64(),
  )

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
    targets.filterIsInstance<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>()
      .map { it.compilations["main"].defaultSourceSet to it.compilations["test"].defaultSourceSet }
      .forEach { (main, test) ->
        main.dependsOn(nativeMain)
        test.dependsOn(nativeTest)
      }
  }
}

tasks {
  withType<KotlinCompile> {
    kotlinOptions {
      jvmTarget = project.properties["org.gradle.project.targetCompatibility"]!!.toString()
    }
  }
  withType<CInteropProcess>{
    onlyIf {
      (konanTarget.name.contains("mingw", true) && currentOS.isWindows) ||
          (konanTarget.name.contains("linux", true) && currentOS.isLinux) ||
          (konanTarget.name.contains("os", true) && currentOS.isMacOsX)
    }
  }
  withType<AbstractKotlinNativeCompile<*, *>> {
    onlyIf {
      (target.contains("mingw", true) && currentOS.isWindows) ||
          (target.contains("linux", true) && currentOS.isLinux) ||
          (target.contains("os", true) && currentOS.isMacOsX)
    }
  }
}
