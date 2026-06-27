using Microsoft.EntityFrameworkCore;
using Zadanie04.Models;

namespace Zadanie04.Data;

public class MailContext : DbContext
{
    public DbSet<Recipient> Recipients => Set<Recipient>();

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder) =>
        optionsBuilder.UseSqlite("Data Source=mailmerge.db");

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Recipient>()
            .HasIndex(r => r.Email)
            .IsUnique();
    }

    public async Task SeedAsync(DateOnly today)
    {
        if (await Recipients.AnyAsync()) return;

        // Przygotowujemy dane tak, by dla "dzisiaj" zawsze ktoś miał urodziny i ktoś imieniny —
        // ułatwia to weryfikację działania filtra niezależnie od daty uruchomienia.
        Recipients.AddRange(
            new Recipient { FirstName = "Anna",  LastName = "Kowalska",   Email = "anna@example.com",
                DateOfBirth = new(1992, today.Month, today.Day),
                NameDayMonth = 7, NameDayDay = 26 },
            new Recipient { FirstName = "Jan",   LastName = "Nowak",      Email = "jan@example.com",
                DateOfBirth = new(1985, 4, 4),
                NameDayMonth = today.Month, NameDayDay = today.Day },
            new Recipient { FirstName = "Maria", LastName = "Wiśniewska", Email = "maria@example.com",
                DateOfBirth = new(1998, 11, 22),
                NameDayMonth = 8, NameDayDay = 15 },
            new Recipient { FirstName = "Piotr", LastName = "Wójcik",     Email = "piotr@example.com",
                DateOfBirth = new(1979, today.Month, today.Day),
                NameDayMonth = today.Month, NameDayDay = today.Day });

        await SaveChangesAsync();
    }
}
