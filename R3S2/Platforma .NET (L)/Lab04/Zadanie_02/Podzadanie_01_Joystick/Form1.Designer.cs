namespace JoystickDemo;

partial class Form1
{
    private System.ComponentModel.IContainer components = null;

    private PictureBox picCanvas = null!;
    private Label lblStatus = null!;
    private Button btnReconnect = null!;
    private Panel pnlSimulation = null!;
    private Label lblSimTitle = null!;
    private TrackBar trackX = null!;
    private TrackBar trackY = null!;
    private TrackBar trackZ = null!;
    private TrackBar trackRZ = null!;
    private TrackBar trackPov = null!;
    private Label lblX = null!;
    private Label lblY = null!;
    private Label lblZ = null!;
    private Label lblRZ = null!;
    private Label lblPov = null!;
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
            Size = new Size(720, 440),
            BackColor = Color.White,
            BorderStyle = BorderStyle.FixedSingle
        };

        lblStatus = new Label
        {
            Location = new Point(12, 460),
            Size = new Size(600, 24),
            Font = new Font("Segoe UI", 10f, FontStyle.Bold)
        };

        btnReconnect = new Button
        {
            Text = "Polacz ponownie",
            Location = new Point(620, 455),
            Size = new Size(112, 30)
        };

        pnlSimulation = new Panel
        {
            Location = new Point(12, 495),
            Size = new Size(720, 200),
            BorderStyle = BorderStyle.FixedSingle,
            BackColor = Color.FromArgb(250, 250, 250)
        };

        lblSimTitle = new Label
        {
            Text = "Symulacja (uzyj gdy brak fizycznego urzadzenia):",
            Location = new Point(10, 6),
            AutoSize = true,
            Font = new Font("Segoe UI", 9f, FontStyle.Italic)
        };

        trackX = CreateTrack(-100, 100, 0, 40);
        lblX = new Label { Text = "Os X", Location = new Point(10, 26), AutoSize = true };
        trackY = CreateTrack(-100, 100, 0, 85);
        lblY = new Label { Text = "Os Y", Location = new Point(10, 71), AutoSize = true };
        trackZ = CreateTrack(-100, 100, -100, 130);
        lblZ = new Label { Text = "Os Z (throttle)", Location = new Point(10, 116), AutoSize = true };
        trackRZ = CreateTrack(-100, 100, 0, 175);
        lblRZ = new Label { Text = "Os RZ", Location = new Point(10, 161), AutoSize = true };

        trackPov = new TrackBar
        {
            Minimum = -1,
            Maximum = 359,
            Value = -1,
            TickFrequency = 45,
            Location = new Point(420, 40),
            Size = new Size(280, 45),
            TickStyle = TickStyle.BottomRight
        };
        lblPov = new Label { Text = "POV: wylaczony", Location = new Point(420, 20), AutoSize = true };

        pnlSimulation.Controls.AddRange(new Control[]
        {
            lblSimTitle,
            lblX, trackX, lblY, trackY, lblZ, trackZ, lblRZ, trackRZ,
            lblPov, trackPov
        });

        btnsButtons = new CheckBox[8];
        for (int i = 0; i < btnsButtons.Length; i++)
        {
            btnsButtons[i] = new CheckBox
            {
                Appearance = Appearance.Button,
                Text = $"B{i + 1}",
                Size = new Size(40, 30),
                Location = new Point(420 + (i % 4) * 45, 90 + (i / 4) * 35),
                TextAlign = ContentAlignment.MiddleCenter
            };
            pnlSimulation.Controls.Add(btnsButtons[i]);
        }

        AutoScaleMode = AutoScaleMode.Font;
        ClientSize = new Size(744, 710);
        Controls.AddRange(new Control[] { picCanvas, lblStatus, btnReconnect, pnlSimulation });
        FormBorderStyle = FormBorderStyle.FixedSingle;
        MaximizeBox = false;
        StartPosition = FormStartPosition.CenterScreen;
        Text = "Joystick - stan urzadzenia";
    }

    private TrackBar CreateTrack(int min, int max, int value, int top)
    {
        return new TrackBar
        {
            Minimum = min,
            Maximum = max,
            Value = value,
            TickFrequency = 25,
            Location = new Point(80, top),
            Size = new Size(320, 45),
            TickStyle = TickStyle.BottomRight
        };
    }
}
