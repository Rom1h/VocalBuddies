#!/bin/bash

#script pour installer les dependance necessaire au bon fonctionnement de whisper.
sudo apt update && sudo apt install ffmpeg && 
pip install git+https://github.com/openai/whisper.git
