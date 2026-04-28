using System.Xml.Linq;

namespace TreeViewXML;

public partial class Form1 : Form
{
    public Form1()
    {
        InitializeComponent();

        btnOpen.Click += (_, _) => OpenFile();
        btnExpandAll.Click += (_, _) => treeView.ExpandAll();
        btnCollapseAll.Click += (_, _) => treeView.CollapseAll();

        AllowDrop = true;
        DragEnter += (_, e) =>
        {
            if (e.Data?.GetDataPresent(DataFormats.FileDrop) == true)
                e.Effect = DragDropEffects.Copy;
        };
        DragDrop += (_, e) =>
        {
            if (e.Data?.GetData(DataFormats.FileDrop) is string[] files && files.Length > 0)
                LoadFile(files[0]);
        };

        TryLoadDefault();
    }

    private void TryLoadDefault()
    {
        var sample = Path.Combine(AppContext.BaseDirectory, "Samples", "osoby.xml");
        if (File.Exists(sample)) LoadFile(sample);
    }

    private void OpenFile()
    {
        using var ofd = new OpenFileDialog
        {
            Filter = "Pliki XML (*.xml)|*.xml",
            InitialDirectory = Path.Combine(AppContext.BaseDirectory, "Samples")
        };
        if (ofd.ShowDialog(this) == DialogResult.OK)
            LoadFile(ofd.FileName);
    }

    private void LoadFile(string path)
    {
        try
        {
            var doc = XDocument.Load(path, LoadOptions.SetLineInfo);
            treeView.BeginUpdate();
            treeView.Nodes.Clear();
            var root = CreateNode(doc.Root!);
            treeView.Nodes.Add(root);
            treeView.ExpandAll();
            treeView.EndUpdate();
            lblStatus.Text = $"Zaladowano {path}.";
            lblStatus.ForeColor = Color.DarkGreen;
        }
        catch (Exception ex)
        {
            lblStatus.Text = $"Blad: {ex.Message}";
            lblStatus.ForeColor = Color.Firebrick;
        }
    }

    private static TreeNode CreateNode(XElement element)
    {
        var node = new TreeNode($"<{element.Name.LocalName}>")
        {
            ForeColor = Color.DarkBlue,
            Tag = element
        };

        foreach (var attr in element.Attributes())
        {
            node.Nodes.Add(new TreeNode($"@{attr.Name.LocalName} = \"{attr.Value}\"")
            {
                ForeColor = Color.DarkMagenta
            });
        }

        foreach (var child in element.Elements())
            node.Nodes.Add(CreateNode(child));

        if (!element.HasElements && !string.IsNullOrWhiteSpace(element.Value))
        {
            node.Nodes.Add(new TreeNode($"= \"{element.Value.Trim()}\"")
            {
                ForeColor = Color.DarkGreen
            });
        }

        return node;
    }
}
