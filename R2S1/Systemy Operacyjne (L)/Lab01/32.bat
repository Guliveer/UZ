@echo off
setlocal

echo Podaj nazwe aplikacji do uruchomienia (np. notepad.exe):
set /p appName=

rem Uruchomienie aplikacji
start "" "%appName%"
rem Czekanie na zakończenie aplikacji
timeout /t 1 > nul

rem Sprawdzenie statusu wyjścia
tasklist /fi "imagename eq %appName%" | find /i "%appName%" > nul
if errorlevel 1 (
    echo Aplikacja %appName% nie jest uruchomiona.
    exit /b 1
) else (
    echo Aplikacja %appName% jest uruchomiona. Czekam na zakończenie...
    rem Czekanie na zakończenie aplikacji
    :WaitForExit
    tasklist /fi "imagename eq %appName%" | find /i "%appName%" > nul
    if errorlevel 1 (
        echo Aplikacja %appName% zostala zamknieta.
        exit /b 0
    ) else (
        timeout /t 2 > nul
        goto WaitForExit
    )
)

endlocal
