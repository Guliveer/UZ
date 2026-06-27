using System.Collections.ObjectModel;

namespace Zadanie_07;

public class CodecInfo
{
    public string Name { get; set; } = "";
    public string FormatDescription { get; set; } = "";
    public string Extensions { get; set; } = "";
    public string MimeType { get; set; } = "";
    public string FormatID { get; set; } = "";
    public int Version { get; set; }
    public string Flags { get; set; } = "";
    public bool IsEncoder { get; set; }
}

public partial class MainPage : ContentPage
{
    private readonly ObservableCollection<CodecInfo> _encoders = new();
    private readonly ObservableCollection<CodecInfo> _decoders = new();

    public MainPage()
    {
        InitializeComponent();
        LoadCodecData();

        EncoderList.ItemsSource = _encoders;
        DecoderList.ItemsSource = _decoders;
        BtnEncoders.Text = $"Enkodery ({_encoders.Count})";
        BtnDecoders.Text = $"Dekodery ({_decoders.Count})";
        InfoBar.Text = $"Dostepne enkodery: {_encoders.Count}  |  Dostepne dekodery: {_decoders.Count}";
    }

    private void OnShowEncoders(object? sender, EventArgs e)
    {
        EncoderList.IsVisible = true;
        DecoderList.IsVisible = false;
        BtnEncoders.BackgroundColor = Colors.RoyalBlue;
        BtnDecoders.BackgroundColor = Colors.Gray;
    }

    private void OnShowDecoders(object? sender, EventArgs e)
    {
        EncoderList.IsVisible = false;
        DecoderList.IsVisible = true;
        BtnDecoders.BackgroundColor = Colors.RoyalBlue;
        BtnEncoders.BackgroundColor = Colors.Gray;
    }

    private void OnCodecSelected(object? sender, SelectionChangedEventArgs e)
    {
        if (e.CurrentSelection.FirstOrDefault() is CodecInfo codec)
        {
            DetailsLabel.Text =
                $"Nazwa kodeka:        {codec.Name}\n" +
                $"Opis formatu:        {codec.FormatDescription}\n" +
                $"Rozszerzenia:        {codec.Extensions}\n" +
                $"Typ MIME:            {codec.MimeType}\n" +
                $"Format ID:           {codec.FormatID}\n" +
                $"Wersja:              {codec.Version}\n" +
                $"Flagi:               {codec.Flags}\n" +
                $"Typ:                 {(codec.IsEncoder ? "Enkoder" : "Dekoder")}";
        }
    }

    private void LoadCodecData()
    {
        var codecs = new[]
        {
            new { Name = "Built-in BMP Codec", Desc = "BMP", Ext = "*.BMP;*.DIB;*.RLE", Mime = "image/bmp",
                Id = "557cf400-1a04-11d3-9a73-0000f81ef32e", Ver = 1, FlagsEnc = "Encoder, Builtin, SupportBitmap", FlagsDec = "Decoder, Builtin, SupportBitmap" },
            new { Name = "Built-in JPEG Codec", Desc = "JPEG", Ext = "*.JPG;*.JPEG;*.JPE;*.JFIF", Mime = "image/jpeg",
                Id = "557cf401-1a04-11d3-9a73-0000f81ef32e", Ver = 1, FlagsEnc = "Encoder, Builtin, SupportBitmap", FlagsDec = "Decoder, Builtin, SupportBitmap" },
            new { Name = "Built-in GIF Codec", Desc = "GIF", Ext = "*.GIF", Mime = "image/gif",
                Id = "557cf402-1a04-11d3-9a73-0000f81ef32e", Ver = 1, FlagsEnc = "Encoder, Builtin, SupportBitmap", FlagsDec = "Decoder, Builtin, SupportBitmap" },
            new { Name = "Built-in EMF Codec", Desc = "EMF", Ext = "*.EMF", Mime = "image/x-emf",
                Id = "557cf403-1a04-11d3-9a73-0000f81ef32e", Ver = 1, FlagsEnc = "Encoder, Builtin, SupportBitmap", FlagsDec = "Decoder, Builtin, SupportBitmap" },
            new { Name = "Built-in WMF Codec", Desc = "WMF", Ext = "*.WMF", Mime = "image/x-wmf",
                Id = "557cf404-1a04-11d3-9a73-0000f81ef32e", Ver = 1, FlagsEnc = "Encoder, Builtin, SupportBitmap", FlagsDec = "Decoder, Builtin, SupportBitmap" },
            new { Name = "Built-in TIFF Codec", Desc = "TIFF", Ext = "*.TIF;*.TIFF", Mime = "image/tiff",
                Id = "557cf405-1a04-11d3-9a73-0000f81ef32e", Ver = 1, FlagsEnc = "Encoder, Builtin, SupportBitmap", FlagsDec = "Decoder, Builtin, SupportBitmap" },
            new { Name = "Built-in PNG Codec", Desc = "PNG", Ext = "*.PNG", Mime = "image/png",
                Id = "557cf406-1a04-11d3-9a73-0000f81ef32e", Ver = 1, FlagsEnc = "Encoder, Builtin, SupportBitmap", FlagsDec = "Decoder, Builtin, SupportBitmap" },
            new { Name = "Built-in ICO Codec", Desc = "ICO", Ext = "*.ICO", Mime = "image/x-icon",
                Id = "557cf407-1a04-11d3-9a73-0000f81ef32e", Ver = 1, FlagsEnc = "", FlagsDec = "Decoder, Builtin, SupportBitmap" },
        };

        foreach (var c in codecs)
        {
            if (!string.IsNullOrEmpty(c.FlagsEnc))
                _encoders.Add(new CodecInfo { Name = c.Name, FormatDescription = c.Desc, Extensions = c.Ext,
                    MimeType = c.Mime, FormatID = c.Id, Version = c.Ver, Flags = c.FlagsEnc, IsEncoder = true });

            _decoders.Add(new CodecInfo { Name = c.Name, FormatDescription = c.Desc, Extensions = c.Ext,
                MimeType = c.Mime, FormatID = c.Id, Version = c.Ver, Flags = c.FlagsDec, IsEncoder = false });
        }
    }
}
