using Microsoft.EntityFrameworkCore;
using Zadanie01.Data;
using Zadanie01.Models;
using Zadanie01.Services;

// Tworzymy bazę od zera przy każdym uruchomieniu — wygodne do demo.
// W realnym projekcie zamiast EnsureCreated używa się migracji (Zadanie02).
await using var db = new PhoneBookContext();
await db.Database.EnsureDeletedAsync();
await db.Database.EnsureCreatedAsync();

var service = new PhoneBookService(db);

// Dane startowe: trzy osoby, dwie grupy, dwa adresy.
await service.AddPersonAsync(
    new Person
    {
        FirstName = "Anna", LastName = "Kowalska", PhoneNumber = "501-111-222",
        Email = "anna@example.com",
        Address = new Address { Street = "Prosta 1", City = "Zielona Góra", PostalCode = "65-001" }
    },
    groupNames: new[] { "Rodzina", "Praca" });

await service.AddPersonAsync(
    new Person
    {
        FirstName = "Jan", LastName = "Nowak", PhoneNumber = "601-333-444",
        Address = new Address { Street = "Krzywa 7", City = "Poznań", PostalCode = "60-100" }
    },
    groupNames: new[] { "Praca" });

await service.AddPersonAsync(
    new Person
    {
        FirstName = "Maria", LastName = "Wiśniewska", PhoneNumber = "789-555-666"
    },
    groupNames: new[] { "Rodzina" });

Console.WriteLine("== Wszystkie osoby ==");
foreach (var p in await service.AllAsync())
{
    var addr = p.Address is null ? "(brak adresu)" : $"{p.Address.City}, {p.Address.Street}";
    var groups = p.Groups.Count == 0 ? "—" : string.Join(", ", p.Groups.Select(g => g.Name));
    Console.WriteLine($"  {p.LastName} {p.FirstName} [{p.PhoneNumber}]  •  {addr}  •  grupy: {groups}");
}

// Wyszukiwanie — przyjmuje frazę z argumentu CLI lub interaktywnie.
// Domyślne demo wykonuje trzy zapytania pokazujące różne ścieżki dopasowania.
Console.WriteLine();
string[] queries = args.Length > 0 ? args : new[] { "Kowal", "Praca", "601" };

foreach (var query in queries)
{
    Console.WriteLine($"== Wyszukaj: \"{query}\" ==");
    var results = await service.FindPeopleAsync(query);
    Console.WriteLine($"  Znaleziono {results.Count} osób:");
    foreach (var p in results)
    {
        var groups = p.Groups.Count == 0 ? "—" : string.Join(", ", p.Groups.Select(g => g.Name));
        Console.WriteLine($"    {p.LastName} {p.FirstName} [{p.PhoneNumber}] grupy: {groups}");
    }
    Console.WriteLine();
}
