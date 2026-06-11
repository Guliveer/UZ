using Microsoft.EntityFrameworkCore;
using Zadanie01.Data;
using Zadanie01.Models;

namespace Zadanie01.Services;

public class PhoneBookService(PhoneBookContext db)
{
    public async Task AddPersonAsync(Person person, IEnumerable<string>? groupNames = null)
    {
        if (groupNames is not null)
        {
            foreach (var name in groupNames)
            {
                var group = await db.Groups.FirstOrDefaultAsync(g => g.Name == name)
                            ?? new Group { Name = name };
                person.Groups.Add(group);
            }
        }

        db.People.Add(person);
        await db.SaveChangesAsync();
    }

    // Wyszukiwanie wieloaspektowe: imię, nazwisko, numer telefonu, e-mail,
    // miasto adresu lub nazwa grupy. SQLite traktuje LIKE jako case-insensitive
    // dla znaków ASCII; dla pełnej niezależności od wielkości literek normalizujemy
    // do lower-case po obu stronach. Eager-loading Address i Groups, bo wyniki
    // wyszukiwania zwykle prezentowane są razem z kontekstem.
    public async Task<List<Person>> FindPeopleAsync(string query)
    {
        var pattern = $"%{query.Trim().ToLower()}%";

        return await db.People
            .Include(p => p.Address)
            .Include(p => p.Groups)
            .Where(p =>
                EF.Functions.Like(p.FirstName.ToLower(), pattern) ||
                EF.Functions.Like(p.LastName.ToLower(),  pattern) ||
                EF.Functions.Like(p.PhoneNumber,         pattern) ||
                (p.Email != null && EF.Functions.Like(p.Email.ToLower(), pattern)) ||
                (p.Address != null && EF.Functions.Like(p.Address.City.ToLower(), pattern)) ||
                p.Groups.Any(g => EF.Functions.Like(g.Name.ToLower(), pattern)))
            .OrderBy(p => p.LastName)
            .ToListAsync();
    }

    public async Task<List<Person>> AllAsync() =>
        await db.People
            .Include(p => p.Address)
            .Include(p => p.Groups)
            .OrderBy(p => p.LastName)
            .ToListAsync();
}
