using System.Runtime.Serialization;
using System.Xml.Serialization;

namespace Serializacja;

[Serializable]
[DataContract(Name = "Osoba", Namespace = "http://lab04.uz/osoby")]
[XmlRoot("osoba", Namespace = "http://lab04.uz/osoby")]
public class Osoba
{
    [DataMember] [XmlAttribute("id")]
    public int Id { get; set; }

    [DataMember] [XmlElement("imie")]
    public string Imie { get; set; } = "";

    [DataMember] [XmlElement("nazwisko")]
    public string Nazwisko { get; set; } = "";

    [DataMember] [XmlElement("dataUrodzenia")]
    public DateTime DataUrodzenia { get; set; }

    [DataMember] [XmlElement("email")]
    public string Email { get; set; } = "";

    [DataMember] [XmlElement("adres")]
    public Adres Adres { get; set; } = new();

    [DataMember]
    [XmlArray("telefony")]
    [XmlArrayItem("telefon")]
    public List<Telefon> Telefony { get; set; } = new();

    public override string ToString() => $"{Imie} {Nazwisko} ({Email})";
}
