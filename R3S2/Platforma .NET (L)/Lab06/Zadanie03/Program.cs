using Microsoft.EntityFrameworkCore;
using Zadanie03.Data;
using Zadanie03.Queries;

await using var db = new PersonalContext();
await db.Database.EnsureDeletedAsync();
await db.Database.EnsureCreatedAsync();
await db.SeedAsync();

await Examples.FilterAndProject(db);
await Examples.GroupByDepartment(db);
await Examples.PeopleOver30(db);
await Examples.DistinctCities(db);
await Examples.PredicateChecks(db);
await Examples.DynamicFilter(db, department: "IT", minSalary: 8000m);

await Examples.TopEarners(db);
await Examples.YoungestPerCity(db);
