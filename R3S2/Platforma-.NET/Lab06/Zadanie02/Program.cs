using Microsoft.EntityFrameworkCore;
using Zadanie02.Data;
using Zadanie02.Models;

await using var db = new PhoneBookContext();

// Stosujemy WSZYSTKIE oczekujące migracje. W przeciwieństwie do EnsureCreated,
// MigrateAsync wykonuje historię migracji — to jest podejście produkcyjne.
// Jeśli baza nie istnieje — utworzy ją; jeśli istnieje, ale brakuje jej
// najnowszych zmian — dograne zostaną tylko brakujące migracje.
await db.Database.MigrateAsync();

// Wstawiamy dane wykorzystujące pola dodane w drugiej migracji.
if (!await db.People.AnyAsync())
{
    db.People.AddRange(
        new Person { FirstName = "Anna", LastName = "Kowalska", PhoneNumber = "501-111-222",
            DateOfBirth = new(1992, 3, 15), Notes = "Sąsiadka", IsFavorite = true },
        new Person { FirstName = "Jan",  LastName = "Nowak",     PhoneNumber = "601-333-444",
            DateOfBirth = new(1985, 7, 4),  IsFavorite = false },
        new Person { FirstName = "Maria", LastName = "Wiśniewska", PhoneNumber = "789-555-666",
            Notes = "Koleżanka z pracy", IsFavorite = true });
    await db.SaveChangesAsync();
}

Console.WriteLine("== Stan bazy po migracji ==");
foreach (var p in await db.People.ToListAsync())
{
    var fav = p.IsFavorite ? "★" : " ";
    var dob = p.DateOfBirth?.ToString("yyyy-MM-dd") ?? "(?)";
    var notes = string.IsNullOrEmpty(p.Notes) ? "" : $" — {p.Notes}";
    Console.WriteLine($"  {fav} #{p.Id} {p.LastName} {p.FirstName} [{p.PhoneNumber}]  ur. {dob}{notes}");
}

// Tabela __EFMigrationsHistory zawiera ślad zastosowanych migracji.
Console.WriteLine();
Console.WriteLine("== Zastosowane migracje (z __EFMigrationsHistory) ==");
foreach (var m in await db.Database.GetAppliedMigrationsAsync())
    Console.WriteLine($"  ✓ {m}");

Console.WriteLine();
Console.WriteLine("== Wszystkie migracje w assembly ==");
foreach (var m in db.Database.GetMigrations())
    Console.WriteLine($"  • {m}");
