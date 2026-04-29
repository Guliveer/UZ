namespace Serializacja;

partial class Form1
{
    private System.ComponentModel.IContainer components = null;

    private Label lblId = null!;
    private NumericUpDown numId = null!;
    private Label lblImie = null!;
    private TextBox txtImie = null!;
    private Label lblNazwisko = null!;
    private TextBox txtNazwisko = null!;
    private Label lblEmail = null!;
    private TextBox txtEmail = null!;
    private Label lblData = null!;
    private DateTimePicker dtpData = null!;

    private GroupBox grpAdres = null!;
    private Label lblUlica = null!;
    private TextBox txtUlica = null!;
    private Label lblMiasto = null!;
    private TextBox txtMiasto = null!;
    private Label lblKod = null!;
    private TextBox txtKod = null!;
    private Label lblKraj = null!;
    private TextBox txtKraj = null!;

    private Label lblTelefony = null!;
    private ListView lvTelefony = null!;

    private Button btnPopulate = null!;
    private Button btnSerializeXml = null!;
    private Button btnSerializeDataContract = null!;
    private Button btnDeserialize = null!;

    private Label lblFormat = null!;
    private TextBox txtOutput = null!;

    protected override void Dispose(bool disposing)
    {
        if (disposing && (components != null))
            components.Dispose();
        base.Dispose(disposing);
    }

    private void InitializeComponent()
    {
        components = new System.ComponentModel.Container();

        lblId = new Label { Text = "Id:", Location = new Point(12, 15), AutoSize = true };
        numId = new NumericUpDown { Location = new Point(80, 12), Size = new Size(60, 24), Minimum = 1, Maximum = 99999 };

        lblImie = new Label { Text = "Imie:", Location = new Point(12, 45), AutoSize = true };
        txtImie = new TextBox { Location = new Point(80, 42), Size = new Size(180, 24) };

        lblNazwisko = new Label { Text = "Nazwisko:", Location = new Point(12, 75), AutoSize = true };
        txtNazwisko = new TextBox { Location = new Point(80, 72), Size = new Size(180, 24) };

        lblEmail = new Label { Text = "Email:", Location = new Point(12, 105), AutoSize = true };
        txtEmail = new TextBox { Location = new Point(80, 102), Size = new Size(180, 24) };

        lblData = new Label { Text = "Data ur.:", Location = new Point(12, 135), AutoSize = true };
        dtpData = new DateTimePicker { Location = new Point(80, 132), Size = new Size(180, 24), Format = DateTimePickerFormat.Short };

        grpAdres = new GroupBox { Text = "Adres", Location = new Point(280, 12), Size = new Size(240, 160) };
        lblUlica = new Label { Text = "Ulica:", Location = new Point(8, 25), AutoSize = true };
        txtUlica = new TextBox { Location = new Point(80, 22), Size = new Size(150, 24) };
        lblMiasto = new Label { Text = "Miasto:", Location = new Point(8, 55), AutoSize = true };
        txtMiasto = new TextBox { Location = new Point(80, 52), Size = new Size(150, 24) };
        lblKod = new Label { Text = "Kod:", Location = new Point(8, 85), AutoSize = true };
        txtKod = new TextBox { Location = new Point(80, 82), Size = new Size(150, 24) };
        lblKraj = new Label { Text = "Kraj:", Location = new Point(8, 115), AutoSize = true };
        txtKraj = new TextBox { Location = new Point(80, 112), Size = new Size(150, 24) };
        grpAdres.Controls.AddRange(new Control[] { lblUlica, txtUlica, lblMiasto, txtMiasto, lblKod, txtKod, lblKraj, txtKraj });

        lblTelefony = new Label { Text = "Telefony:", Location = new Point(12, 180), AutoSize = true };
        lvTelefony = new ListView
        {
            Location = new Point(12, 200),
            Size = new Size(508, 100),
            View = View.Details,
            FullRowSelect = true,
            GridLines = true,
            LabelEdit = false
        };
        lvTelefony.Columns.Add("Typ", 120);
        lvTelefony.Columns.Add("Numer", 360);

        btnPopulate = new Button { Text = "Przykladowe dane", Location = new Point(12, 310), Size = new Size(150, 32) };
        btnSerializeXml = new Button { Text = "Serializuj (XmlSerializer)", Location = new Point(168, 310), Size = new Size(180, 32) };
        btnSerializeDataContract = new Button { Text = "Serializuj (DataContract)", Location = new Point(354, 310), Size = new Size(170, 32) };
        btnDeserialize = new Button { Text = "Deserializuj -> formularz", Location = new Point(12, 348), Size = new Size(230, 32) };

        lblFormat = new Label
        {
            Location = new Point(540, 15),
            Size = new Size(340, 24),
            Font = new Font("Segoe UI", 9f, FontStyle.Italic),
            Text = ""
        };
        txtOutput = new TextBox
        {
            Location = new Point(540, 42),
            Size = new Size(340, 338),
            Multiline = true,
            ScrollBars = ScrollBars.Both,
            Font = new Font("Consolas", 9f),
            WordWrap = false
        };

        AutoScaleMode = AutoScaleMode.Font;
        ClientSize = new Size(895, 395);
        Controls.AddRange(new Control[]
        {
            lblId, numId, lblImie, txtImie, lblNazwisko, txtNazwisko,
            lblEmail, txtEmail, lblData, dtpData,
            grpAdres,
            lblTelefony, lvTelefony,
            btnPopulate, btnSerializeXml, btnSerializeDataContract, btnDeserialize,
            lblFormat, txtOutput
        });
        FormBorderStyle = FormBorderStyle.FixedSingle;
        MaximizeBox = false;
        StartPosition = FormStartPosition.CenterScreen;
        Text = "Serializacja Osoba - XmlSerializer / DataContractSerializer";
    }
}
