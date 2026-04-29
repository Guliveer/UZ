using System.Drawing.Printing;

namespace ListViewDruk;

/// <summary>
/// Rysuje tabele produktow na PrintDocument. Zachowuje indeks aktualnej pozycji
/// miedzy wywolaniami OnPrintPage, wiec jeden PrintDocument potrafi wygenerowac wiele stron.
/// </summary>
public sealed class PrintService
{
    private readonly (string Header, int Width, Func<Produkt, string> Value)[] _columns =
    {
        ("Id",        60,  p => p.Id.ToString()),
        ("Nazwa",     220, p => p.Nazwa),
        ("Kategoria", 150, p => p.Kategoria),
        ("Cena",      90,  p => p.Cena.ToString("N2")),
        ("Stan",      70,  p => p.Stan.ToString())
    };

    private readonly List<Produkt> _items;
    private int _cursor;

    public PrintService(IEnumerable<Produkt> items)
    {
        _items = items.ToList();
    }

    public void Reset() => _cursor = 0;

    public void RenderPage(object? sender, PrintPageEventArgs e)
    {
        var g = e.Graphics!;
        using var headerFont = new Font("Segoe UI", 11f, FontStyle.Bold);
        using var cellFont = new Font("Segoe UI", 10f);
        using var titleFont = new Font("Segoe UI", 16f, FontStyle.Bold);

        float x = e.MarginBounds.Left;
        float y = e.MarginBounds.Top;

        g.DrawString("Raport produktow - Lab04 / Zadanie_05", titleFont, Brushes.Black, x, y);
        y += titleFont.GetHeight(g) + 8;
        g.DrawString($"Wygenerowano {DateTime.Now:yyyy-MM-dd HH:mm}", cellFont, Brushes.Gray, x, y);
        y += cellFont.GetHeight(g) + 8;

        DrawRow(g, x, y, headerFont, Brushes.White, Brushes.SteelBlue, _columns.Select(c => c.Header).ToArray());
        y += headerFont.GetHeight(g) + 8;

        float lineHeight = cellFont.GetHeight(g) + 6;
        while (_cursor < _items.Count && y + lineHeight < e.MarginBounds.Bottom)
        {
            var p = _items[_cursor];
            var values = _columns.Select(c => c.Value(p)).ToArray();
            Brush rowBg = _cursor % 2 == 0 ? Brushes.White : Brushes.WhiteSmoke;
            DrawRow(g, x, y, cellFont, Brushes.Black, rowBg, values);
            y += lineHeight;
            _cursor++;
        }

        using var footFont = new Font("Segoe UI", 8f, FontStyle.Italic);
        string foot = $"Strona - {_cursor} z {_items.Count} pozycji";
        g.DrawString(foot, footFont, Brushes.Gray,
            e.MarginBounds.Left, e.MarginBounds.Bottom + 2);

        e.HasMorePages = _cursor < _items.Count;
    }

    private void DrawRow(Graphics g, float x, float y, Font font, Brush text, Brush bg, string[] values)
    {
        float cx = x;
        for (int i = 0; i < _columns.Length; i++)
        {
            var cell = new RectangleF(cx, y, _columns[i].Width, font.GetHeight(g) + 6);
            g.FillRectangle(bg, cell);
            g.DrawRectangle(Pens.LightGray, cell.X, cell.Y, cell.Width, cell.Height);
            g.DrawString(values[i], font, text, cx + 4, y + 2);
            cx += _columns[i].Width;
        }
    }
}
