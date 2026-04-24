@echo off
setlocal EnableDelayedExpansion

REM ============================================================
REM This script automates applying the required naming convention
REM and structure enforced by the course instructor.
REM It copies a selected LabXX folder, renames it according to
REM the required schema, removes build artifacts (bin/obj/.claude),
REM replaces occurrences inside files, renames the solution file
REM (.sln or .slnx), and compresses the result into a .7z archive
REM (max compression).
REM ============================================================

echo.
echo Available Lab folders:
echo ----------------------

set count=0
for /d %%D in (Lab??) do (
    set /a count+=1
    echo !count!. %%D
    set "lab[!count!]=%%D"
)

if %count%==0 (
    echo No Lab folders found.
    pause
    exit /b
)

echo.
set /p choice=Select a lab by number: 

if not defined lab[%choice%] (
    echo Invalid selection.
    pause
    exit /b
)

set "selectedLab=!lab[%choice%]!"
echo Selected: %selectedLab%

REM Extract lab number (last 2 characters)
set "labNumber=%selectedLab:~-2%"

echo.
set /p firstName=Enter your first name: 
set /p lastName=Enter your last name: 

REM Build new folder name
set "newName=Plat-dot-NET-34INF-SSI-SP_LAB_%labNumber%_%firstName%_%lastName%"

echo.
echo Creating copy...

xcopy "%selectedLab%" "%newName%\" /E /I /H /Y >nul

if errorlevel 1 (
    echo Copy failed.
    pause
    exit /b
)

echo Removing bin/, obj/ and .claude/ folders...

for /d /r "%newName%" %%D in (bin obj .claude) do (
    if exist "%%D" rd /s /q "%%D"
)

echo Renaming solution file...

set "solutionRenamed=false"

for %%E in (sln slnx) do (
    if exist "%newName%\%selectedLab%.%%E" (
        ren "%newName%\%selectedLab%.%%E" "%newName%.%%E"
        set "solutionRenamed=true"
    )
)

if "%solutionRenamed%"=="false" (
    for /r "%newName%" %%F in (*.sln *.slnx) do (
        if "%solutionRenamed%"=="false" (
            ren "%%F" "%newName%%%~xF"
            set "solutionRenamed=true"
        )
    )
)

echo Replacing occurrences in text files...

for /r "%newName%" %%F in (*.sln *.slnx *.csproj *.cs *.config *.json *.xml *.txt) do (
    powershell -Command ^
    "(Get-Content -Raw '%%F') -replace '%selectedLab%', '%newName%' | Set-Content '%%F'"
)

echo Creating archive with maximum compression...

set "zipPath=7z"

%zipPath% a -t7z "%newName%.7z" "%newName%" -mx=9 -mmt=on -ms=on >nul

if errorlevel 1 (
    echo Compression failed.
    pause
    exit /b
)

echo.
echo Done successfully!
echo Output:
echo - Folder: %newName%
echo - Archive: %newName%.7z

pause