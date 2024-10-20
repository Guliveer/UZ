@echo off

rem Sprawdzenie liczby argumentów
if "%~3"=="" (
    echo Uzycie: kalkulator.bat liczba1 operator liczba2
    echo Przykład: kalkulator.bat 5 + 3
    exit /b
)

set num1=%~1
set operator=%~2
set num2=%~3

rem Sprawdzanie operatora
if "%operator%"=="+" (
    set /a result=num1 + num2
    echo Wynik: %result%
) else if "%operator%"=="-" (
    set /a result=num1 - num2
    echo Wynik: %result%
) else if "%operator%"=="*" (
    set /a result=num1 * num2
    echo Wynik: %result%
) else if "%operator%"=="/" (
    if %num2%==0 (
        echo Blad: Dzielenie przez zero!
    ) else (
        set /a result=num1 / num2
        echo Wynik: %result%
    )
) else (
    echo Blad: Nieznany operator!
)

exit /b
