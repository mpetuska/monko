plugins {
  kotlin("jvm") version "1.8.20-mercury-607"
  application
}

repositories {
  mavenCentral()
  google()
  maven("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/dev")
}

description = "Local consumer sandbox"

application {
  mainClass.set("local.sandbox.MainKt")
}

dependencies {
  implementation("dev.petuska:monko")
  testImplementation("dev.petuska:test")
}
