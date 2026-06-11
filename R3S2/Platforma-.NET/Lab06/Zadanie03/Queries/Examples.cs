using Microsoft.EntityFrameworkCore;
using Zadanie03.Data;
using Zadanie03.Models;

namespace Zadanie03.Queries;

// Każda metoda demonstruje inny operator LINQ. Wszystkie zapytania są
// tłumaczone przez EF Core na SQL i wykonywane po stronie bazy danych
// (deferred execution + IQueryable provider) — uruchamiają się dopiero po
// wywołaniu ToList/Single/Count itp.
public static class Examples
{
    // 1. Where + OrderBy + projekcja na anonimowy typ
    public static async Task FilterAndProject(PersonalContext db)
    {
        var result = await db.People
            .Where(p => p.Salary > 7000m)
            .OrderByDescending(p => p.Salary)
            .Select(p => new { p.LastName, p.Salary, p.Department })
            .ToListAsync();

        Console.WriteLine("\n[1] Osoby z pensją > 7000 zł (malejąco)");
        foreach (var x in result)
            Console.WriteLine($"    {x.LastName,-15} {x.Salary,8:C}  {x.Department}");
    }

    // 2. GroupBy + agregacja
    public static async Task GroupByDepartment(PersonalContext db)
    {
        var result = await db.People
            .GroupBy(p => p.Department)
            .Select(g => new
            {
                Department = g.Key,
                Headcount = g.Count(),
                AvgSalary = g.Average(p => p.Salary),
                MaxSalary = g.Max(p => p.Salary)
            })
            .ToListAsync();

        Console.WriteLine("\n[2] Statystyki per dział");
        foreach (var g in result)
            Console.WriteLine($"    {g.Department,-12}  osoby={g.Headcount}  śr={g.AvgSalary:C}  max={g.MaxSalary:C}");
    }

    // 3. Where z wyrażeniem czasowym — wiek liczony po stronie .NET
    //   (DateOnly nie jest jeszcze w pełni tłumaczony przez SQLite provider,
    //   więc wczytujemy kolumnę i dopiero w pamięci robimy obliczenie wieku).
    public static async Task PeopleOver30(PersonalContext db)
    {
        var today = DateOnly.FromDateTime(DateTime.Today);
        var people = await db.People.ToListAsync();

        var over30 = people
            .Where(p => YearsBetween(p.DateOfBirth, today) >= 30)
            .OrderBy(p => p.LastName)
            .ToList();

        Console.WriteLine("\n[3] Osoby ≥ 30 lat");
        foreach (var p in over30)
            Console.WriteLine($"    {p.LastName} {p.FirstName} (lat {YearsBetween(p.DateOfBirth, today)})");
    }

    // 4. Dystynkcja + sortowanie alfabetyczne
    public static async Task DistinctCities(PersonalContext db)
    {
        var cities = await db.People
            .Select(p => p.City)
            .Distinct()
            .OrderBy(c => c)
            .ToListAsync();

        Console.WriteLine("\n[4] Lista unikalnych miast: " + string.Join(", ", cities));
    }

    // 5. Any / All — typowe zapytania predykatów
    public static async Task PredicateChecks(PersonalContext db)
    {
        var anyMillionaire = await db.People.AnyAsync(p => p.Salary > 1_000_000m);
        var allEarnEnough = await db.People.AllAsync(p => p.Salary >= 4000m);
        Console.WriteLine($"\n[5] Czy ktoś zarabia >1mln? {anyMillionaire}  •  Wszyscy ≥ 4000? {allEarnEnough}");
    }

    // 6. Składanie warunków dynamicznie (klasyczny przypadek wyszukiwarki)
    public static async Task DynamicFilter(PersonalContext db, string? department, decimal? minSalary)
    {
        IQueryable<Person> q = db.People;
        if (!string.IsNullOrEmpty(department)) q = q.Where(p => p.Department == department);
        if (minSalary is not null)             q = q.Where(p => p.Salary >= minSalary.Value);

        var list = await q.OrderBy(p => p.LastName).ToListAsync();
        Console.WriteLine($"\n[6] Filtr dynamiczny (dept={department ?? "-"}, min={minSalary?.ToString("C") ?? "-"}) — {list.Count} osób");
        foreach (var p in list)
            Console.WriteLine($"    {p.LastName} {p.FirstName} ({p.Salary:C}, {p.Department})");
    }

    private static int YearsBetween(DateOnly birth, DateOnly today)
    {
        var years = today.Year - birth.Year;
        if (today < birth.AddYears(years)) years--;
        return years;
    }

    // [A] Top-3 najlepiej zarabiających + ranking (Take + projekcja indeksowana)
    public static async Task TopEarners(PersonalContext db)
    {
        var top = await db.People
            .OrderByDescending(p => p.Salary)
            .Take(3)
            .Select(p => new { p.FirstName, p.LastName, p.Salary, p.Department })
            .ToListAsync();

        Console.WriteLine("\n[A] TOP-3 najlepiej zarabiających");
        for (var i = 0; i < top.Count; i++)
        {
            var x = top[i];
            Console.WriteLine($"    {i + 1}. {x.LastName} {x.FirstName,-12} {x.Salary,8:C}  {x.Department}");
        }
    }

    // [B] Najmłodsza osoba w każdym mieście (GroupBy + First po posortowaniu)
    public static async Task YoungestPerCity(PersonalContext db)
    {
        var people = await db.People.ToListAsync();
        var youngest = people
            .GroupBy(p => p.City)
            .Select(g => g.OrderByDescending(p => p.DateOfBirth).First())
            .OrderBy(p => p.City)
            .ToList();

        Console.WriteLine("\n[B] Najmłodsza osoba w każdym mieście");
        foreach (var p in youngest)
            Console.WriteLine($"    {p.City,-15} → {p.LastName} {p.FirstName} (ur. {p.DateOfBirth:yyyy-MM-dd})");
    }
}
