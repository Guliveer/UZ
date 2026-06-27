namespace Zadanie01.Models;

// Encja "Osoba" — element centralny książki telefonicznej.
// Posiada relację 1:1 z Address oraz n:m z Group.
public class Person
{
    public int Id { get; set; }
    public string FirstName { get; set; } = string.Empty;
    public string LastName { get; set; } = string.Empty;
    public string PhoneNumber { get; set; } = string.Empty;
    public string? Email { get; set; }

    // 1:1 — adres jest opcjonalny; klucz obcy znajduje się po stronie Address
    public Address? Address { get; set; }

    // n:m — osoba może należeć do wielu grup, grupa zawiera wielu członków
    public List<Group> Groups { get; set; } = new();
}
