@echo off
REM Instaluje uslugę HeartbeatService. Uruchom jako administrator.
set "SVC=HeartbeatService"
set "BIN=%~dp0bin\Debug\HeartbeatService.exe"

if not exist "%BIN%" (
    echo Plik %BIN% nie istnieje. Najpierw zbuduj projekt w Visual Studio (Debug lub Release).
    goto :eof
)

sc create %SVC% binPath= "%BIN%" start= demand DisplayName= "Heartbeat Demo Service (Lab04)"
if errorlevel 1 goto :eof

sc description %SVC% "Demo Windows Service z Lab04 (Zadanie_01/Podzadanie_03). Co 10s zapisuje wpis do Event Log."
sc start %SVC%

echo.
echo Sprawdz wpisy w Event Viewer -> Windows Logs -> Application, zrodlo: HeartbeatService.
