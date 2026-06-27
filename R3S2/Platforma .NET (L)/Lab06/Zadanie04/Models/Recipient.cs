namespace Zadanie04.Models;

// Adresat korespondencji seryjnej.
// FirstName służy do podstawienia w treści maila ("Drogi/Droga {FirstName}").
// NameDay przechowujemy jako (Month, Day), bo imieniny powtarzają się każdego roku
// i nie powinny być wiązane z konkretnym rokiem urodzenia.
public class Recipient
{
    public int Id { get; set; }
    public string FirstName { get; set; } = string.Empty;
    public string LastName { get; set; } = string.Empty;
    public string Email { get; set; } = string.Empty;
    public DateOnly DateOfBirth { get; set; }
    public int NameDayMonth { get; set; }
    public int NameDayDay { get; set; }
}
