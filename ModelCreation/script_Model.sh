#!/bin/bash
cd ModelCreation
./script_norm.sh&&
bin/TrainWorld --config cfg/TrainWorld.cfg&&
mkdir ./gmm/$1&&
mv ./gmm/wld ./gmm/wld.gmm&&
mv ./gmm/wldinit ./gmm/wldinit.gmm&&
mv ./gmm/wld.gmm ./gmm/wldinit.gmm ./gmm/$1/
rm ./wav/* ./lbl/* ./prm/*
