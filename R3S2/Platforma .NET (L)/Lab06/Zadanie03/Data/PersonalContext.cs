using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Storage.ValueConversion;
using Zadanie03.Models;

namespace Zadanie03.Data;

public class PersonalContext : DbContext
{
    public DbSet<Person> People => Set<Person>();

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder) =>
        optionsBuilder.UseSqlite("Data Source=personal.db");

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        // SQLite nie wspiera natywnie typu decimal w klauzulach ORDER BY ani agregacjach.
        // Konwerter mapuje decimal -> double (REAL w SQLite). Dla pełnej precyzji finansowej
        // zwykle przechowuje się grosze jako long.
        var decimalAsDouble = new ValueConverter<decimal, double>(
            v => (double)v,
            v => (decimal)v);

        modelBuilder.Entity<Person>()
            .Property(p => p.Salary)
            .HasConversion(decimalAsDouble);
    }

    public async Task SeedAsync()
    {
        if (await People.AnyAsync()) return;

        People.AddRange(
            new Person { FirstName = "Anna",    LastName = "Kowalska",    City = "Zielona Góra", DateOfBirth = new(1992,  3, 15), Salary = 7500m,  Department = "IT" },
            new Person { FirstName = "Jan",     LastName = "Nowak",       City = "Poznań",       DateOfBirth = new(1985,  7,  4), Salary = 9200m,  Department = "IT" },
            new Person { FirstName = "Maria",   LastName = "Wiśniewska",  City = "Warszawa",     DateOfBirth = new(1998, 11, 22), Salary = 5400m,  Department = "HR" },
            new Person { FirstName = "Piotr",   LastName = "Wójcik",      City = "Warszawa",     DateOfBirth = new(1979,  1, 30), Salary = 12500m, Department = "Finanse" },
            new Person { FirstName = "Katarzyna", LastName = "Lewandowska", City = "Zielona Góra", DateOfBirth = new(2001,  5,  9), Salary = 4200m, Department = "HR" },
            new Person { FirstName = "Tomasz",  LastName = "Zieliński",   City = "Poznań",       DateOfBirth = new(1990,  8, 18), Salary = 8100m,  Department = "IT" },
            new Person { FirstName = "Agnieszka", LastName = "Szymańska", City = "Kraków",       DateOfBirth = new(1995, 12, 28), Salary = 6800m,  Department = "Marketing" },
            new Person { FirstName = "Marek",   LastName = "Dąbrowski",   City = "Kraków",       DateOfBirth = new(1988,  4, 11), Salary = 10300m, Department = "Finanse" });

        await SaveChangesAsync();
    }
}
