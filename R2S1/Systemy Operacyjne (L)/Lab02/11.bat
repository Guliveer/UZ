@echo off
cls && set "params=%*" && cd /d "%~dp0" && ( if exist "%temp%\getadmin.vbs" del "%temp%\getadmin.vbs" ) && fsutil dirty query %systemdrive% 1>nul 2>nul || (  echo Set UAC = CreateObject^("Shell.Application"^) : UAC.ShellExecute "cmd.exe", "/k cd ""%~sdp0"" && ""%~s0"" %params%", "", "runas", 1 >> "%temp%\getadmin.vbs" && "%temp%\getadmin.vbs" && exit /B )

if "%1" == "" goto help
net user %1 /add
net user %1 /time:Pn-Pt,08:00-15:00
REM Idk dlaczego po polsku
exit /B
:help
echo usage: net user username
exit /b