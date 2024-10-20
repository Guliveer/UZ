@echo off
set /a start=%1
set /a end=%2

for /l %%i in (%start%,1,%end%) do (
    echo %%i
)

start wypisz_liczby.bat 1 10;