echo off
cd/d %~dp0
cls
kotlinc ./src/Main.kt -include-runtime -d ./out/app.jar

