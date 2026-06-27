using System.Diagnostics;
using System.IO;
using System.Text;
using System.Xml;
using System.Xml.Schema;

namespace WalidatorXML;

public partial class Form1 : Form
{
    public Form1()
    {
        InitializeComponent();

        btnChooseXml.Click += (_, _) => PickFile(txtXml, "Pliki XML (*.xml)|*.xml");
        btnChooseSchema.Click += (_, _) => PickFile(txtSchema, "Schematy (*.xsd;*.dtd)|*.xsd;*.dtd");
        btnValidate.Click += (_, _) => RunValidation();
        btnOpenSamples.Click += (_, _) =>
        {
            var path = Path.Combine(AppContext.BaseDirectory, "Samples");
            if (Directory.Exists(path))
                Process.Start(new ProcessStartInfo("explorer.exe", path) { UseShellExecute = true });
        };
    }

    private void PickFile(TextBox target, string filter)
    {
        using var ofd = new OpenFileDialog
        {
            Filter = filter,
            InitialDirectory = Path.Combine(AppContext.BaseDirectory, "Samples")
        };
        if (ofd.ShowDialog(this) == DialogResult.OK)
            target.Text = ofd.FileName;
    }

    private void RunValidation()
    {
        txtReport.Clear();

        string xmlPath = txtXml.Text;
        string schemaPath = txtSchema.Text;

        if (!File.Exists(xmlPath))
        {
            ReportError("Plik XML nie istnieje.");
            return;
        }

        var settings = new XmlReaderSettings();
        var errors = new StringBuilder();

        if (!string.IsNullOrWhiteSpace(schemaPath))
        {
            string ext = Path.GetExtension(schemaPath).ToLowerInvariant();
            if (ext == ".xsd")
            {
                settings.ValidationType = ValidationType.Schema;
                settings.Schemas.Add(null, schemaPath);
            }
            else if (ext == ".dtd")
            {
                settings.ValidationType = ValidationType.DTD;
                settings.DtdProcessing = DtdProcessing.Parse;
            }
            else
            {
                ReportError($"Nieobslugiwany typ schematu: {ext}. Wybierz .xsd lub .dtd.");
                return;
            }
        }
        else
        {
            settings.ValidationType = ValidationType.None;
            errors.AppendLine("INFO: nie podano schematu - sprawdzana tylko poprawnosc skladni XML.");
        }

        settings.ValidationEventHandler += (_, ev) =>
        {
            errors.AppendLine($"[{ev.Severity}] linia {ev.Exception.LineNumber}:{ev.Exception.LinePosition} — {ev.Message}");
        };

        try
        {
            using var reader = XmlReader.Create(xmlPath, settings);
            while (reader.Read()) { }
        }
        catch (XmlException ex)
        {
            errors.AppendLine($"[Syntax] linia {ex.LineNumber}:{ex.LinePosition} — {ex.Message}");
        }
        catch (Exception ex)
        {
            errors.AppendLine($"[Blad] {ex.Message}");
        }

        if (errors.Length == 0 || IsOnlyInfoLines(errors.ToString()))
        {
            txtReport.AppendText("WALIDACJA OK");
            if (errors.Length > 0) txtReport.AppendText(Environment.NewLine + errors);
            lblStatus.Text = "Dokument poprawny.";
            lblStatus.ForeColor = Color.DarkGreen;
        }
        else
        {
            txtReport.AppendText("WALIDACJA NIEUDANA" + Environment.NewLine + errors);
            lblStatus.Text = "Znaleziono bledy walidacji.";
            lblStatus.ForeColor = Color.Firebrick;
        }
    }

    private static bool IsOnlyInfoLines(string text)
    {
        foreach (var line in text.Split('\n', StringSplitOptions.RemoveEmptyEntries))
            if (!line.StartsWith("INFO:")) return false;
        return true;
    }

    private void ReportError(string message)
    {
        lblStatus.Text = message;
        lblStatus.ForeColor = Color.Firebrick;
        txtReport.Text = message;
    }
}
