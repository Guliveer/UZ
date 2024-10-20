@echo off
if "%~1"=="" goto help
if "%~1"=="/?" goto help

cd /d %~dp0

:loop
if "%~1"=="" goto done
del /Q "%~1"
shift
goto loop

:done
echo Done!
@pause
exit

:help
echo Usage: start remover.bat "path"
@pause
exit
