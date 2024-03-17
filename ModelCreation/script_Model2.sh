#!/bin/bash
cd ModelCreation
echo -n $1" ">test.ndx&&
ls -1 wav|grep -v empty| cut -f1 -d'.'|tr '\n' ' '>>test.ndx&&
./script_norm.sh&&
bin/TrainTarget --config cfg/TrainTarget.cfg&&
rm ./wav/*.wav ./lbl/*.lbl ./prm/*.prm
