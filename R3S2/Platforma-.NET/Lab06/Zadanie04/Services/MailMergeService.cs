using Microsoft.EntityFrameworkCore;
using Zadanie04.Data;
using Zadanie04.Models;

namespace Zadanie04.Services;

public enum Occasion { Birthday, NameDay }

public record GreetingMessage(Recipient To, Occasion Occasion, string Subject, string Body);

public class MailMergeService(MailContext db)
{
    // Zbiera wszystkich odbiorców, którzy DZIŚ obchodzą urodziny lub imieniny.
    // Filtrujemy po stronie bazy używając Month i Day (EF Core tłumaczy na strftime).
    // Następnie dla każdej dopasowanej osoby tworzymy osobny komunikat per okazja —
    // jeśli ktoś tego dnia ma jednocześnie urodziny i imieniny, dostanie dwie wiadomości.
    public async Task<List<GreetingMessage>> CollectTodaysGreetingsAsync(DateOnly today)
    {
        var birthdayPeople = await db.Recipients
            .Where(r => r.DateOfBirth.Month == today.Month && r.DateOfBirth.Day == today.Day)
            .ToListAsync();

        var nameDayPeople = await db.Recipients
            .Where(r => r.NameDayMonth == today.Month && r.NameDayDay == today.Day)
            .ToListAsync();

        var messages = new List<GreetingMessage>();
        foreach (var r in birthdayPeople)
            messages.Add(new GreetingMessage(r, Occasion.Birthday,
                BuildSubject(r, Occasion.Birthday), BuildBody(r, Occasion.Birthday, today)));
        foreach (var r in nameDayPeople)
            messages.Add(new GreetingMessage(r, Occasion.NameDay,
                BuildSubject(r, Occasion.NameDay), BuildBody(r, Occasion.NameDay, today)));

        return messages;
    }

    public static string BuildSubject(Recipient r, Occasion occasion) => occasion switch
    {
        Occasion.Birthday => $"Wszystkiego najlepszego z okazji urodzin, {r.FirstName}!",
        Occasion.NameDay  => $"Najserdeczniejsze życzenia imieninowe, {r.FirstName}!",
        _ => throw new ArgumentOutOfRangeException(nameof(occasion))
    };

    public static string BuildBody(Recipient r, Occasion occasion, DateOnly today)
    {
        if (occasion == Occasion.Birthday)
        {
            var age = today.Year - r.DateOfBirth.Year;
            if (today < r.DateOfBirth.AddYears(age)) age--;
            return $"""
                Drogi/Droga {r.FirstName},

                Z okazji {age}. urodzin życzę Ci dużo zdrowia, szczęścia i pomyślności
                w życiu osobistym oraz zawodowym. Niech każdy kolejny rok przynosi
                same powody do uśmiechu!

                Sto lat!
                — Korespondencja seryjna (Lab06/Zadanie04)
                """;
        }

        return $"""
            Drogi/Droga {r.FirstName},

            Z okazji Twoich imienin składam najserdeczniejsze życzenia: dużo zdrowia,
            wytrwałości w realizacji marzeń oraz wielu chwil radości w gronie najbliższych.

            Wszystkiego dobrego!
            — Korespondencja seryjna (Lab06/Zadanie04)
            """;
    }

    // Symulator wysyłki — w realnym scenariuszu zamienilibyśmy na System.Net.Mail.SmtpClient
    // lub serwis typu SendGrid/Mailgun. Tu wypisujemy treść do konsoli.
    public static void Send(GreetingMessage msg)
    {
        Console.WriteLine("─────────────────────────────────────────");
        Console.WriteLine($"To:      {msg.To.Email}");
        Console.WriteLine($"Subject: {msg.Subject}");
        Console.WriteLine();
        Console.WriteLine(msg.Body);
        Console.WriteLine();
    }
}
