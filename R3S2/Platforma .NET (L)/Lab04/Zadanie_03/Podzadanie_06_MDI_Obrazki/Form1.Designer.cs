namespace MDI_Obrazki;

partial class Form1
{
    private System.ComponentModel.IContainer components = null;

    private MenuStrip menu = null!;
    private ToolStripMenuItem miFile = null!;
    internal ToolStripMenuItem miOpen = null!;
    internal ToolStripMenuItem miCloseAll = null!;
    private ToolStripSeparator miSep = null!;
    internal ToolStripMenuItem miExit = null!;
    private ToolStripMenuItem miWindow = null!;
    internal ToolStripMenuItem miCascade = null!;
    internal ToolStripMenuItem miTileH = null!;
    internal ToolStripMenuItem miTileV = null!;
    internal ToolStripMenuItem miArrange = null!;

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
        miOpen = new ToolStripMenuItem("&Otworz obraz...") { ShortcutKeys = Keys.Control | Keys.O };
        miCloseAll = new ToolStripMenuItem("Za&mknij wszystkie");
        miSep = new ToolStripSeparator();
        miExit = new ToolStripMenuItem("&Zakoncz");
        miFile.DropDownItems.AddRange(new ToolStripItem[] { miOpen, miCloseAll, miSep, miExit });

        miWindow = new ToolStripMenuItem("&Okna");
        miCascade = new ToolStripMenuItem("Kaskada");
        miTileH = new ToolStripMenuItem("Obok siebie (poziomo)");
        miTileV = new ToolStripMenuItem("Obok siebie (pionowo)");
        miArrange = new ToolStripMenuItem("Ulozenie ikon");
        miWindow.DropDownItems.AddRange(new ToolStripItem[] { miCascade, miTileH, miTileV, miArrange });

        menu.Items.AddRange(new ToolStripItem[] { miFile, miWindow });
        menu.MdiWindowListItem = miWindow;

        MainMenuStrip = menu;
        MdiChildrenMinimizedAnchorBottom = true;
        IsMdiContainer = true;

        AutoScaleMode = AutoScaleMode.Font;
        ClientSize = new Size(1000, 700);
        Controls.Add(menu);
        StartPosition = FormStartPosition.CenterScreen;
        Text = "MDI - przegladarka obrazkow";
    }
}
