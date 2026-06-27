using System.Diagnostics;
using Microsoft.AspNetCore.Mvc;
using Zadanie05.Models;

namespace Zadanie05.Controllers;

public class HomeController : Controller
{
    [HttpGet]
    public IActionResult Index() => View(new CalculatorModel());

    [HttpPost]
    public IActionResult Index(CalculatorModel model)
    {
        if (!ModelState.IsValid)
            return View(model);

        double a = model.A!.Value, b = model.B!.Value;
        model.Result = model.Operation switch
        {
            "+" => a + b,
            "-" => a - b,
            "*" => a * b,
            "/" when b == 0 => null,
            "/" => a / b,
            "%" when b == 0 => null,
            "%" => a % b,
            "^" => Math.Pow(a, b),
            _ => null
        };

        if (model.Result == null && (model.Operation == "/" || model.Operation == "%"))
            model.Error = "Błąd: dzielenie przez zero!";

        return View(model);
    }

    [ResponseCache(Duration = 0, Location = ResponseCacheLocation.None, NoStore = true)]
    public IActionResult Error() => View(new ErrorViewModel { RequestId = Activity.Current?.Id ?? HttpContext.TraceIdentifier });
}
