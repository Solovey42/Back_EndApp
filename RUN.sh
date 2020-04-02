#!/bin/bash

if [[ "$OSTYPE" == "msys" ]]; then
  params="bin/app.jar"
else
  params="bin/app.jar"
fi

# shellcheck disable=SC2068
java -classpath $params com.solo.myProject.MainKt $@
