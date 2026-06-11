using Zadanie04.Data;
using Zadanie04.Services;

var today = DateOnly.FromDateTime(DateTime.Today);

await using var db = new MailContext();
await db.Database.EnsureDeletedAsync();
await db.Database.EnsureCreatedAsync();
await db.SeedAsync(today);

Console.WriteLine($"Dzisiaj: {today:yyyy-MM-dd}\n");

var mailer = new MailMergeService(db);
var messages = await mailer.CollectTodaysGreetingsAsync(today);

if (messages.Count == 0)
{
    Console.WriteLine("Dziś nie ma żadnych urodzin ani imienin.");
    return;
}

Console.WriteLine($"Wysyłam {messages.Count} życzeń:\n");
foreach (var msg in messages)
    MailMergeService.Send(msg);
