using System.ComponentModel;
using System.Text;
using Microsoft.SemanticKernel;

public sealed class ReservationPlugin
{
    private readonly List<string> _reservations = [];

    // ── Restauracja ──────────────────────────────────────────────────────────

    [KernelFunction, Description("Rezerwuje stolik w restauracji.")]
    public string RezerwujStolik(
        [Description("Imię i nazwisko gościa")] string imie,
        [Description("Data i godzina rezerwacji, np. '2026-06-15 19:00'")] string termin,
        [Description("Liczba osób")] int liczbaOsob,
        [Description("Opcjonalne życzenia specjalne, np. 'przy oknie', 'urodziny'")] string zyczenia = "")
    {
        var id = $"REST-{_reservations.Count + 1:D3}";
        var entry = $"[{id}] Restauracja | {imie} | {termin} | {liczbaOsob} os." +
                    (string.IsNullOrWhiteSpace(zyczenia) ? "" : $" | {zyczenia}");
        _reservations.Add(entry);
        return $"Stolik zarezerwowany! Nr rezerwacji: {id}. Szczegóły: {entry}";
    }

    // ── Kino ─────────────────────────────────────────────────────────────────

    [KernelFunction, Description("Zwraca listę dostępnych filmów z godzinami seansów.")]
    public string ListaFilmow() => """
        Dostępne filmy:
          1. "Interstellar" – 18:00, 21:00
          2. "Dune: Część Trzecia" – 17:30, 20:30
          3. "Matrix Reloaded" – 19:00, 22:00
        """;

    [KernelFunction, Description("Kupuje bilet(y) do kina.")]
    public string KupBiletDoKina(
        [Description("Tytuł filmu")] string film,
        [Description("Godzina seansu, np. '20:30'")] string godzina,
        [Description("Imię kupującego")] string imie,
        [Description("Liczba biletów")] int liczba = 1)
    {
        var id = $"KINO-{_reservations.Count + 1:D3}";
        var entry = $"[{id}] Kino | {imie} | \"{film}\" godz. {godzina} | {liczba} bilet(y)";
        _reservations.Add(entry);
        var cena = liczba * 25.0;
        return $"Bilety zakupione! Nr zamówienia: {id}. {liczba} × 25 zł = {cena} zł. Szczegóły: {entry}";
    }

    // ── Teatr ─────────────────────────────────────────────────────────────────

    [KernelFunction, Description("Zwraca repertuar teatru z dostępnymi przedstawieniami.")]
    public string RepertuarTeatru() => """
        Repertuar Teatru Miejskiego:
          1. "Hamlet" (Szekspir) – 19:00, premiera 14.06
          2. "Zemsta" (Fredro) – 18:30, 15.06 i 16.06
          3. "Czekając na Godota" (Beckett) – 20:00, 17.06
        """;

    [KernelFunction, Description("Rezerwuje bilety do teatru.")]
    public string RezerwujBiletDoTeatru(
        [Description("Tytuł spektaklu")] string spektakl,
        [Description("Data i godzina, np. '2026-06-15 18:30'")] string termin,
        [Description("Imię i nazwisko")] string imie,
        [Description("Liczba biletów")] int liczba = 1)
    {
        var id = $"TEATR-{_reservations.Count + 1:D3}";
        var entry = $"[{id}] Teatr | {imie} | \"{spektakl}\" {termin} | {liczba} bilet(y)";
        _reservations.Add(entry);
        var cena = liczba * 45.0;
        return $"Rezerwacja przyjęta! Nr: {id}. {liczba} × 45 zł = {cena} zł. Szczegóły: {entry}";
    }

    // ── Historia ─────────────────────────────────────────────────────────────

    [KernelFunction, Description("Wyświetla wszystkie bieżące rezerwacje i zamówienia użytkownika.")]
    public string MojeRezerwacje()
    {
        if (_reservations.Count == 0)
            return "Brak aktywnych rezerwacji.";

        var sb = new StringBuilder("Twoje rezerwacje:\n");
        foreach (var r in _reservations)
            sb.AppendLine($"  • {r}");
        return sb.ToString();
    }
}
