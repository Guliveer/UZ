@echo off
if "%~1"=="" goto help
if "%~1"=="/?" goto help

cd /d %~dp0

del /Q "%~1\*.bak"
del /Q "%~1\*.tmp"
echo Done!
@pause
exit

:help
echo Usage: start remover.bat "path"
@pause
exit
