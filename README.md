[![Dokka docs](https://img.shields.io/badge/docs-dokka-orange?style=flat-square)](http://mpetuska.github.io/monko)
[![Version maven-central](https://img.shields.io/maven-central/v/dev.petuska/monko?logo=apache-maven&style=flat-square)](https://mvnrepository.com/artifact/dev.petuska/monko/latest)

# MONKO

Kotlin multiplatform bindongs for mongodb driver.

Has a baseline setup for a multiplatform library supporting all
kotlin [targets](https://kotlinlang.org/docs/mpp-supported-platforms.html)
except android (any help in getting that setup welcome) & deprecated wasm32.

### Kotlin Targets

The template comes packed with all kotlin targets preconfigured, however if you want to remove some of them or tweak the
config, you only need to make changes as needed in `/buildSrc/src/main/kotlin/convention.library.gradle.kts`. Removing
targets from this file will not break any publications as they're configured on top of pre-registered targets.

### GitHub Actions

The template also comes with GH actions to check builds on PRs and publish artefacts when creating a GH release. By
default, it'll publish to GH packages and Maven Central. However to fully unlock Maven Central publishing, you'll need
to add these secrets to your GH repository. If you want to quickly disable Maven Central publishing, you can toggle it
at `.github/workflows/release.yml#L80`

* `SIGNING_KEY` - GPG signing key
* `SIGNING_KEY_ID` - GPG signing key ID
* `SIGNING_PASSWORD` - GPG signing key password (if set)
* `SONATYPE_PASSWORD` - Sonatype PAT username
* `SONATYPE_USERNAME` - Sonatype PAT password

## Known Issues

* [KT-46957](https://youtrack.jetbrains.com/issue/KT-46957) - Commonizer breaks for linuxMips32. This is fixed and
  scheduled to be released in 1.5.30-M1. However, to make the setup work now, the template is currently using dev build
  from kotlin snapshot repositories. If you'd like to use a stable kotlin version instead, remove linuxMips32 from linux
  native target group in `/buildSrc/src/main/kotlin/convention.library.gradle.kts`.

# Local Setup

## Prerequisites

* Debian/Ubuntu: `./scripts/setupUbuntu.sh`
* OSX (requires [Homebrew](https://brew.sh/)): `./scripts/setupOSX.sh`
* Windows (requires [MSYS2](https://www.msys2.org/)): `./scripts/setupMingw.sh`

## Running tests

* Start mongodb: `docker run --rm -p 27017:27017 mongo`
* Run via gradle: `./gradlew allTests`
