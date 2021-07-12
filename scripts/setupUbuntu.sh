#!/usr/bin/env bash

sudo -v
sudo apt install git cmake libssl-dev libsasl2-dev libncurses5-dev libncursesw5-dev libtinfo5 tar wget -y
sudo ln -s /usr/lib/libncurses.so.5 /usr/lib/libtinfo.so.5

"$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)/.build.sh"

sudo /sbin/ldconfig -v
