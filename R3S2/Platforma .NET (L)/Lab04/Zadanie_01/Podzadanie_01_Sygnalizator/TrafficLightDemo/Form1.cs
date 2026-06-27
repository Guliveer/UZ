using TrafficLightControl;

namespace TrafficLightDemo;

public partial class Form1 : Form
{
    private readonly TrafficLight _light;
    private readonly System.Windows.Forms.Timer _cycleTimer;
    private int _cycleIndex;

    private static readonly TrafficLightState[] CycleThree =
    {
        TrafficLightState.Red,
        TrafficLightState.RedYellow,
        TrafficLightState.Green,
        TrafficLightState.Yellow
    };

    public Form1()
    {
        InitializeComponent();

        _light = new TrafficLight
        {
            Location = new Point(20, 20),
            LampCount = TrafficLightLampCount.Three,
            State = TrafficLightState.Red,
            Orientation = TrafficLightOrientation.Vertical,
            HousingColor = Color.FromArgb(30, 30, 30)
        };
        _light.Size = _light.GetPreferredSize(Size.Empty);
        panelLight.Controls.Add(_light);

        cbLampCount.Items.AddRange(new object[]
        {
            TrafficLightLampCount.One,
            TrafficLightLampCount.Two,
            TrafficLightLampCount.Three
        });
        cbLampCount.SelectedItem = _light.LampCount;
        cbLampCount.SelectedIndexChanged += (_, _) =>
        {
            _light.LampCount = (TrafficLightLampCount)cbLampCount.SelectedItem!;
            _light.Size = _light.GetPreferredSize(Size.Empty);
        };

        cbOrientation.Items.AddRange(new object[]
        {
            TrafficLightOrientation.Vertical,
            TrafficLightOrientation.Horizontal
        });
        cbOrientation.SelectedItem = _light.Orientation;
        cbOrientation.SelectedIndexChanged += (_, _) =>
        {
            _light.Orientation = (TrafficLightOrientation)cbOrientation.SelectedItem!;
            _light.Size = _light.GetPreferredSize(Size.Empty);
        };

        rbOff.CheckedChanged += (_, _) => { if (rbOff.Checked) _light.State = TrafficLightState.Off; };
        rbRed.CheckedChanged += (_, _) => { if (rbRed.Checked) _light.State = TrafficLightState.Red; };
        rbRedYellow.CheckedChanged += (_, _) => { if (rbRedYellow.Checked) _light.State = TrafficLightState.RedYellow; };
        rbYellow.CheckedChanged += (_, _) => { if (rbYellow.Checked) _light.State = TrafficLightState.Yellow; };
        rbGreen.CheckedChanged += (_, _) => { if (rbGreen.Checked) _light.State = TrafficLightState.Green; };

        _cycleTimer = new System.Windows.Forms.Timer { Interval = 1000 };
        _cycleTimer.Tick += CycleTick;
        btnAutoCycle.CheckedChanged += (_, _) =>
        {
            _cycleTimer.Enabled = btnAutoCycle.Checked;
            SetRadioButtonsEnabled(!btnAutoCycle.Checked);
        };

        lblStatus.DataBindings.Add(nameof(Label.Text), _light, nameof(TrafficLight.State));
    }

    private void CycleTick(object? sender, EventArgs e)
    {
        _cycleIndex = (_cycleIndex + 1) % CycleThree.Length;
        _light.State = CycleThree[_cycleIndex];
        SyncRadioButtonsToState();
    }

    private void SyncRadioButtonsToState()
    {
        rbOff.Checked = _light.State == TrafficLightState.Off;
        rbRed.Checked = _light.State == TrafficLightState.Red;
        rbRedYellow.Checked = _light.State == TrafficLightState.RedYellow;
        rbYellow.Checked = _light.State == TrafficLightState.Yellow;
        rbGreen.Checked = _light.State == TrafficLightState.Green;
    }

    private void SetRadioButtonsEnabled(bool enabled)
    {
        rbOff.Enabled = enabled;
        rbRed.Enabled = enabled;
        rbRedYellow.Enabled = enabled;
        rbYellow.Enabled = enabled;
        rbGreen.Enabled = enabled;
    }
}
