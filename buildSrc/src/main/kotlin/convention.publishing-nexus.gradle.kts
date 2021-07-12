import util.controlPublications

plugins {
  id("io.github.gradle-nexus.publish-plugin")
  id("convention.publishing")
}

nexusPublishing {
  repositories {
    sonatype {
      nexusUrl.set(uri("https://s01.oss.sonatype.org/service/local/"))
      snapshotRepositoryUrl.set(uri("https://s01.oss.sonatype.org/content/repositories/snapshots/"))
    }
  }
}

kotlin {
  controlPublications(targets)
}
