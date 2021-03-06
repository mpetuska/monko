plugins {
  id("com.github.jakemarsden.git-hooks")
  id("plugin.library")
  id("plugin.publishing")
  id("plugin.publishing-nexus")
  id("com.dorongold.task-tree")
}

gitHooks {
  setHooks(
    mapOf(
      "pre-commit" to "ktlintFormat",
      "pre-push" to "ktlintCheck"
    )
  )
}

gradleEnterprise {
  buildScan {
    termsOfServiceUrl = "https://gradle.com/terms-of-service"
    termsOfServiceAgree = "yes"
  }
}

kotlin {
  sourceSets {
    commonMain {
      dependencies {
        subprojects.filter { it.path.startsWith(":lib:") }.forEach {
          api(it)
        }
      }
    }
  }
}
