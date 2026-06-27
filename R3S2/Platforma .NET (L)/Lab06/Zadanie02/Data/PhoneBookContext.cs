using Microsoft.EntityFrameworkCore;
using Zadanie02.Models;

namespace Zadanie02.Data;

public class PhoneBookContext : DbContext
{
    public DbSet<Person> People => Set<Person>();

    protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder) =>
        optionsBuilder.UseSqlite("Data Source=phonebook_v2.db");

    protected override void OnModelCreating(ModelBuilder modelBuilder)
    {
        modelBuilder.Entity<Person>()
            .HasIndex(p => p.PhoneNumber)
            .IsUnique();
    }
}
