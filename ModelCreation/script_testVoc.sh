#!/bin/bash
if [ $# != "1" ]; then
echo "user missing"
exit 1
fi

user=$1
cd ModelCreation&&
./script_norm.sh&&
#ls "./wav/.wav"|cut -f1 -d'.'|sed 's/$/ wld/'>liste/TestListe.ndx&&
bin/ComputeTest --config cfg/ComputeTest.cfg &&
rm ./lbl/*.lbl ./wav/*.wav ./prm/*.prm

