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
#export CMAKE_GENERATOR="Visual Studio 16 2019 Win64"
#export CMAKE_VS_PLATFORM_NAME="x64"
#export CMAKE_VS_PLATFORM_TOOLSET_HOST_ARCHITECTURE="x64"
#export CMAKE_C_STANDARD_REQUIRED="ON"
#export CMAKE_C_STANDARD="99"
"$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/.build.sh"
