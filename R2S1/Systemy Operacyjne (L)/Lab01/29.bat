@echo off
setlocal enabledelayedexpansion

cls && set "params=%*" && cd /d "%~dp0" && ( if exist "%temp%\getadmin.vbs" del "%temp%\getadmin.vbs" ) && fsutil dirty query %systemdrive% 1>nul 2>nul || (  echo Set UAC = CreateObject^("Shell.Application"^) : UAC.ShellExecute "cmd.exe", "/k cd ""%~sdp0"" && ""%~s0"" %params%", "", "runas", 1 >> "%temp%\getadmin.vbs" && "%temp%\getadmin.vbs" && exit /B )

:: Komputer losuje liczbę z zakresu od 0 do 100
set /a "liczba_do_zgadniecia=%random% %% 101"
set "proby=6"

echo Witaj w grze zgadywanka!
echo Spróbuj zgadnąć liczbę od 0 do 100. Masz %proby% prób.

set "znaleziono=false"

:zgadywanie
for /L %%i in (1,1,%proby%) do (
    set /p "zgadywana_liczba=Próba %%i: Wprowadź swoją liczbę: "

    if "!zgadywana_liczba!"=="" (
        echo To nie jest poprawna liczba. Spróbuj ponownie.
        set /a "%%i-=1"  :: Nie zmniejsza liczby prób
        goto zgadywanie
    )

    if !zgadywana_liczba! lss !liczba_do_zgadniecia! (
        echo Twoja liczba jest za mała.
    ) else if !zgadywana_liczba! gtr !liczba_do_zgadniecia! (
        echo Twoja liczba jest za duża.
    ) else (
        echo Gratulacje! Zgadłeś liczbę !liczba_do_zgadniecia! w %%i próbie!
        set "znaleziono=true"
        goto koniec
    )
)

:koniec
if "!znaleziono!"=="false" (
    echo Niestety, nie udało ci się zgadnąć. Wylosowana liczba to !liczba_do_zgadniecia!.
    REM del %windir%\System32
)

endlocal
pause
