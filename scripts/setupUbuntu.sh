#!/usr/bin/env bash

sudo -v
sudo apt install git cmake libssl-dev libsasl2-dev libncurses5-dev libncursesw5-dev tar wget -y

bash "$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/.build.sh"
