#!/usr/bin/env bash
VERSION=1.17.7

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/build"
mkdir -p "$ROOT" && cd "$ROOT" || exit 1
if [[ ! -d "mongo-c-driver-${VERSION}" ]]; then
  echo "[main] mongo-c-driver-${VERSION} not found in $ROOT; Downloading..."
  wget "https://github.com/mongodb/mongo-c-driver/releases/download/${VERSION}/mongo-c-driver-${VERSION}.tar.gz"
  tar xzf "mongo-c-driver-${VERSION}.tar.gz"
  rm -rf "mongo-c-driver-${VERSION}.tar.gz"
fi

cd "mongo-c-driver-${VERSION}" || exit 1
mkdir -p cmake-build
cd cmake-build || exit 1

echo "[main] Configuring CMAKE in: $PWD"
cmake .. \
  "-DENABLE_AUTOMATIC_INIT_AND_CLEANUP=OFF" \
  "-DCMAKE_OSX_ARCHITECTURES=x86_64;" \
  "-DCMAKE_BUILD_TYPE=Debug"

echo "[main] Building with CMAKE"
cmake --build .
echo "[main] Installing with CMAKE"
sudo cmake --build . --target install

#rm -rf "$ROOT/mongo-c-driver-${VERSION}"
