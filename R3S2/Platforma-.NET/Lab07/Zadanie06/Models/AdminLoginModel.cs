using System.ComponentModel.DataAnnotations;

namespace Zadanie06.Models;

public class AdminLoginModel
{
    [Required(ErrorMessage = "Hasło jest wymagane")]
    [DataType(DataType.Password)]
    [Display(Name = "Hasło administratora")]
    public string Password { get; set; } = "";
}
