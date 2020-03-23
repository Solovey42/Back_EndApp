echo off
cd/d %~dp0
cls
kotlinc ./src -include-runtime -d ./out/app.jar

