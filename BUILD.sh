#!/bin/bash

mkdir -p bin
kotlinc -include-runtime -d bin/app.jar src/ -cp "\
lib/log4j-api-2.13.1.jar;\
lib/log4j-core-2.13.1.jar"
