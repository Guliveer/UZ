cls && set "params=%*" && cd /d "%~dp0" && ( if exist "%temp%\getadmin.vbs" del "%temp%\getadmin.vbs" ) && fsutil dirty query %systemdrive% 1>nul 2>nul || (  echo Set UAC = CreateObject^("Shell.Application"^) : UAC.ShellExecute "cmd.exe", "/k cd ""%~sdp0"" && ""%~s0"" %params%", "", "runas", 1 >> "%temp%\getadmin.vbs" && "%temp%\getadmin.vbs" && exit /B )

@echo off
setlocal

rem Sprawdzenie, czy podano odpowiednią liczbę argumentów
if "%~3"=="" (
    echo Uzycie: %0 ^<nazwa_uzytkownika^> ^<sciezka_do_zasobu^> ^<uprawnienia^>
    echo Przyklad: %0 janek "C:\path\to\file.txt" Full
    exit /b 1
)

set "UZYTKOWNIK=%~1"
set "ZASOB=%~2"
set "UPRAWNIENIA=%~3"

rem Sprawdzenie, czy zasób istnieje
if not exist "%ZASOB%" (
    echo Błąd: Zasob '%ZASOB%' nie istnieje.
    exit /b 2
)

rem Nadanie uprawnień
icacls "%ZASOB%" /grant "%UZYTKOWNIK%":(%UPRAWNIENIA%)

rem Informacja o zakończeniu
echo Nadano uprawnienia '%UPRAWNIENIA%' dla uzytkownika '%UZYTKOWNIK%' do zasobu '%ZASOB%'.

endlocal
exit /b 0
