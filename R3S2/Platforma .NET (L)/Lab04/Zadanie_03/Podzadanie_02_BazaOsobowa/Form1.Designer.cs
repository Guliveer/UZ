namespace BazaOsobowa;

partial class Form1
{
    private System.ComponentModel.IContainer components = null;

    private DataGridView dataGrid = null!;
    private Button btnDodaj = null!;
    private Button btnUsun = null!;
    private Button btnZapisz = null!;
    private Button btnWczytaj = null!;
    private Button btnPokazXsd = null!;
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

        dataGrid = new DataGridView
        {
            Location = new Point(12, 12),
            Size = new Size(900, 420),
            AllowUserToResizeRows = false,
            SelectionMode = DataGridViewSelectionMode.FullRowSelect,
            MultiSelect = false,
            AutoSizeColumnsMode = DataGridViewAutoSizeColumnsMode.Fill
        };

        btnDodaj = new Button { Text = "Dodaj", Location = new Point(12, 445), Size = new Size(100, 34) };
        btnUsun = new Button { Text = "Usun", Location = new Point(120, 445), Size = new Size(100, 34) };
        btnZapisz = new Button { Text = "Zapisz (+XSD)", Location = new Point(230, 445), Size = new Size(130, 34) };
        btnWczytaj = new Button { Text = "Wczytaj", Location = new Point(370, 445), Size = new Size(100, 34) };
        btnPokazXsd = new Button { Text = "Pokaz osoby.xsd", Location = new Point(480, 445), Size = new Size(130, 34) };

        lblStatus = new Label
        {
            Location = new Point(12, 490),
            Size = new Size(900, 20),
            Font = new Font("Segoe UI", 9f, FontStyle.Italic)
        };

        AutoScaleMode = AutoScaleMode.Font;
        ClientSize = new Size(930, 520);
        Controls.AddRange(new Control[]
        {
            dataGrid, btnDodaj, btnUsun, btnZapisz, btnWczytaj, btnPokazXsd, lblStatus
        });
        StartPosition = FormStartPosition.CenterScreen;
        Text = "Baza osobowa - XML + XSD";
    }
}
