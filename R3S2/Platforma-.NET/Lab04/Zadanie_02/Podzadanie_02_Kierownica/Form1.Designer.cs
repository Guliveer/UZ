namespace KierownicaDemo;

partial class Form1
{
    private System.ComponentModel.IContainer components = null;

    private PictureBox picCanvas = null!;
    private Label lblStatus = null!;
    private Button btnReconnect = null!;
    private Panel pnlSimulation = null!;
    private Label lblSimTitle = null!;
    private Label lblSteering = null!;
    private TrackBar trackSteering = null!;
    private Label lblThrottle = null!;
    private TrackBar trackThrottle = null!;
    private Label lblBrake = null!;
    private TrackBar trackBrake = null!;
    private Label lblClutch = null!;
    private TrackBar trackClutch = null!;
    private Label lblGear = null!;
    private ComboBox cbGear = null!;
    internal CheckBox[] btnsButtons = null!;

    protected override void Dispose(bool disposing)
    {
        if (disposing && (components != null))
            components.Dispose();
        base.Dispose(disposing);
    }

    private void InitializeComponent()
    {
        components = new System.ComponentModel.Container();

        picCanvas = new PictureBox
        {
            Location = new Point(12, 12),
            Size = new Size(960, 420),
            BackColor = Color.White,
            BorderStyle = BorderStyle.FixedSingle
        };

        lblStatus = new Label
        {
            Location = new Point(12, 440),
            Size = new Size(840, 24),
            Font = new Font("Segoe UI", 10f, FontStyle.Bold)
        };

        btnReconnect = new Button
        {
            Text = "Polacz ponownie",
            Location = new Point(860, 435),
            Size = new Size(112, 30)
        };

        pnlSimulation = new Panel
        {
            Location = new Point(12, 475),
            Size = new Size(960, 200),
            BorderStyle = BorderStyle.FixedSingle,
            BackColor = Color.FromArgb(250, 250, 250)
        };

        lblSimTitle = new Label
        {
            Text = "Symulacja (uzyj gdy brak kierownicy):",
            Location = new Point(10, 6),
            AutoSize = true,
            Font = new Font("Segoe UI", 9f, FontStyle.Italic)
        };

        lblSteering = new Label { Text = "Skret (X)", Location = new Point(10, 26), AutoSize = true };
        trackSteering = CreateTrack(-100, 100, 0, 40);
        lblThrottle = new Label { Text = "Gaz", Location = new Point(10, 71), AutoSize = true };
        trackThrottle = CreateTrack(0, 200, 0, 85);
        lblBrake = new Label { Text = "Hamulec", Location = new Point(10, 116), AutoSize = true };
        trackBrake = CreateTrack(0, 200, 0, 130);
        lblClutch = new Label { Text = "Sprzeglo", Location = new Point(10, 161), AutoSize = true };
        trackClutch = CreateTrack(0, 200, 0, 175);

        lblGear = new Label { Text = "Bieg:", Location = new Point(420, 24), AutoSize = true };
        cbGear = new ComboBox
        {
            Location = new Point(460, 20),
            Size = new Size(150, 24),
            DropDownStyle = ComboBoxStyle.DropDownList
        };

        pnlSimulation.Controls.AddRange(new Control[]
        {
            lblSimTitle,
            lblSteering, trackSteering, lblThrottle, trackThrottle,
            lblBrake, trackBrake, lblClutch, trackClutch,
            lblGear, cbGear
        });

        btnsButtons = new CheckBox[8];
        for (int i = 0; i < btnsButtons.Length; i++)
        {
            btnsButtons[i] = new CheckBox
            {
                Appearance = Appearance.Button,
                Text = $"B{i + 1}",
                Size = new Size(40, 30),
                Location = new Point(620 + (i % 4) * 45, 60 + (i / 4) * 35),
                TextAlign = ContentAlignment.MiddleCenter
            };
            pnlSimulation.Controls.Add(btnsButtons[i]);
        }

        AutoScaleMode = AutoScaleMode.Font;
        ClientSize = new Size(984, 690);
        Controls.AddRange(new Control[] { picCanvas, lblStatus, btnReconnect, pnlSimulation });
        FormBorderStyle = FormBorderStyle.FixedSingle;
        MaximizeBox = false;
        StartPosition = FormStartPosition.CenterScreen;
        Text = "Kierownica - stan urzadzenia";
    }

    private TrackBar CreateTrack(int min, int max, int value, int top)
    {
        return new TrackBar
        {
            Minimum = min,
            Maximum = max,
            Value = value,
            TickFrequency = 25,
            Location = new Point(90, top),
            Size = new Size(310, 45),
            TickStyle = TickStyle.BottomRight
        };
    }
}
