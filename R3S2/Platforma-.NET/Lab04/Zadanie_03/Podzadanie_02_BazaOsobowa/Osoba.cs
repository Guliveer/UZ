using System.Xml.Serialization;

namespace BazaOsobowa;

[XmlRoot("osoby", Namespace = "http://lab04.uz/osoby")]
public class Osoby
{
    [XmlElement("osoba")]
    public List<Osoba> Lista { get; set; } = new();
}

public class Osoba
{
    [XmlAttribute("id")]
    public int Id { get; set; }

    [XmlElement("imie")]
    public string Imie { get; set; } = "";

    [XmlElement("nazwisko")]
    public string Nazwisko { get; set; } = "";

    [XmlElement("email")]
    public string Email { get; set; } = "";

    [XmlElement("dataUrodzenia", DataType = "date")]
    public DateTime DataUrodzenia { get; set; } = DateTime.Today;

    [XmlElement("miasto")]
    public string Miasto { get; set; } = "";
}
