@echo off
echo Nazwa zalogowanego użytkownika: %USERNAME%
echo Litera dysku: %HOMEDRIVE%
echo Nazwa katalogu macierzystego: %HOMEPATH%
echo Informacje o procesorze:
powershell -Command "Get-WmiObject -Class Win32_Processor | Select-Object -Property Name"
pause
exit