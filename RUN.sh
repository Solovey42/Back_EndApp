#!/bin/bash

if [[ "$OSTYPE" == "msys" ]]; then
  params="./lib/kotlinx-cli-0.2.1.jar;bin/app.jar"
else
  params="./lib/kotlinx-cli-0.2.1.jar:bin/app.jar"
fi

# shellcheck disable=SC2068
java -classpath $params com.solo.myProject.MainKt $@
