#!/usr/bin/env bash


pacman --noconfirm -Syu
pacman --noconfirm -S mingw-w64-x86_64-gcc mingw-w64-x86_64-cmake
pacman --noconfirm -S mingw-w64-x86_64-extra-cmake-modules make tar
pacman --noconfirm -S mingw64/mingw-w64-x86_64-cyrus-sasl

set -o allexport
sudo() {
 COMMAND="$*"
 $COMMAND
}

export EXTRA_CMAKE_CONFIG="-G 'MSYS Makefiles'"
"$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/.build.sh"
