[![Slack chat](https://img.shields.io/badge/chat-kotlinlang-purple?logo=slack&style=flat-square)](https://kotlinlang.slack.com/team/UL1A5BA2X)
[![Dokka docs](https://img.shields.io/badge/docs-dokka-orange?style=flat-square)](http://mpetuska.github.io/monko)
[![Version maven-central](https://img.shields.io/maven-central/v/dev.petuska/monko?logo=apache-maven&style=flat-square)](https://mvnrepository.com/artifact/dev.petuska/monko/latest)

# MONKO [EXPERIMENTAL]

Kotlin multiplatform bindings for mongodb driver.

Has a baseline setup for a multiplatform library supporting all kotlin
server-side [targets](https://kotlinlang.org/docs/mpp-supported-platforms.html).

* JVM
* JS (NodeJs)
* LinuxX64
* MingwX64
* MacOSX64

The library tries to integrate with native libraries for each platform to the best of its ability, while also exposing
the native objects directly via `source` property on Monko objects.

* JVM -> [mongo-java-driver@4.3.3](https://github.com/mongodb/mongo-java-driver/tree/r4.3.3)
* JS (NodeJs) -> [node-mongodb-native@^4.0.0](https://github.com/mongodb/node-mongodb-native/tree/v4.0.0)
* LinuxX64, MingwX64, MacOSX64 -> [mongo-c-driver@1.19.1](https://github.com/mongodb/mongo-c-driver/tree/1.19.1)

# Local Setup

## Prerequisites

* Debian/Ubuntu: `./scripts/setupUbuntu.sh`
* OSX (requires [Homebrew](https://brew.sh/)): `./scripts/setupOSX.sh`
* Windows (requires [MSYS2](https://www.msys2.org/)): `./scripts/setupMingw.sh`

## Running tests

* Start mongodb: `docker run --rm -p 27017:27017 mongo`
  * Alternatively you can use `docker-compose` which also gives you access to `mongo-express` web interface to interact
    with the database directly at `http://localhost:8081`. To run it use `docker-compose up` command.
* Run via gradle: `./gradlew allTests` or alias `./gradlew test`

## Docker (On KVM)

First you need to build builder images for each platform (only needs to be done once).

* Debian/Ubuntu: `docker build -t monko-build:ubuntu --build-arg "user=$USER" -f docker/ubuntu.dockerfile .`
* OSX: `docker build -t monko-build:osx --build-arg "user=$USER" -f docker/osx.dockerfile .`

Then you can run gradle commands of the project on a chosen image as:

* Debian/Ubuntu: `docker run --network host --rm -v "$PWD:/repository" monko-build:ubuntu <gradle commands>`
* OSX: `docker run --network host --rm -v "$PWD:/repository" monko-build:osx <gradle commands>`
