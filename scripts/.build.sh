#!/usr/bin/env bash
VERSION=1.17.7


ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$ROOT" || exit 1

if [[ ! -f "mongo-c-driver-${VERSION}" ]]; then
  wget "https://github.com/mongodb/mongo-c-driver/releases/download/${VERSION}/mongo-c-driver-${VERSION}.tar.gz"
  tar xzf "mongo-c-driver-${VERSION}.tar.gz"
  rm -rf "mongo-c-driver-${VERSION}.tar.gz"
fi

cd "mongo-c-driver-${VERSION}" || exit 1
mkdir cmake-build
cd cmake-build || exit 1
cmake .. \
  "-DENABLE_AUTOMATIC_INIT_AND_CLEANUP=OFF" \
  "-DCMAKE_OSX_ARCHITECTURES=arm64;x86_64" \
  "-DCMAKE_BUILD_TYPE=Debug"

cmake --build .
sudo cmake --build . --target install

#rm -rf "$ROOT/mongo-c-driver-${VERSION}"
