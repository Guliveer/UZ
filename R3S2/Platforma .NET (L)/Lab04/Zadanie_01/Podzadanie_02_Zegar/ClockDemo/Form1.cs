using ClockControl;

namespace ClockDemo;

public partial class Form1 : Form
{
    private readonly Clock _clock;

    public Form1()
    {
        InitializeComponent();

        _clock = new Clock
        {
            Location = new Point(12, 12),
            Size = new Size(340, 340),
            DisplayMode = ClockDisplayMode.Analog
        };
        Controls.Add(_clock);

        rbAnalog.Checked = true;
        rbAnalog.CheckedChanged += (_, _) => { if (rbAnalog.Checked) _clock.DisplayMode = ClockDisplayMode.Analog; };
        rbDigital.CheckedChanged += (_, _) => { if (rbDigital.Checked) _clock.DisplayMode = ClockDisplayMode.Digital; };

        cbShowSeconds.Checked = _clock.ShowSeconds;
        cbShowSeconds.CheckedChanged += (_, _) => _clock.ShowSeconds = cbShowSeconds.Checked;

        btnFaceColor.Click += (_, _) => PickColor(c => _clock.FaceColor = c, _clock.FaceColor);
        btnHourColor.Click += (_, _) => PickColor(c => _clock.HourHandColor = c, _clock.HourHandColor);
        btnMinuteColor.Click += (_, _) => PickColor(c => _clock.MinuteHandColor = c, _clock.MinuteHandColor);
        btnSecondColor.Click += (_, _) => PickColor(c => _clock.SecondHandColor = c, _clock.SecondHandColor);
        btnDigitalColor.Click += (_, _) => PickColor(c => _clock.DigitalColor = c, _clock.DigitalColor);
    }

    private void PickColor(Action<Color> apply, Color current)
    {
        using var dlg = new ColorDialog { Color = current, FullOpen = true };
        if (dlg.ShowDialog(this) == DialogResult.OK)
            apply(dlg.Color);
    }
}
