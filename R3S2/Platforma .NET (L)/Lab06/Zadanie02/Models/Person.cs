namespace Zadanie02.Models;

public class Person
{
    public int Id { get; set; }
    public string FirstName { get; set; } = string.Empty;
    public string LastName { get; set; } = string.Empty;
    public string PhoneNumber { get; set; } = string.Empty;

    // Pola dodane w drugiej migracji ("AddPersonExtras") — pokazują, że
    // EF Core potrafi rozszerzyć istniejącą tabelę bez utraty danych.
    public DateOnly? DateOfBirth { get; set; }
    public string? Notes { get; set; }
    public bool IsFavorite { get; set; }
}
