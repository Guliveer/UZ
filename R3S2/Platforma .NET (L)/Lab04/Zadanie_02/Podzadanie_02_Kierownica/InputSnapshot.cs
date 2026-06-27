namespace KierownicaDemo;

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
