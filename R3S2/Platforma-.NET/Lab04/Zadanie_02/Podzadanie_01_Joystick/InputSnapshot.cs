namespace JoystickDemo;

/// <summary>
/// Znormalizowany stan urzadzenia wejscia. Osie w zakresie [-1, 1] (0 = neutral),
/// throttle/brake/clutch w zakresie [0, 1]. Nieobecne osie maja wartosc null.
/// </summary>
public sealed class InputSnapshot
{
    public float X { get; init; }
    public float Y { get; init; }
    public float? Z { get; init; }
    public float? RotationX { get; init; }
    public float? RotationY { get; init; }
    public float? RotationZ { get; init; }
    public int Pov { get; init; } = -1;
    public bool[] Buttons { get; init; } = Array.Empty<bool>();
    public string DeviceName { get; init; } = "";
    public bool IsConnected { get; init; }
}
