using System.ComponentModel;
using Microsoft.SemanticKernel;

public sealed class SmartHomePlugin
{
    private readonly Dictionary<string, bool> _lights = new()
    {
        ["salon"] = false,
        ["kuchnia"] = false,
        ["sypialnia"] = false,
        ["łazienka"] = false
    };

    private double _thermostatTarget = 21.0;
    private bool _alarmEnabled = false;

    [KernelFunction, Description("Zwraca aktualną godzinę i datę.")]
    public string PodajCzas() =>
        $"Teraz jest {DateTime.Now:HH:mm}, {DateTime.Now:dddd d MMMM yyyy}.";

    [KernelFunction, Description("Włącza światło w podanym pomieszczeniu. Jeśli pomieszczenie nie zostanie podane, włącza wszystkie.")]
    public string WlaczSwiatla(
        [Description("Nazwa pomieszczenia: salon, kuchnia, sypialnia, łazienka. Puste = wszystkie.")] string pomieszczenie = "")
    {
        if (string.IsNullOrWhiteSpace(pomieszczenie))
        {
            foreach (var key in _lights.Keys.ToList()) _lights[key] = true;
            return "Włączono światła we wszystkich pomieszczeniach.";
        }
        var room = pomieszczenie.ToLower();
        if (!_lights.ContainsKey(room)) return $"Nieznane pomieszczenie: {pomieszczenie}";
        _lights[room] = true;
        return $"Włączono światło w: {room}.";
    }

    [KernelFunction, Description("Wyłącza światło w podanym pomieszczeniu. Jeśli pomieszczenie nie zostanie podane, wyłącza wszystkie.")]
    public string WylaczSwiatla(
        [Description("Nazwa pomieszczenia. Puste = wszystkie.")] string pomieszczenie = "")
    {
        if (string.IsNullOrWhiteSpace(pomieszczenie))
        {
            foreach (var key in _lights.Keys.ToList()) _lights[key] = false;
            return "Wyłączono światła we wszystkich pomieszczeniach.";
        }
        var room = pomieszczenie.ToLower();
        if (!_lights.ContainsKey(room)) return $"Nieznane pomieszczenie: {pomieszczenie}";
        _lights[room] = false;
        return $"Wyłączono światło w: {room}.";
    }

    [KernelFunction, Description("Podaje stan świateł we wszystkich pomieszczeniach.")]
    public string StanSwiatel()
    {
        var lines = _lights.Select(kv => $"  {kv.Key}: {(kv.Value ? "włączone" : "wyłączone")}");
        return "Stan świateł:\n" + string.Join("\n", lines);
    }

    [KernelFunction, Description("Zwraca aktualną temperaturę w domu (symulowana).")]
    public string PodajTemperature()
    {
        var current = _thermostatTarget - 0.5 + Random.Shared.NextDouble();
        return $"Aktualna temperatura: {current:F1}°C. Ustawiona na termostacie: {_thermostatTarget}°C.";
    }

    [KernelFunction, Description("Ustawia docelową temperaturę na termostacie.")]
    public string UstawTemperature(
        [Description("Temperatura w stopniach Celsjusza")] double temperatura)
    {
        _thermostatTarget = temperatura;
        return $"Termostat ustawiony na {temperatura}°C.";
    }

    [KernelFunction, Description("Włącza lub wyłącza alarm.")]
    public string ZarzadzajAlarmem(
        [Description("true = włącz alarm, false = wyłącz alarm")] bool wlacz)
    {
        _alarmEnabled = wlacz;
        return _alarmEnabled ? "Alarm został włączony." : "Alarm został wyłączony.";
    }

    [KernelFunction, Description("Podaje ogólny status systemu domowej automatyki.")]
    public string StatusDomu()
    {
        var swiatla = _lights.Count(kv => kv.Value);
        return $"Status domu: {swiatla}/{_lights.Count} świateł włączonych, " +
               $"termostat: {_thermostatTarget}°C, alarm: {(_alarmEnabled ? "aktywny" : "nieaktywny")}.";
    }
}
