#!/usr/bin/env bash

pacman --noconfirm -Syu
pacman --noconfirm -S --needed mingw-w64-x86_64-gcc mingw-w64-x86_64-cmake
pacman --noconfirm -S --needed mingw-w64-x86_64-extra-cmake-modules make tar
pacman --noconfirm -S --needed mingw64/mingw-w64-x86_64-cyrus-sasl

set -o allexport
sudo() {
 COMMAND="$*"
 $COMMAND
}

export CMAKE_GENERATOR="MSYS Makefiles"
"$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/.build.sh" \
  "-DENABLE_AUTOMATIC_INIT_AND_CLEANUP=OFF" \
  "-DCMAKE_OSX_ARCHITECTURES=x86_64;" \
  "-DCMAKE_BUILD_TYPE=Debug" \
  "-DCMAKE_INSTALL_PREFIX=C:\mongo-c-driver" \
  "-DCMAKE_PREFIX_PATH=C:\mongo-c-driver" \
  -DENABLE_SSL=WINDOWS \
  -DENABLE_SASL=SSPI
export PATH="C:\\mongo-c-driver\\bin:$PATH"
if [[ ! -z ${GITHUB_PATH+x} ]]; then
    echo "C:\\mongo-c-driver\\bin" >> $GITHUB_PATH
fi;