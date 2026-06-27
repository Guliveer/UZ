namespace WalidatorXML;

partial class Form1
{
    private System.ComponentModel.IContainer components = null;

    private Label lblXml = null!;
    private TextBox txtXml = null!;
    private Button btnChooseXml = null!;
    private Label lblSchema = null!;
    private TextBox txtSchema = null!;
    private Button btnChooseSchema = null!;
    private Button btnValidate = null!;
    private Button btnOpenSamples = null!;
    private Label lblReport = null!;
    private TextBox txtReport = null!;
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

        lblXml = new Label { Text = "Plik XML:", Location = new Point(12, 18), AutoSize = true };
        txtXml = new TextBox { Location = new Point(100, 15), Size = new Size(560, 24), ReadOnly = true };
        btnChooseXml = new Button { Text = "Wybierz...", Location = new Point(670, 14), Size = new Size(100, 26) };

        lblSchema = new Label { Text = "Schemat:", Location = new Point(12, 48), AutoSize = true };
        txtSchema = new TextBox { Location = new Point(100, 45), Size = new Size(560, 24), ReadOnly = true };
        btnChooseSchema = new Button { Text = "Wybierz XSD/DTD", Location = new Point(670, 44), Size = new Size(100, 26) };

        btnValidate = new Button
        {
            Text = "Waliduj",
            Location = new Point(12, 80),
            Size = new Size(200, 34),
            BackColor = Color.LightSteelBlue,
            Font = new Font("Segoe UI", 10f, FontStyle.Bold)
        };

        btnOpenSamples = new Button { Text = "Otworz katalog Samples", Location = new Point(220, 80), Size = new Size(200, 34) };

        lblReport = new Label { Text = "Raport:", Location = new Point(12, 125), AutoSize = true };
        txtReport = new TextBox
        {
            Location = new Point(12, 145),
            Size = new Size(760, 280),
            Multiline = true,
            ScrollBars = ScrollBars.Both,
            Font = new Font("Consolas", 9.5f),
            ReadOnly = true,
            BackColor = Color.White
        };

        lblStatus = new Label
        {
            Location = new Point(12, 435),
            Size = new Size(760, 24),
            Font = new Font("Segoe UI", 10f, FontStyle.Bold)
        };

        AutoScaleMode = AutoScaleMode.Font;
        ClientSize = new Size(790, 475);
        Controls.AddRange(new Control[]
        {
            lblXml, txtXml, btnChooseXml,
            lblSchema, txtSchema, btnChooseSchema,
            btnValidate, btnOpenSamples,
            lblReport, txtReport, lblStatus
        });
        FormBorderStyle = FormBorderStyle.FixedSingle;
        MaximizeBox = false;
        StartPosition = FormStartPosition.CenterScreen;
        Text = "Walidator XML (XSD / DTD)";
    }
}
