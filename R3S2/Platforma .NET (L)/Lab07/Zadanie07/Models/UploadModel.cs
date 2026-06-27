using System.ComponentModel.DataAnnotations;

namespace Zadanie07.Models;

public class UploadModel
{
    [Required(ErrorMessage = "Wybierz przynajmniej jeden plik")]
    [Display(Name = "Pliki")]
    public List<IFormFile>? Files { get; set; }
}

public class UploadedFile
{
    public string FileName { get; set; } = "";
    public long Size { get; set; }
    public string ContentType { get; set; } = "";
    public DateTime UploadedAt { get; set; }

    public string SizeFormatted => Size switch
    {
        < 1024 => $"{Size} B",
        < 1024 * 1024 => $"{Size / 1024.0:F1} KB",
        _ => $"{Size / (1024.0 * 1024):F1} MB"
    };
}
