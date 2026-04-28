using System.ComponentModel;
using System.Diagnostics;
using System.IO;
using System.Text;
using System.Xml;
using System.Xml.Schema;
using System.Xml.Serialization;

namespace BazaOsobowa;

public partial class Form1 : Form
{
    private readonly BindingList<Osoba> _osoby = new();
    private readonly string _schemaPath = Path.Combine(AppContext.BaseDirectory, "Schema", "osoby.xsd");

    public Form1()
    {
        InitializeComponent();

        dataGrid.AutoGenerateColumns = false;
        dataGrid.DataSource = _osoby;
        dataGrid.Columns.AddRange(
            new DataGridViewTextBoxColumn { HeaderText = "Id", DataPropertyName = nameof(Osoba.Id), Width = 50 },
            new DataGridViewTextBoxColumn { HeaderText = "Imie", DataPropertyName = nameof(Osoba.Imie), Width = 120 },
            new DataGridViewTextBoxColumn { HeaderText = "Nazwisko", DataPropertyName = nameof(Osoba.Nazwisko), Width = 140 },
            new DataGridViewTextBoxColumn { HeaderText = "Email", DataPropertyName = nameof(Osoba.Email), Width = 180 },
            new DataGridViewTextBoxColumn
            {
                HeaderText = "Data ur.",
                DataPropertyName = nameof(Osoba.DataUrodzenia),
                Width = 110,
                DefaultCellStyle = { Format = "yyyy-MM-dd" }
            },
            new DataGridViewTextBoxColumn { HeaderText = "Miasto", DataPropertyName = nameof(Osoba.Miasto), Width = 140 });

        btnDodaj.Click += (_, _) => _osoby.Add(new Osoba
        {
            Id = NextId(),
            Imie = "Nowy",
            Nazwisko = "Rekord",
            DataUrodzenia = DateTime.Today,
            Miasto = "Zielona Gora"
        });

        btnUsun.Click += (_, _) =>
        {
            if (dataGrid.CurrentRow?.DataBoundItem is Osoba o)
                _osoby.Remove(o);
        };

        btnZapisz.Click += (_, _) => ZapiszZWalidacja();
        btnWczytaj.Click += (_, _) => Wczytaj();
        btnPokazXsd.Click += (_, _) => Process.Start(new ProcessStartInfo(_schemaPath) { UseShellExecute = true });
    }

    private int NextId() => _osoby.Count == 0 ? 1 : _osoby.Max(o => o.Id) + 1;

    private void ZapiszZWalidacja()
    {
        using var sfd = new SaveFileDialog
        {
            Filter = "Pliki XML (*.xml)|*.xml",
            FileName = "osoby.xml"
        };
        if (sfd.ShowDialog(this) != DialogResult.OK) return;

        var root = new Osoby { Lista = _osoby.ToList() };
        var serializer = new XmlSerializer(typeof(Osoby));

        var sb = new StringBuilder();
        using (var writer = XmlWriter.Create(sb, new XmlWriterSettings { Indent = true }))
            serializer.Serialize(writer, root);

        if (TryValidate(sb.ToString(), out var errors))
        {
            File.WriteAllText(sfd.FileName, sb.ToString(), Encoding.UTF8);
            lblStatus.Text = $"Zapisano {sfd.FileName} ({_osoby.Count} rekordow).";
            lblStatus.ForeColor = Color.DarkGreen;
        }
        else
        {
            lblStatus.Text = "Nie zapisano - dane nie spelniaja schematu:";
            lblStatus.ForeColor = Color.Firebrick;
            MessageBox.Show(this, errors, "Walidacja XSD nieudana",
                MessageBoxButtons.OK, MessageBoxIcon.Warning);
        }
    }

    private bool TryValidate(string xml, out string errors)
    {
        var sb = new StringBuilder();
        var settings = new XmlReaderSettings { ValidationType = ValidationType.Schema };
        settings.Schemas.Add("http://lab04.uz/osoby", _schemaPath);
        settings.ValidationEventHandler += (_, ev) =>
            sb.AppendLine($"Linia {ev.Exception.LineNumber}: [{ev.Severity}] {ev.Message}");

        using var reader = XmlReader.Create(new StringReader(xml), settings);
        while (reader.Read()) { /* force validation */ }

        errors = sb.ToString();
        return errors.Length == 0;
    }

    private void Wczytaj()
    {
        using var ofd = new OpenFileDialog { Filter = "Pliki XML (*.xml)|*.xml" };
        if (ofd.ShowDialog(this) != DialogResult.OK) return;

        try
        {
            var serializer = new XmlSerializer(typeof(Osoby));
            using var stream = File.OpenRead(ofd.FileName);
            var root = (Osoby)serializer.Deserialize(stream)!;
            _osoby.Clear();
            foreach (var o in root.Lista) _osoby.Add(o);
            lblStatus.Text = $"Wczytano {_osoby.Count} rekordow z {ofd.FileName}.";
            lblStatus.ForeColor = Color.DarkGreen;
        }
        catch (Exception ex)
        {
            MessageBox.Show(this, ex.Message, "Blad wczytywania",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }
}
