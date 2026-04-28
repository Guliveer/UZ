@echo off
REM Zatrzymuje i usuwa uslugę HeartbeatService. Uruchom jako administrator.
set "SVC=HeartbeatService"
sc stop %SVC%
sc delete %SVC%
