namespace Zadanie03.Models;

public class Person
{
    public int Id { get; set; }
    public string FirstName { get; set; } = string.Empty;
    public string LastName { get; set; } = string.Empty;
    public string City { get; set; } = string.Empty;
    public DateOnly DateOfBirth { get; set; }
    public decimal Salary { get; set; }
    public string Department { get; set; } = string.Empty;
}
