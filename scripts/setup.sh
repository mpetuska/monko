#!/usr/bin/env bash

ROOT="$(dirname "${BASH_SOURCE[0]}")"
case $(uname | tr '[:upper:]' '[:lower:]') in
  darwin*)  echo "OSX detected" && "${ROOT}"/setupOSX.sh ;;
  linux*)   echo "Linux detected" && "${ROOT}"/setupUbuntu.sh ;;
  msys*)    echo "Windows detected" && "${ROOT}"/setupWindows.sh ;;
  *)        echo "unknown OS: $OSTYPE" && exit 1 ;;
esac
