#!/usr/bin/env bash
VERSION=1.18.0

ROOT="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/build"
mkdir -p "$ROOT" && cd "$ROOT" || exit 1
if [[ ! -d "mongo-c-driver-${VERSION}" ]]; then
  rm -rf ./**
  echo "[main] mongo-c-driver-${VERSION} not found in $ROOT; Downloading..."
  curl -O -L "https://github.com/mongodb/mongo-c-driver/releases/download/${VERSION}/mongo-c-driver-${VERSION}.tar.gz"
  tar xzf "mongo-c-driver-${VERSION}.tar.gz" || exit 1
  rm -rf "mongo-c-driver-${VERSION}.tar.gz" || exit 1
fi

cd "mongo-c-driver-${VERSION}" || exit 1
mkdir -p cmake-build
cd cmake-build || exit 1

echo "[main] Configuring CMAKE in: $PWD"
cmake .. ${@} || exit 1

echo "[main] Building with CMAKE"
cmake --build . || exit 1
echo "[main] Installing with CMAKE"
sudo cmake --build . --target install || exit 1

#rm -rf "$ROOT/mongo-c-driver-${VERSION}"
