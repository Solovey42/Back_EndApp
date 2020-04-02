#!/bin/bash

mkdir -p bin
kotlinc  ./src -include-runtime -d bin/app.jar