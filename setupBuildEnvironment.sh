#!/usr/bin/env bash

sudo apt install cmake libssl-dev libsasl2-dev libncurses5-dev libncursesw5-dev python -y

git clone https://github.com/mongodb/mongo-c-driver.git
cd mongo-c-driver
git checkout 1.17.6  # To build a particular release
python build/calc_release_version.py > VERSION_CURRENT
mkdir cmake-build
cd cmake-build
cmake -DENABLE_AUTOMATIC_INIT_AND_CLEANUP=OFF ..
sudo cmake --build . --target install
cd ../..
rm -rf mongo-c-driver