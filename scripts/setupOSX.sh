#!/usr/bin/env bash

sudo -v
xcode-select --install
brew install cmake gnu-tar wget

bash "$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/.build.sh"
