namespace MDI_Obrazki;

public partial class Form1 : Form
{
    public Form1()
    {
        InitializeComponent();

        miOpen.Click += (_, _) => OpenImages();
        miCloseAll.Click += (_, _) => CloseAllChildren();
        miExit.Click += (_, _) => Close();

        miCascade.Click += (_, _) => LayoutMdi(MdiLayout.Cascade);
        miTileH.Click += (_, _) => LayoutMdi(MdiLayout.TileHorizontal);
        miTileV.Click += (_, _) => LayoutMdi(MdiLayout.TileVertical);
        miArrange.Click += (_, _) => LayoutMdi(MdiLayout.ArrangeIcons);

        AllowDrop = true;
        DragEnter += (_, e) =>
        {
            if (e.Data?.GetDataPresent(DataFormats.FileDrop) == true)
                e.Effect = DragDropEffects.Copy;
        };
        DragDrop += (_, e) =>
        {
            if (e.Data?.GetData(DataFormats.FileDrop) is string[] files)
                foreach (var f in files) OpenImage(f);
        };
    }

    private void OpenImages()
    {
        using var ofd = new OpenFileDialog
        {
            Filter = "Obrazy|*.jpg;*.jpeg;*.png;*.bmp;*.gif",
            Multiselect = true
        };
        if (ofd.ShowDialog(this) == DialogResult.OK)
            foreach (var f in ofd.FileNames) OpenImage(f);
    }

    private void OpenImage(string path)
    {
        var child = new ImageChildForm(path) { MdiParent = this };
        child.Show();
    }

    private void CloseAllChildren()
    {
        foreach (var c in MdiChildren.ToArray())
            c.Close();
    }
}
