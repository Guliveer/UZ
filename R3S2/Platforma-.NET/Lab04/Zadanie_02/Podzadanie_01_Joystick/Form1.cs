using SharpDX.DirectInput;

namespace JoystickDemo;

public partial class Form1 : Form
{
    private IInputSource _source;
    private readonly SimulatedInputSource _simulated;
    private readonly System.Windows.Forms.Timer _pollTimer;

    public Form1()
    {
        InitializeComponent();

        _simulated = new SimulatedInputSource();
        _source = TryCreateHardwareOrFallback();

        _pollTimer = new System.Windows.Forms.Timer { Interval = 33 };
        _pollTimer.Tick += (_, _) => picCanvas.Invalidate();
        _pollTimer.Start();

        picCanvas.Paint += (_, e) => JoystickVisualizer.Draw(e.Graphics, picCanvas.ClientRectangle, _source.Poll());

        trackX.Scroll += (_, _) => { _simulated.X = trackX.Value / 100f; };
        trackY.Scroll += (_, _) => { _simulated.Y = trackY.Value / 100f; };
        trackZ.Scroll += (_, _) => { _simulated.Throttle = trackZ.Value / 100f; };
        trackRZ.Scroll += (_, _) => { _simulated.Rudder = trackRZ.Value / 100f; };
        trackPov.Scroll += (_, _) =>
        {
            _simulated.Pov = trackPov.Value < 0 ? -1 : trackPov.Value * 100;
            lblPov.Text = _simulated.Pov < 0 ? "POV: wylaczony" : $"POV: {trackPov.Value}°";
        };

        for (int i = 0; i < btnsButtons.Length; i++)
        {
            int idx = i;
            btnsButtons[idx].CheckedChanged += (_, _) => _simulated.Buttons[idx] = btnsButtons[idx].Checked;
        }

        btnReconnect.Click += (_, _) =>
        {
            _source.Dispose();
            _source = TryCreateHardwareOrFallback();
            UpdateStatus();
        };

        UpdateStatus();
    }

    private IInputSource TryCreateHardwareOrFallback()
    {
        var hw = DirectInputSource.TryCreate(
            DeviceType.Joystick, DeviceType.Gamepad, DeviceType.FirstPerson);
        if (hw != null)
        {
            pnlSimulation.Enabled = false;
            return hw;
        }
        pnlSimulation.Enabled = true;
        return _simulated;
    }

    private void UpdateStatus()
    {
        lblStatus.Text = _source is DirectInputSource
            ? $"Tryb: urzadzenie fizyczne — {_source.Name}"
            : "Tryb: SYMULACJA (brak joysticka) — uzyj suwakow";
        lblStatus.ForeColor = _source is DirectInputSource ? Color.DarkGreen : Color.DarkOrange;
    }

    protected override void OnFormClosed(FormClosedEventArgs e)
    {
        _pollTimer.Stop();
        _source.Dispose();
        base.OnFormClosed(e);
    }
}
