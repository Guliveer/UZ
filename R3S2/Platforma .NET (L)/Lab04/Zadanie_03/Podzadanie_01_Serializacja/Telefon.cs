using System.Runtime.Serialization;
using System.Xml.Serialization;

namespace Serializacja;

public enum TypTelefonu
{
    Komorkowy,
    Domowy,
    Sluzbowy
}

[Serializable]
[DataContract(Name = "Telefon", Namespace = "http://lab04.uz/osoby")]
public class Telefon
{
    [DataMember] [XmlAttribute("typ")]
    public TypTelefonu Typ { get; set; }

    [DataMember] [XmlText]
    public string Numer { get; set; } = "";
}
