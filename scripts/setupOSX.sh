#!/usr/bin/env bash

sudo -v
xcode-select --install
brew install cmake gnu-tar wget

"$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/.build.sh" \
  "-DENABLE_AUTOMATIC_INIT_AND_CLEANUP=OFF" \
  "-DCMAKE_OSX_ARCHITECTURES=x86_64;" \
  "-DCMAKE_BUILD_TYPE=Debug"
