using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using Zadanie04.Models;

namespace Zadanie04.Controllers;

public class HomeController : Controller
{
    [HttpGet]
    public IActionResult Index() => View(new RegistrationModel());

    [HttpPost]
    public IActionResult Index(RegistrationModel model)
    {
        if (!ModelState.IsValid)
            return View(model);

        TempData["SuccessMessage"] = $"Rejestracja zakończona sukcesem! Witaj, {model.FirstName} {model.LastName}!";
        return RedirectToAction(nameof(Success));
    }

    public IActionResult Success() => View();

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error() => View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
}
