# HeartbeatService (Lab04 / Zadanie_01 / Podzadanie_03)

Demo uslugi systemu Windows. Co 10 sekund zapisuje wpis informacyjny do dziennika zdarzen (`Application`, zrodlo `HeartbeatService`).

## Wymagania

- Windows 10/11
- .NET Framework 4.8 Developer Pack
- Uprawnienia administratora (do instalacji uslugi)

## Kompilacja

Projekt uzywa klasycznego formatu `.csproj` (nie SDK-style). Buduj w **Visual Studio 2022/2026** (Build → Build Solution) albo z konsoli dewelopera:

```cmd
msbuild HeartbeatService.csproj /p:Configuration=Debug
```

`dotnet build` nie obsluguje szablonu Windows Service (.NET Framework) bezposrednio.

## Instalacja (PowerShell / cmd jako administrator)

```cmd
install.cmd
```

Skrypt wywoluje `sc create HeartbeatService binPath= "...\bin\Debug\HeartbeatService.exe"`, a potem `sc start`. Po chwili w podgladzie zdarzen (`eventvwr.msc` → Windows Logs → Application, Source: HeartbeatService) powinny pojawic sie wpisy "Heartbeat yyyy-MM-dd HH:mm:ss" co 10 s.

## Dezinstalacja

```cmd
uninstall.cmd
```

Wywoluje `sc stop HeartbeatService` i `sc delete HeartbeatService`.

## Alternatywa: InstallUtil

Jesli wolisz kanoniczne narzedzie `.NET Framework`:

```cmd
"%WINDIR%\Microsoft.NET\Framework\v4.0.30319\InstallUtil.exe" bin\Debug\HeartbeatService.exe
"%WINDIR%\Microsoft.NET\Framework\v4.0.30319\InstallUtil.exe" /u bin\Debug\HeartbeatService.exe
```

`InstallUtil` uzywa klasy `ProjectInstaller` z tego projektu, wiec konto (`LocalSystem`) i tryb startu (`Manual`) zostana ustawione automatycznie.
