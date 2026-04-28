using System.Drawing.Printing;

namespace ListViewDruk;

public partial class Form1 : Form
{
    private readonly List<Produkt> _produkty = GenerateSample(50).ToList();

    public Form1()
    {
        InitializeComponent();

        listView.Columns.AddRange(new[]
        {
            new ColumnHeader { Text = "Id", Width = 60 },
            new ColumnHeader { Text = "Nazwa", Width = 220 },
            new ColumnHeader { Text = "Kategoria", Width = 140 },
            new ColumnHeader { Text = "Cena", Width = 90, TextAlign = HorizontalAlignment.Right },
            new ColumnHeader { Text = "Stan", Width = 70, TextAlign = HorizontalAlignment.Right }
        });

        foreach (var p in _produkty)
            listView.Items.Add(new ListViewItem(new[]
            {
                p.Id.ToString(), p.Nazwa, p.Kategoria, p.Cena.ToString("N2"), p.Stan.ToString()
            }));

        btnPrint.Click += (_, _) => Print(selected: false);
        btnPrintSelected.Click += (_, _) => Print(selected: true);
        btnPreview.Click += (_, _) => Preview(selected: false);
    }

    private IEnumerable<Produkt> SelectedProducts()
    {
        foreach (int idx in listView.SelectedIndices)
            yield return _produkty[idx];
    }

    private void Print(bool selected)
    {
        var items = selected ? SelectedProducts().ToList() : _produkty;
        if (selected && items.Count == 0)
        {
            MessageBox.Show(this, "Zaznacz pozycje do wydruku.", "Brak zaznaczenia",
                MessageBoxButtons.OK, MessageBoxIcon.Information);
            return;
        }

        var svc = new PrintService(items);
        using var doc = new PrintDocument();
        doc.PrintPage += svc.RenderPage;

        using var dlg = new PrintDialog { Document = doc, UseEXDialog = true };
        if (dlg.ShowDialog(this) == DialogResult.OK)
        {
            svc.Reset();
            doc.Print();
        }
    }

    private void Preview(bool selected)
    {
        var items = selected ? SelectedProducts().ToList() : _produkty;
        var svc = new PrintService(items);
        var doc = new PrintDocument();
        doc.PrintPage += svc.RenderPage;
        svc.Reset();

        using var dlg = new PrintPreviewDialog
        {
            Document = doc,
            Width = 900,
            Height = 700,
            StartPosition = FormStartPosition.CenterParent
        };
        dlg.ShowDialog(this);
    }

    private static IEnumerable<Produkt> GenerateSample(int count)
    {
        string[] nazwy =
        {
            "Klawiatura mechaniczna", "Mysz optyczna", "Monitor 27\"", "Slim SSD 1TB",
            "Laptop 15 cali", "Kabel HDMI 2m", "Router WiFi 6", "Smartfon Pro",
            "Tablet 10 cali", "Drukarka laserowa"
        };
        string[] kategorie = { "Peryferia", "Komputery", "Akcesoria", "Sieciowe" };
        var rng = new Random(42);
        for (int i = 1; i <= count; i++)
        {
            yield return new Produkt(
                i,
                nazwy[i % nazwy.Length] + $" #{i:D3}",
                kategorie[i % kategorie.Length],
                Math.Round((decimal)(50 + rng.NextDouble() * 4500), 2),
                rng.Next(0, 150));
        }
    }
}
