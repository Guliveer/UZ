@echo off
IF "%1" == "" goto help
net user %1
exit /b
:help
echo usage: net user username
exit /b