namespace KierownicaDemo;

public sealed class SimulatedInputSource : IInputSource
{
    public string Name => "Symulacja (brak fizycznego urzadzenia)";
    public bool IsAvailable => true;

    public float Steering { get; set; }
    public float Throttle { get; set; }
    public float Brake { get; set; }
    public float Clutch { get; set; }
    public int Gear { get; set; } = -1;
    public bool[] Buttons { get; set; } = new bool[8];

    public InputSnapshot Poll() => new()
    {
        DeviceName = Name,
        IsConnected = true,
        X = Steering,
        Y = Throttle,
        RotationZ = Brake,
        Z = Clutch,
        Pov = Gear < 0 ? -1 : Gear * 100,
        Buttons = (bool[])Buttons.Clone()
    };

    public void Dispose() { }
}
