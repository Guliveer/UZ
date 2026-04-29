using System.IO;
using System.Runtime.Serialization;
using System.Text;
using System.Xml;
using System.Xml.Serialization;

namespace Serializacja;

public partial class Form1 : Form
{
    public Form1()
    {
        InitializeComponent();

        btnPopulate.Click += (_, _) => UpdateForm(SampleOsoba());
        btnSerializeXml.Click += (_, _) => SerializeWithXmlSerializer();
        btnSerializeDataContract.Click += (_, _) => SerializeWithDataContract();
        btnDeserialize.Click += (_, _) => DeserializeFromXml();

        UpdateForm(SampleOsoba());
    }

    private static Osoba SampleOsoba() => new()
    {
        Id = 1,
        Imie = "Oliwer",
        Nazwisko = "Pawelski",
        Email = "oliwer@example.com",
        DataUrodzenia = new DateTime(2003, 5, 14),
        Adres = new Adres
        {
            Ulica = "Licealna 9",
            Miasto = "Zielona Gora",
            KodPocztowy = "65-417",
            Kraj = "Polska"
        },
        Telefony =
        {
            new Telefon { Typ = TypTelefonu.Komorkowy, Numer = "+48 600 000 000" },
            new Telefon { Typ = TypTelefonu.Sluzbowy, Numer = "+48 68 328 0000" }
        }
    };

    private Osoba BuildFromForm()
    {
        var p = new Osoba
        {
            Id = (int)numId.Value,
            Imie = txtImie.Text,
            Nazwisko = txtNazwisko.Text,
            Email = txtEmail.Text,
            DataUrodzenia = dtpData.Value.Date,
            Adres = new Adres
            {
                Ulica = txtUlica.Text,
                Miasto = txtMiasto.Text,
                KodPocztowy = txtKod.Text,
                Kraj = txtKraj.Text
            }
        };
        foreach (ListViewItem item in lvTelefony.Items)
        {
            p.Telefony.Add(new Telefon
            {
                Typ = Enum.Parse<TypTelefonu>(item.SubItems[0].Text),
                Numer = item.SubItems[1].Text
            });
        }
        return p;
    }

    private void UpdateForm(Osoba p)
    {
        numId.Value = p.Id;
        txtImie.Text = p.Imie;
        txtNazwisko.Text = p.Nazwisko;
        txtEmail.Text = p.Email;
        dtpData.Value = p.DataUrodzenia == default ? DateTime.Today : p.DataUrodzenia;
        txtUlica.Text = p.Adres.Ulica;
        txtMiasto.Text = p.Adres.Miasto;
        txtKod.Text = p.Adres.KodPocztowy;
        txtKraj.Text = p.Adres.Kraj;
        lvTelefony.Items.Clear();
        foreach (var t in p.Telefony)
            lvTelefony.Items.Add(new ListViewItem(new[] { t.Typ.ToString(), t.Numer }));
    }

    private void SerializeWithXmlSerializer()
    {
        var p = BuildFromForm();
        var serializer = new XmlSerializer(typeof(Osoba));
        var ns = new XmlSerializerNamespaces();
        ns.Add("o", "http://lab04.uz/osoby");

        var sb = new StringBuilder();
        using (var writer = XmlWriter.Create(sb, new XmlWriterSettings { Indent = true }))
            serializer.Serialize(writer, p, ns);

        txtOutput.Text = sb.ToString();
        lblFormat.Text = "XmlSerializer (atrybut id, elementy zagniezdzone, tablica <telefony>)";
    }

    private void SerializeWithDataContract()
    {
        var p = BuildFromForm();
        var serializer = new DataContractSerializer(typeof(Osoba));
        using var ms = new MemoryStream();
        using (var writer = XmlWriter.Create(ms, new XmlWriterSettings { Indent = true }))
            serializer.WriteObject(writer, p);
        txtOutput.Text = Encoding.UTF8.GetString(ms.ToArray());
        lblFormat.Text = "DataContractSerializer (alfabetyczna kolejnosc skladowych, jawny namespace)";
    }

    private void DeserializeFromXml()
    {
        try
        {
            var serializer = new XmlSerializer(typeof(Osoba));
            using var reader = new StringReader(txtOutput.Text);
            var p = (Osoba)serializer.Deserialize(reader)!;
            UpdateForm(p);
            lblFormat.Text = "Zdeserializowano pomyslnie";
        }
        catch (Exception ex)
        {
            MessageBox.Show(this, ex.Message, "Blad deserializacji",
                MessageBoxButtons.OK, MessageBoxIcon.Error);
        }
    }
}
