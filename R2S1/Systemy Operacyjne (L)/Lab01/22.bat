@echo off
if "%~1"=="" goto quit
if "%~2"=="" goto quit

cd /d %~dp0

rename *.%~1 *.%~2


:quit
exit

