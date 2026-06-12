using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using Zadanie07.Models;

namespace Zadanie07.Controllers;

public class HomeController : Controller
{
    private static readonly List<UploadedFile> _uploadedFiles = [];
    private const long MaxFileSizeBytes = 10 * 1024 * 1024; // 10 MB

    [HttpGet]
    public IActionResult Index() => View((new UploadModel(), _uploadedFiles.AsReadOnly()));

    [HttpPost]
    public async Task<IActionResult> Upload(UploadModel model)
    {
        if (!ModelState.IsValid)
            return View("Index", (model, _uploadedFiles.AsReadOnly()));

        var uploaded = new List<string>();
        var errors = new List<string>();

        foreach (var file in model.Files!)
        {
            if (file.Length > MaxFileSizeBytes)
            {
                errors.Add($"{file.FileName}: plik przekracza limit 10 MB");
                continue;
            }

            var safeName = Path.GetFileNameWithoutExtension(file.FileName)
                + "_" + DateTime.Now.Ticks
                + Path.GetExtension(file.FileName);

            var uploadDir = Path.Combine(Directory.GetCurrentDirectory(), "wwwroot", "uploads");
            Directory.CreateDirectory(uploadDir);

            var path = Path.Combine(uploadDir, safeName);
            await using var stream = System.IO.File.Create(path);
            await file.CopyToAsync(stream);

            _uploadedFiles.Insert(0, new UploadedFile
            {
                FileName = file.FileName,
                Size = file.Length,
                ContentType = file.ContentType,
                UploadedAt = DateTime.Now
            });
            uploaded.Add(file.FileName);
        }

        if (errors.Any())
            TempData["Errors"] = string.Join("; ", errors);

        if (uploaded.Any())
            TempData["Success"] = $"Przesłano: {string.Join(", ", uploaded)}";

        return RedirectToAction(nameof(Index));
    }

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error() => View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
}
