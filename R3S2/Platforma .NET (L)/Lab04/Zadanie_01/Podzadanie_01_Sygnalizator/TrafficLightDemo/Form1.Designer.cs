namespace TrafficLightDemo;

partial class Form1
{
    private System.ComponentModel.IContainer components = null;

    private Panel panelLight = null!;
    private GroupBox grpState = null!;
    private RadioButton rbOff = null!;
    private RadioButton rbRed = null!;
    private RadioButton rbRedYellow = null!;
    private RadioButton rbYellow = null!;
    private RadioButton rbGreen = null!;
    private Label lblLampCount = null!;
    private ComboBox cbLampCount = null!;
    private Label lblOrientation = null!;
    private ComboBox cbOrientation = null!;
    private CheckBox btnAutoCycle = null!;
    private Label lblStatusCaption = null!;
    private Label lblStatus = null!;

    protected override void Dispose(bool disposing)
    {
        if (disposing && (components != null))
            components.Dispose();
        base.Dispose(disposing);
    }

    private void InitializeComponent()
    {
        components = new System.ComponentModel.Container();

        panelLight = new Panel
        {
            Location = new Point(12, 12),
            Size = new Size(260, 360),
            BorderStyle = BorderStyle.FixedSingle,
            BackColor = Color.White
        };

        grpState = new GroupBox
        {
            Text = "Stan",
            Location = new Point(290, 12),
            Size = new Size(200, 170)
        };

        rbOff = new RadioButton { Text = "Off", Location = new Point(15, 25), AutoSize = true };
        rbRed = new RadioButton { Text = "Red", Location = new Point(15, 50), AutoSize = true, Checked = true };
        rbRedYellow = new RadioButton { Text = "Red + Yellow", Location = new Point(15, 75), AutoSize = true };
        rbYellow = new RadioButton { Text = "Yellow", Location = new Point(15, 100), AutoSize = true };
        rbGreen = new RadioButton { Text = "Green", Location = new Point(15, 125), AutoSize = true };
        grpState.Controls.AddRange(new Control[] { rbOff, rbRed, rbRedYellow, rbYellow, rbGreen });

        lblLampCount = new Label { Text = "Liczba lamp:", Location = new Point(290, 195), AutoSize = true };
        cbLampCount = new ComboBox
        {
            Location = new Point(290, 215),
            Size = new Size(200, 24),
            DropDownStyle = ComboBoxStyle.DropDownList
        };

        lblOrientation = new Label { Text = "Orientacja:", Location = new Point(290, 250), AutoSize = true };
        cbOrientation = new ComboBox
        {
            Location = new Point(290, 270),
            Size = new Size(200, 24),
            DropDownStyle = ComboBoxStyle.DropDownList
        };

        btnAutoCycle = new CheckBox
        {
            Text = "Auto cykl (1s)",
            Appearance = Appearance.Button,
            Location = new Point(290, 305),
            Size = new Size(200, 32),
            TextAlign = ContentAlignment.MiddleCenter
        };

        lblStatusCaption = new Label { Text = "Biezacy stan:", Location = new Point(290, 345), AutoSize = true };
        lblStatus = new Label
        {
            Location = new Point(380, 345),
            AutoSize = true,
            ForeColor = Color.DarkBlue,
            Font = new Font(DefaultFont, FontStyle.Bold)
        };

        AutoScaleMode = AutoScaleMode.Font;
        ClientSize = new Size(510, 385);
        Controls.AddRange(new Control[]
        {
            panelLight, grpState,
            lblLampCount, cbLampCount,
            lblOrientation, cbOrientation,
            btnAutoCycle,
            lblStatusCaption, lblStatus
        });
        FormBorderStyle = FormBorderStyle.FixedSingle;
        MaximizeBox = false;
        StartPosition = FormStartPosition.CenterScreen;
        Text = "Sygnalizator - demo";
    }
}
