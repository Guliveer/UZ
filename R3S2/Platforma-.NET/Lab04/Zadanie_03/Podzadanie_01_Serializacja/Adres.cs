using System.Runtime.Serialization;
using System.Xml.Serialization;

namespace Serializacja;

[Serializable]
[DataContract(Name = "Adres", Namespace = "http://lab04.uz/osoby")]
public class Adres
{
    [DataMember] [XmlElement("ulica")]
    public string Ulica { get; set; } = "";

    [DataMember] [XmlElement("miasto")]
    public string Miasto { get; set; } = "";

    [DataMember] [XmlElement("kod")]
    public string KodPocztowy { get; set; } = "";

    [DataMember] [XmlElement("kraj")]
    public string Kraj { get; set; } = "Polska";
}
