using SharpDX.DirectInput;

namespace KierownicaDemo;

public sealed class DirectInputSource : IInputSource
{
    private readonly DirectInput _directInput = new();
    private Joystick? _joystick;
    private string _name = "";

    public string Name => _name;
    public bool IsAvailable => _joystick != null;

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
            return new InputSnapshot { IsConnected = false };

        try
        {
            _joystick.Poll();
            var s = _joystick.GetCurrentState();
            return new InputSnapshot
            {
                DeviceName = _name,
                IsConnected = true,
                X = Normalize(s.X),
                Y = Normalize(s.Y),
                Z = Normalize(s.Z),
                RotationX = Normalize(s.RotationX),
                RotationY = Normalize(s.RotationY),
                RotationZ = Normalize(s.RotationZ),
                Pov = s.PointOfViewControllers.Length > 0 ? s.PointOfViewControllers[0] : -1,
                Buttons = s.Buttons.Take(32).ToArray()
            };
        }
        catch
        {
            return new InputSnapshot { IsConnected = false, DeviceName = _name };
        }
    }

    private static float Normalize(int raw) => (raw - 32768f) / 32768f;

    public void Dispose()
    {
        _joystick?.Unacquire();
        _joystick?.Dispose();
        _directInput.Dispose();
    }
}
