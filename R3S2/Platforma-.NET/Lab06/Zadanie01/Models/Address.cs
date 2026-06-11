namespace Zadanie01.Models;

// Encja "Adres" — strona zależna w relacji 1:1 z Person.
// PersonId pełni jednocześnie rolę PK i FK (shared primary key) — typowy wzorzec
// dla 1:1 w EF Core, gwarantujący że nie da się dodać dwóch adresów do jednej osoby.
public class Address
{
    public int PersonId { get; set; }
    public string Street { get; set; } = string.Empty;
    public string City { get; set; } = string.Empty;
    public string PostalCode { get; set; } = string.Empty;

    public Person? Person { get; set; }
}
