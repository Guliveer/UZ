@echo off
cls && set "params=%*" && cd /d "%~dp0" && ( if exist "%temp%\getadmin.vbs" del "%temp%\getadmin.vbs" ) && fsutil dirty query %systemdrive% 1>nul 2>nul || (  echo Set UAC = CreateObject^("Shell.Application"^) : UAC.ShellExecute "cmd.exe", "/k cd ""%~sdp0"" && ""%~s0"" %params%", "", "runas", 1 >> "%temp%\getadmin.vbs" && "%temp%\getadmin.vbs" && exit /B )

@echo on
fsutil quota track C:
fsutil quota enforcement C:

fsutil quota query C:

fsutil quota modify C: 104857600 62914560 Student1

fsutil quota query C:

fsutil quota modify C: 125829120 83886080 Student1

fsutil quota query C:

rem fsutil quota disable C:

exit /B

