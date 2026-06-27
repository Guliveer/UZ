using SharpDX.DirectInput;

namespace KierownicaDemo;

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

        picCanvas.Paint += (_, e) => WheelVisualizer.Draw(e.Graphics, picCanvas.ClientRectangle, _source.Poll());

        trackSteering.Scroll += (_, _) => _simulated.Steering = trackSteering.Value / 100f;
        trackThrottle.Scroll += (_, _) => _simulated.Throttle = trackThrottle.Value / 100f - 1f;
        trackBrake.Scroll += (_, _) => _simulated.Brake = trackBrake.Value / 100f - 1f;
        trackClutch.Scroll += (_, _) => _simulated.Clutch = trackClutch.Value / 100f - 1f;

        cbGear.Items.AddRange(new object[] { "Neutralny", "1", "2", "3", "4", "5", "6", "Wsteczny" });
        cbGear.SelectedIndex = 0;
        cbGear.SelectedIndexChanged += (_, _) =>
        {
            _simulated.Gear = cbGear.SelectedIndex == 0 ? -1 : cbGear.SelectedIndex - 1;
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
            DeviceType.Driving, DeviceType.Joystick, DeviceType.Gamepad);
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
            ? $"Tryb: kierownica fizyczna — {_source.Name}"
            : "Tryb: SYMULACJA (brak kierownicy) — uzyj suwakow";
        lblStatus.ForeColor = _source is DirectInputSource ? Color.DarkGreen : Color.DarkOrange;
    }

    protected override void OnFormClosed(FormClosedEventArgs e)
    {
        _pollTimer.Stop();
        _source.Dispose();
        base.OnFormClosed(e);
    }
}
