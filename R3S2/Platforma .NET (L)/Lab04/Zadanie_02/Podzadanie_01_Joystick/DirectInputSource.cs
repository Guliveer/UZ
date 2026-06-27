using SharpDX.DirectInput;

namespace JoystickDemo;

public sealed class DirectInputSource : IInputSource
{
    private readonly DirectInput _directInput = new();
    private Joystick? _joystick;
    private string _name = "";

    public string Name => _name;
    public bool IsAvailable => _joystick != null;

    /// <summary>
    /// Zwraca nowy DirectInputSource, jesli uda sie podlaczyc do pierwszego pasujacego urzadzenia.
    /// </summary>
    public static DirectInputSource? TryCreate(params DeviceType[] preferredTypes)
    {
        var src = new DirectInputSource();
        if (src.TryAcquire(preferredTypes))
            return src;
        src.Dispose();
        return null;
    }

    private bool TryAcquire(DeviceType[] preferredTypes)
    {
        var devices = _directInput
            .GetDevices(DeviceClass.GameControl, DeviceEnumerationFlags.AttachedOnly)
            .ToList();

        if (devices.Count == 0) return false;

        DeviceInstance? match = null;
        foreach (var type in preferredTypes)
        {
            match = devices.FirstOrDefault(d => d.Type == type);
            if (match != null) break;
        }
        match ??= devices[0];

        try
        {
            _joystick = new Joystick(_directInput, match.InstanceGuid);
            _joystick.Properties.BufferSize = 128;
            _joystick.Acquire();
            _name = match.InstanceName;
            return true;
        }
        catch
        {
            _joystick?.Dispose();
            _joystick = null;
            return false;
        }
    }

    public InputSnapshot Poll()
    {
        if (_joystick == null)
            return new InputSnapshot { IsConnected = false, DeviceName = "" };

        try
        {
            _joystick.Poll();
            var state = _joystick.GetCurrentState();

            return new InputSnapshot
            {
                DeviceName = _name,
                IsConnected = true,
                X = Normalize(state.X),
                Y = Normalize(state.Y),
                Z = Normalize(state.Z),
                RotationX = Normalize(state.RotationX),
                RotationY = Normalize(state.RotationY),
                RotationZ = Normalize(state.RotationZ),
                Pov = state.PointOfViewControllers.Length > 0 ? state.PointOfViewControllers[0] : -1,
                Buttons = state.Buttons.Take(32).ToArray()
            };
        }
        catch
        {
            return new InputSnapshot { IsConnected = false, DeviceName = _name };
        }
    }

    private static float Normalize(int raw)
    {
        // DirectInput domyslnie zwraca [0, 65535]; mapujemy na [-1, 1]
        return (raw - 32768f) / 32768f;
    }

    public void Dispose()
    {
        _joystick?.Unacquire();
        _joystick?.Dispose();
        _directInput.Dispose();
    }
}
