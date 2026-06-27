using Microsoft.AspNetCore.Mvc;
using Zadanie06.Models;
using Zadanie06.Services;

namespace Zadanie06.Controllers;

public class AdminController : Controller
{
    private const string AdminPassword = "admin123";
    private const string SessionKey = "IsAdmin";
    private readonly NewsService _news;

    public AdminController(NewsService news) => _news = news;

    private bool IsLoggedIn => HttpContext.Session.GetString(SessionKey) == "true";

    [HttpGet]
    public IActionResult Login() => View(new AdminLoginModel());

    [HttpPost]
    public IActionResult Login(AdminLoginModel model)
    {
        if (!ModelState.IsValid) return View(model);

        if (model.Password != AdminPassword)
        {
            ModelState.AddModelError("Password", "Nieprawidłowe hasło");
            return View(model);
        }

        HttpContext.Session.SetString(SessionKey, "true");
        return RedirectToAction(nameof(Index));
    }

    public IActionResult Logout()
    {
        HttpContext.Session.Remove(SessionKey);
        return RedirectToAction("Index", "Home");
    }

    [HttpGet]
    public IActionResult Index()
    {
        if (!IsLoggedIn) return RedirectToAction(nameof(Login));
        return View(_news.GetAll());
    }

    [HttpGet]
    public IActionResult Add()
    {
        if (!IsLoggedIn) return RedirectToAction(nameof(Login));
        return View(new NewsItem());
    }

    [HttpPost]
    public IActionResult Add(NewsItem item)
    {
        if (!IsLoggedIn) return RedirectToAction(nameof(Login));
        if (!ModelState.IsValid) return View(item);
        _news.Add(item);
        TempData["Success"] = "Wiadomość dodana pomyślnie.";
        return RedirectToAction(nameof(Index));
    }

    [HttpPost]
    public IActionResult Delete(int id)
    {
        if (!IsLoggedIn) return RedirectToAction(nameof(Login));
        _news.Delete(id);
        return RedirectToAction(nameof(Index));
    }
}
