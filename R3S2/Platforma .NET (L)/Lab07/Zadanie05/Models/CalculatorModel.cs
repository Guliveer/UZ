using System.ComponentModel.DataAnnotations;

namespace Zadanie05.Models;

public class CalculatorModel
{
    [Required(ErrorMessage = "Pierwsza liczba jest wymagana")]
    [Display(Name = "Pierwsza liczba")]
    public double? A { get; set; }

    [Required(ErrorMessage = "Druga liczba jest wymagana")]
    [Display(Name = "Druga liczba")]
    public double? B { get; set; }

    [Required(ErrorMessage = "Wybierz operację")]
    [Display(Name = "Operacja")]
    public string Operation { get; set; } = "+";

    public double? Result { get; set; }
    public string? Error { get; set; }
}
