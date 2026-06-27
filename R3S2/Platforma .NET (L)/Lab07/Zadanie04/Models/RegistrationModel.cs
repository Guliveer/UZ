using System.ComponentModel.DataAnnotations;

namespace Zadanie04.Models;

public class RegistrationModel
{
    [Required(ErrorMessage = "Imię jest wymagane")]
    [StringLength(50, MinimumLength = 2, ErrorMessage = "Imię musi mieć od 2 do 50 znaków")]
    [Display(Name = "Imię")]
    public string FirstName { get; set; } = "";

    [Required(ErrorMessage = "Nazwisko jest wymagane")]
    [StringLength(100, MinimumLength = 2, ErrorMessage = "Nazwisko musi mieć od 2 do 100 znaków")]
    [Display(Name = "Nazwisko")]
    public string LastName { get; set; } = "";

    [Required(ErrorMessage = "Adres e-mail jest wymagany")]
    [EmailAddress(ErrorMessage = "Nieprawidłowy format adresu e-mail")]
    [Display(Name = "Adres e-mail")]
    public string Email { get; set; } = "";

    [Required(ErrorMessage = "Wiek jest wymagany")]
    [Range(18, 120, ErrorMessage = "Wiek musi być między 18 a 120")]
    [Display(Name = "Wiek")]
    public int? Age { get; set; }

    [Required(ErrorMessage = "Hasło jest wymagane")]
    [StringLength(100, MinimumLength = 8, ErrorMessage = "Hasło musi mieć co najmniej 8 znaków")]
    [DataType(DataType.Password)]
    [Display(Name = "Hasło")]
    public string Password { get; set; } = "";

    [Required(ErrorMessage = "Potwierdzenie hasła jest wymagane")]
    [DataType(DataType.Password)]
    [Compare("Password", ErrorMessage = "Hasła nie są identyczne")]
    [Display(Name = "Potwierdź hasło")]
    public string ConfirmPassword { get; set; } = "";

    [Required(ErrorMessage = "Numer telefonu jest wymagany")]
    [Phone(ErrorMessage = "Nieprawidłowy format numeru telefonu")]
    [Display(Name = "Telefon")]
    public string Phone { get; set; } = "";

    [Required(ErrorMessage = "Musisz zaakceptować regulamin")]
    [Range(typeof(bool), "true", "true", ErrorMessage = "Musisz zaakceptować regulamin")]
    [Display(Name = "Akceptuję regulamin")]
    public bool AcceptTerms { get; set; }
}
