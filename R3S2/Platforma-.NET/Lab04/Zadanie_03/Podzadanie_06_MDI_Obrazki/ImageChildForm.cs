namespace MDI_Obrazki;

public class ImageChildForm : Form
{
    private readonly PictureBox _picture;
    public string ImagePath { get; }

    public ImageChildForm(string path)
    {
        ImagePath = path;
        Text = Path.GetFileName(path);
        ClientSize = new Size(480, 360);

        _picture = new PictureBox
        {
            Dock = DockStyle.Fill,
            SizeMode = PictureBoxSizeMode.Zoom,
            BackColor = Color.Black
        };

        try
        {
            _picture.Image = Image.FromFile(path);
        }
        catch (Exception ex)
        {
            MessageBox.Show($"Nie mozna zaladowac obrazu: {ex.Message}",
                "Blad", MessageBoxButtons.OK, MessageBoxIcon.Error);
        }

        var menu = new MenuStrip();
        var view = new ToolStripMenuItem("&Widok");
        var fitItem = new ToolStripMenuItem("Dopasuj (Zoom)", null, (_, _) => _picture.SizeMode = PictureBoxSizeMode.Zoom);
        var stretchItem = new ToolStripMenuItem("Rozciagnij", null, (_, _) => _picture.SizeMode = PictureBoxSizeMode.StretchImage);
        var center = new ToolStripMenuItem("Wysrodkuj", null, (_, _) => _picture.SizeMode = PictureBoxSizeMode.CenterImage);
        view.DropDownItems.AddRange(new ToolStripItem[] { fitItem, stretchItem, center });
        menu.Items.Add(view);

        Controls.Add(_picture);
        Controls.Add(menu);
        MainMenuStrip = menu;
    }

    protected override void OnFormClosed(FormClosedEventArgs e)
    {
        _picture.Image?.Dispose();
        base.OnFormClosed(e);
    }
}
