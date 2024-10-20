@echo off
:Start
echo Witaj w kalkulatorze!
echo Podaj pierwsza liczbe:
set /p num1=
echo Podaj operator (+, -, *, /):
set /p operator=
echo Podaj druga liczbe:
set /p num2=

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

echo Czy chcesz wykonac kolejne obliczenie? (tak/nie)
set /p answer=
if /i "%answer%"=="tak" (
    goto Start
) else (
    echo Dziekujemy za skorzystanie z kalkulatora!
)

exit /b
