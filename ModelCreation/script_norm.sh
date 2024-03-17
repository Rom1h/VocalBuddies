#!/bin/bash

directory="./wav/"
ls "$directory"|grep -v empty |cut -f1 -d'.'>liste/Liste.lst

script_features/feature_extract.sh liste/Liste.lst 21LFCC &&
bin/EnergyDetector --config cfg/EnergyDetector.cfg&&
bin/NormFeat --config cfg/NormFeat.cfg

