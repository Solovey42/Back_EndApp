#!/bin/bash

if [ -z "$URL" ]; then
    export URL=jdbc:h2:file:./db/aaa
fi
if [ -z "$LOGIN" ]; then
    export LOGIN=Solo
fi
if [ -z "$PASS" ]; then
    export PASS=1234
fi

if [[ "$OSTYPE" == "msys" ]]; then
  params="./lib/log4j-api-2.13.1.jar;./lib/log4j-core-2.13.1.jar;./lib/h2-1.4.200.jar;./lib/flyway-core-6.3.2.jar;./bin/app.jar"
else
  params="./lib/log4j-api-2.13.1.jar:./lib/log4j-core-2.13.1.jar:./lib/h2-1.4.200.jar:./lib/flyway-core-6.3.2.jar:./bin/app.jar"
fi

# shellcheck disable=SC2068
java -classpath $params com.solo.myProject.MainKt $@
