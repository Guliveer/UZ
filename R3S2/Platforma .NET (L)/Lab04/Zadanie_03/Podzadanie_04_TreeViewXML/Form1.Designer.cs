namespace TreeViewXML;

partial class Form1
{
    private System.ComponentModel.IContainer components = null;

    private MenuStrip menu = null!;
    private ToolStripMenuItem miFile = null!;
    private ToolStripMenuItem miOpen = null!;
    private ToolStripSeparator miSep = null!;
    private ToolStripMenuItem miExit = null!;

    private Button btnOpen = null!;
    private Button btnExpandAll = null!;
    private Button btnCollapseAll = null!;
    private TreeView treeView = null!;
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

        menu = new MenuStrip();
        miFile = new ToolStripMenuItem("&Plik");
        miOpen = new ToolStripMenuItem("&Otworz...", null, (_, _) => btnOpen.PerformClick()) { ShortcutKeys = Keys.Control | Keys.O };
        miSep = new ToolStripSeparator();
        miExit = new ToolStripMenuItem("&Zakoncz", null, (_, _) => Close());
        miFile.DropDownItems.AddRange(new ToolStripItem[] { miOpen, miSep, miExit });
        menu.Items.Add(miFile);

        btnOpen = new Button { Text = "Otworz XML", Location = new Point(12, 35), Size = new Size(140, 30) };
        btnExpandAll = new Button { Text = "Rozwin wszystko", Location = new Point(160, 35), Size = new Size(140, 30) };
        btnCollapseAll = new Button { Text = "Zwin wszystko", Location = new Point(308, 35), Size = new Size(140, 30) };

        treeView = new TreeView
        {
            Location = new Point(12, 75),
            Size = new Size(760, 390),
            Font = new Font("Consolas", 10f),
            ShowLines = true,
            ShowRootLines = true,
            HideSelection = false
        };

        lblStatus = new Label
        {
            Location = new Point(12, 475),
            Size = new Size(760, 20),
            Font = new Font("Segoe UI", 9f, FontStyle.Italic),
            Text = "Przeciagnij plik .xml na okno lub kliknij 'Otworz XML'."
        };

        MainMenuStrip = menu;
        Controls.Add(menu);

        AutoScaleMode = AutoScaleMode.Font;
        ClientSize = new Size(790, 505);
        Controls.AddRange(new Control[] { btnOpen, btnExpandAll, btnCollapseAll, treeView, lblStatus });
        StartPosition = FormStartPosition.CenterScreen;
        Text = "TreeView XML - podglad drzewa";
    }
}
