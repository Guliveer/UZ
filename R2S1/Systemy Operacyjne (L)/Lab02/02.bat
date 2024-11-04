@echo off
cls && set "params=%*" && cd /d "%~dp0" && ( if exist "%temp%\getadmin.vbs" del "%temp%\getadmin.vbs" ) && fsutil dirty query %systemdrive% 1>nul 2>nul || (  echo Set UAC = CreateObject^("Shell.Application"^) : UAC.ShellExecute "cmd.exe", "/k cd ""%~sdp0"" && ""%~s0"" %params%", "", "runas", 1 >> "%temp%\getadmin.vbs" && "%temp%\getadmin.vbs" && exit /B )

setlocal

@echo on

rem Tworzenie kont użytkowników
net user Student1 Haslo1 /add
net user Student2 Haslo2 /add
net user Student3 Haslo3 /add
net user Student4 Haslo4 /add
net user Student5 Haslo5 /add

rem Dodawanie użytkowników do grup
net localgroup "Użytkownicy zaawansowani" Student1 /add
net localgroup "Użytkownicy" Student2 /add
net localgroup "Goście" Student3 /add
net localgroup "Administratorzy" Student4 /add
net localgroup "Studenci" Student5 /add

echo Konta uzytkownikow zostaly utworzone i przypisane do odpowiednich grup.
@endlocal
@exit /b
