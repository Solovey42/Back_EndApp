#!/bin/bash

if [[ "$OSTYPE" == "msys" ]]; then
  params="./lib/log4j-api-2.13.1.jar;./lib/log4j-core-2.13.1.jar;./lib/h2-1.4.200.jar;./lib/flyway-core-6.3.2.jar"
else
  params="./lib/log4j-api-2.13.1.jar:./lib/log4j-core-2.13.1.jar:./lib/h2-1.4.200.jar:./lib/flyway-core-6.3.2.jar"
fi


mkdir -p bin
kotlinc -include-runtime -d bin/app.jar src/ -cp $params
jar uf bin/app.jar -C src log4j2.xml

