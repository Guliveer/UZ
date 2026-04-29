namespace ClockDemo;

partial class Form1
{
    private System.ComponentModel.IContainer components = null;

    private GroupBox grpMode = null!;
    private RadioButton rbAnalog = null!;
    private RadioButton rbDigital = null!;
    private CheckBox cbShowSeconds = null!;
    private Button btnFaceColor = null!;
    private Button btnHourColor = null!;
    private Button btnMinuteColor = null!;
    private Button btnSecondColor = null!;
    private Button btnDigitalColor = null!;

    protected override void Dispose(bool disposing)
    {
        if (disposing && (components != null))
            components.Dispose();
        base.Dispose(disposing);
    }

    private void InitializeComponent()
    {
        components = new System.ComponentModel.Container();

        grpMode = new GroupBox
        {
            Text = "Tryb",
            Location = new Point(370, 12),
            Size = new Size(200, 90)
        };
        rbAnalog = new RadioButton { Text = "Analogowy", Location = new Point(15, 25), AutoSize = true };
        rbDigital = new RadioButton { Text = "Cyfrowy", Location = new Point(15, 55), AutoSize = true };
        grpMode.Controls.AddRange(new Control[] { rbAnalog, rbDigital });

        cbShowSeconds = new CheckBox
        {
            Text = "Pokaz sekundy",
            Location = new Point(370, 115),
            AutoSize = true
        };

        btnFaceColor = new Button { Text = "Kolor tarczy", Location = new Point(370, 150), Size = new Size(200, 30) };
        btnHourColor = new Button { Text = "Kolor wskazowki godzinowej", Location = new Point(370, 185), Size = new Size(200, 30) };
        btnMinuteColor = new Button { Text = "Kolor wskazowki minutowej", Location = new Point(370, 220), Size = new Size(200, 30) };
        btnSecondColor = new Button { Text = "Kolor wskazowki sekundowej", Location = new Point(370, 255), Size = new Size(200, 30) };
        btnDigitalColor = new Button { Text = "Kolor cyfr (tryb cyfrowy)", Location = new Point(370, 290), Size = new Size(200, 30) };

        AutoScaleMode = AutoScaleMode.Font;
        ClientSize = new Size(590, 365);
        Controls.AddRange(new Control[]
        {
            grpMode, cbShowSeconds,
            btnFaceColor, btnHourColor, btnMinuteColor, btnSecondColor, btnDigitalColor
        });
        FormBorderStyle = FormBorderStyle.FixedSingle;
        MaximizeBox = false;
        StartPosition = FormStartPosition.CenterScreen;
        Text = "Zegar - demo";
    }
}
