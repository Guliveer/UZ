cls && set "params=%*" && cd /d "%~dp0" && ( if exist "%temp%\getadmin.vbs" del "%temp%\getadmin.vbs" ) && fsutil dirty query %systemdrive% 1>nul 2>nul || (  echo Set UAC = CreateObject^("Shell.Application"^) : UAC.ShellExecute "cmd.exe", "/k cd ""%~sdp0"" && ""%~s0"" %params%", "", "runas", 1 >> "%temp%\getadmin.vbs" && "%temp%\getadmin.vbs" && exit /B )

rem a)
net user Student1 /add
net user Student2 /add
net user Student3 /add

rem b) 
net localgroup Grupa1 /add
net localgroup Grupa2 /add

rem c) 
net localgroup Grupa1 Student1 /add
net localgroup Grupa1 Student2 /add

rem d) 
net localgroup Grupa2 Student2 /add
net localgroup Grupa2 Student3 /add

rem e) 
mkdir C:\dane_grup

rem f) 
icalcs /?

rem g) 
icacls C:\dane_grup

rem h) 
icacls "C:\dane_grup" /grant Grupa1:(OI)(CI)M

rem i) 
icacls "C:\dane_grup" /grant Grupa2:(OI)(CI)R /deny Grupa2:(OI)(CI)W

rem j) 
icacls "C:\dane_grup"
icacls "C:\dane_grup" | findstr "Student2"