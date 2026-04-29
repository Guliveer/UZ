namespace ListViewDruk;

partial class Form1
{
    private System.ComponentModel.IContainer components = null;

    private ListView listView = null!;
    private Button btnPreview = null!;
    private Button btnPrint = null!;
    private Button btnPrintSelected = null!;
    private Label lblHint = null!;

    protected override void Dispose(bool disposing)
    {
        if (disposing && (components != null))
            components.Dispose();
        base.Dispose(disposing);
    }

    private void InitializeComponent()
    {
        components = new System.ComponentModel.Container();

        listView = new ListView
        {
            Location = new Point(12, 12),
            Size = new Size(760, 420),
            View = View.Details,
            FullRowSelect = true,
            GridLines = true,
            MultiSelect = true
        };

        btnPreview = new Button { Text = "Podglad wydruku", Location = new Point(12, 445), Size = new Size(170, 34) };
        btnPrint = new Button { Text = "Drukuj wszystkie", Location = new Point(190, 445), Size = new Size(170, 34) };
        btnPrintSelected = new Button { Text = "Drukuj zaznaczone", Location = new Point(368, 445), Size = new Size(170, 34) };

        lblHint = new Label
        {
            Location = new Point(12, 485),
            Size = new Size(760, 20),
            Font = new Font("Segoe UI", 9f, FontStyle.Italic),
            Text = "Tip: Ctrl+klik zaznacza wiele wierszy, Shift+klik zakres."
        };

        AutoScaleMode = AutoScaleMode.Font;
        ClientSize = new Size(790, 515);
        Controls.AddRange(new Control[] { listView, btnPreview, btnPrint, btnPrintSelected, lblHint });
        StartPosition = FormStartPosition.CenterScreen;
        Text = "ListView + wydruk";
    }
}
