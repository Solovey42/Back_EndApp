#!/bin/bash

if [[ "$OSTYPE" == "msys" ]]; then
  params="./lib/log4j-api-2.13.1.jar;./lib/log4j-core-2.13.1.jar"
else
  params="./lib/log4j-api-2.13.1.jar:./lib/log4j-core-2.13.1.jar"
fi


mkdir -p bin
kotlinc -include-runtime -d bin/app.jar src/ -cp $params
jar uf bin/app.jar -C src com/solo/myProject/log4j2.xml
