namespace JoystickDemo;

/// <summary>
/// Zrodlo symulowane: wartosci osi i przyciskow ustawiane z zewnatrz (np. przez suwaki UI).
/// Pozwala zademonstrowac zadanie bez fizycznego urzadzenia.
/// </summary>
public sealed class SimulatedInputSource : IInputSource
{
    public string Name => "Symulacja (brak fizycznego urzadzenia)";
    public bool IsAvailable => true;

    public float X { get; set; }
    public float Y { get; set; }
    public float Throttle { get; set; }
    public float Rudder { get; set; }
    public int Pov { get; set; } = -1;
    public bool[] Buttons { get; set; } = new bool[8];

    public InputSnapshot Poll() => new()
    {
        DeviceName = Name,
        IsConnected = true,
        X = X,
        Y = Y,
        Z = Throttle,
        RotationZ = Rudder,
        Pov = Pov,
        Buttons = (bool[])Buttons.Clone()
    };

    public void Dispose() { }
}
