@echo off
setlocal

rem Usuwanie kont użytkowników
net user Student1 /delete
net user Student2 /delete
net user Student3 /delete
net user Student4 /delete
net user Student5 /delete

rem Usuwanie grup
net localgroup "Użytkownicy zaawansowani" /delete
net localgroup "Użytkownicy" /delete
net localgroup "Goście" /delete
net localgroup "Administratorzy" /delete
net localgroup "Studenci" /delete

echo Konta uzytkownikow i grupy zostaly usuniete.
endlocal
exit /b
