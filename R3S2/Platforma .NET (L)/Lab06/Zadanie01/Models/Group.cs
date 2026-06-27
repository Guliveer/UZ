namespace Zadanie01.Models;

// Encja "Grupa" — np. "Rodzina", "Praca", "Znajomi".
// Relacja n:m z Person — EF Core 7+ tworzy tabelę pośredniczącą automatycznie
// (skip navigation), bez konieczności ręcznego definiowania klasy łącznikowej.
public class Group
{
    public int Id { get; set; }
    public string Name { get; set; } = string.Empty;

    public List<Person> People { get; set; } = new();
}
