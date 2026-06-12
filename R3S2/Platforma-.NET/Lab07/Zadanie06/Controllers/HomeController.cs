using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using Zadanie06.Models;
using Zadanie06.Services;

namespace Zadanie06.Controllers;

public class HomeController : Controller
{
    private readonly NewsService _news;

    public HomeController(NewsService news) => _news = news;

    public IActionResult Index() => View(_news.GetAll());

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error() => View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
}
