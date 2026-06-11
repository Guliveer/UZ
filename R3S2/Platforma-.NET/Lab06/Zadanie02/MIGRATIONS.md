# System migracji EF Core — przewodnik

## Cykl pracy

```bash
# 1. Wygeneruj migrację z bieżącego stanu modelu
dotnet ef migrations add <Nazwa>

# 2. Zastosuj wszystkie zaległe migracje do bazy
dotnet ef database update

# 3. Wycofaj się do wcześniejszej migracji
dotnet ef database update <NazwaPoprzedniej>

# 4. Usuń ostatnią migrację (przed apply!)
dotnet ef migrations remove

# 5. Wyświetl skrypt SQL dla wszystkich migracji
dotnet ef migrations script
```

## Struktura wygenerowanego pliku

Każda migracja składa się z dwóch metod:

- `Up()` — operacje wykonywane przy stosowaniu migracji w przód
- `Down()` — operacje wycofujące zmianę (inwersja `Up`)

EF Core utrzymuje tabelę `__EFMigrationsHistory` w bazie, w której rejestruje
zastosowane migracje. Dzięki temu wie, czego jeszcze nie wykonał.

## Twoje zadanie

1. Uruchom projekt — utworzy bazę z migracji `Initial`.
2. Otwórz `Models/Person.cs` i dodaj jedno pole (np. `DateOnly DateOfBirth`).
3. Wygeneruj drugą migrację:
   ```bash
   dotnet ef migrations add AddDateOfBirth
   ```
4. Sprawdź zawartość pliku `Migrations/<timestamp>_AddDateOfBirth.cs` — zobaczysz
   wywołanie `migrationBuilder.AddColumn<DateOnly>(...)`.
5. Uruchom aplikację ponownie — migracja zostanie zastosowana, a tabela `Person`
   wzbogacona o nową kolumnę. Istniejące rekordy pozostaną nietknięte.
