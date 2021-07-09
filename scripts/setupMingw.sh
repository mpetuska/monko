#!/usr/bin/env bash


pacman --noconfirm -Syu
pacman --noconfirm -S mingw-w64-x86_64-gcc mingw-w64-x86_64-cmake
pacman --noconfirm -S mingw-w64-x86_64-extra-cmake-modules make tar
pacman --noconfirm -S mingw64/mingw-w64-x86_64-cyrus-sasl

sudo() {
 COMMAND="$*"
 $COMMAND
}

bash "$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/.build.sh"
