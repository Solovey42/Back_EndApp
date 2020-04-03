#!/bin/bash

if [[ "$OSTYPE" == "msys" ]]; then
  params="./lib/log4j-api-2.13.1.jar;./lib/log4j-core-2.13.1.jar;bin/app.jar"
else
  params="./lib/log4j-api-2.13.1.jar:./lib/log4j-core-2.13.1.jar:bin/app.jar"
fi

# shellcheck disable=SC2068
java -classpath $params com.solo.myProject.MainKt $@
