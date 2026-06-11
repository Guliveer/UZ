using Microsoft.EntityFrameworkCore;
using Zadanie01.Models;

namespace Zadanie01.Data;

// DbContext to "okno na bazę" — każda właściwość DbSet<T> odpowiada tabeli.
// W metodzie OnModelCreating definiujemy reguły mapowania (Fluent API),
// których nie da się wyrazić atrybutami lub konwencjami.
public class PhoneBookContext : DbContext
{
    public DbSet<Person> People => Set<Person>();
    public DbSet<Address> Addresses => Set<Address>();
    public DbSet<Group> Groups => Set<Group>();

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
    {
        // Plik bazy zostanie utworzony obok skompilowanego pliku .exe.
        optionsBuilder.UseSqlite("Data Source=phonebook.db");
    }

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        // 1:1 — Address dzieli klucz główny z Person (shared primary key).
        // Dzięki temu EF Core wymusi unikalność relacji na poziomie schematu.
        modelBuilder.Entity<Address>()
            .HasKey(a => a.PersonId);

        modelBuilder.Entity<Person>()
            .HasOne(p => p.Address)
            .WithOne(a => a.Person!)
            .HasForeignKey<Address>(a => a.PersonId)
            .OnDelete(DeleteBehavior.Cascade);

        // n:m — bez explicit join entity. EF utworzy tabelę GroupPerson.
        modelBuilder.Entity<Person>()
            .HasMany(p => p.Groups)
            .WithMany(g => g.People);

        // Numer telefonu unikalny — typowe ograniczenie w książce telefonicznej.
        modelBuilder.Entity<Person>()
            .HasIndex(p => p.PhoneNumber)
            .IsUnique();
    }
}
