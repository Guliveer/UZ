using System.Collections.ObjectModel;
using System.Globalization;

namespace Zadanie_06;

public class Contact
{
    public string Imie { get; set; } = "";
    public string Nazwisko { get; set; } = "";
    public string Telefon { get; set; } = "";
    public string Email { get; set; } = "";
    public string Adres { get; set; } = "";
    public string DataUrodzin { get; set; } = "";
    public string FullDisplay => $"{Imie} {Nazwisko}  |  {Telefon}  |  {Email}";
    public string DetailDisplay => $"{Adres}  |  ur. {DataUrodzin}";
}

public partial class MainPage : ContentPage
{
    private readonly ObservableCollection<Contact> _allContacts;
    private readonly ObservableCollection<Contact> _displayed;
    private string _sortField = "";
    private bool _sortAsc = true;
    private Contact? _selected;

    public MainPage()
    {
        InitializeComponent();

        _allContacts = new ObservableCollection<Contact>
        {
            new() { Imie = "Jan", Nazwisko = "Kowalski", Telefon = "501-123-456", Email = "jan.kowalski@email.pl", Adres = "ul. Dluga 15, Warszawa", DataUrodzin = "15.03.1985" },
            new() { Imie = "Anna", Nazwisko = "Nowak", Telefon = "602-234-567", Email = "anna.nowak@email.pl", Adres = "ul. Krotka 7, Krakow", DataUrodzin = "22.07.1990" },
            new() { Imie = "Piotr", Nazwisko = "Wisniewski", Telefon = "503-345-678", Email = "piotr.w@email.pl", Adres = "ul. Polna 3, Gdansk", DataUrodzin = "10.01.1978" },
            new() { Imie = "Maria", Nazwisko = "Wojcik", Telefon = "604-456-789", Email = "m.wojcik@email.pl", Adres = "ul. Lesna 22, Poznan", DataUrodzin = "05.11.1995" },
            new() { Imie = "Tomasz", Nazwisko = "Kaminski", Telefon = "505-567-890", Email = "t.kaminski@email.pl", Adres = "ul. Ogrodowa 9, Wroclaw", DataUrodzin = "18.09.1982" }
        };
        _displayed = new ObservableCollection<Contact>(_allContacts);
        ContactsView.ItemsSource = _displayed;
    }

    private void OnSelectionChanged(object? sender, SelectionChangedEventArgs e)
    {
        _selected = e.CurrentSelection.FirstOrDefault() as Contact;
        if (_selected == null) return;
        TxtImie.Text = _selected.Imie;
        TxtNazwisko.Text = _selected.Nazwisko;
        TxtTelefon.Text = _selected.Telefon;
        TxtEmail.Text = _selected.Email;
        TxtAdres.Text = _selected.Adres;
        TxtDataUr.Text = _selected.DataUrodzin;
    }

    private async void OnAdd(object? sender, EventArgs e)
    {
        if (string.IsNullOrWhiteSpace(TxtImie.Text) || string.IsNullOrWhiteSpace(TxtNazwisko.Text))
        {
            await DisplayAlertAsync("Blad", "Imie i nazwisko sa wymagane.", "OK");
            return;
        }
        var contact = new Contact
        {
            Imie = TxtImie.Text.Trim(), Nazwisko = TxtNazwisko.Text.Trim(),
            Telefon = TxtTelefon.Text?.Trim() ?? "", Email = TxtEmail.Text?.Trim() ?? "",
            Adres = TxtAdres.Text?.Trim() ?? "", DataUrodzin = TxtDataUr.Text?.Trim() ?? ""
        };
        _allContacts.Add(contact);
        RefreshDisplay();
        ClearFields();
    }

    private void OnEdit(object? sender, EventArgs e)
    {
        if (_selected == null) return;
        _selected.Imie = TxtImie.Text?.Trim() ?? "";
        _selected.Nazwisko = TxtNazwisko.Text?.Trim() ?? "";
        _selected.Telefon = TxtTelefon.Text?.Trim() ?? "";
        _selected.Email = TxtEmail.Text?.Trim() ?? "";
        _selected.Adres = TxtAdres.Text?.Trim() ?? "";
        _selected.DataUrodzin = TxtDataUr.Text?.Trim() ?? "";
        RefreshDisplay();
    }

    private void OnRemove(object? sender, EventArgs e)
    {
        if (_selected == null) return;
        _allContacts.Remove(_selected);
        _selected = null;
        RefreshDisplay();
        ClearFields();
    }

    private void OnSearchChanged(object? sender, TextChangedEventArgs e) =>
        RefreshDisplay(e.NewTextValue?.Trim().ToLower() ?? "");

    private void OnSortName(object? sender, EventArgs e) => SortBy("Nazwisko");
    private void OnSortDate(object? sender, EventArgs e) => SortBy("DataUrodzin");

    private void SortBy(string field)
    {
        if (_sortField == field) _sortAsc = !_sortAsc;
        else { _sortField = field; _sortAsc = true; }
        RefreshDisplay();
    }

    private void RefreshDisplay(string? query = null)
    {
        query ??= SearchBarCtrl.Text?.Trim().ToLower() ?? "";
        IEnumerable<Contact> filtered = _allContacts;

        if (!string.IsNullOrEmpty(query))
            filtered = filtered.Where(c =>
                c.Imie.ToLower().Contains(query) || c.Nazwisko.ToLower().Contains(query) ||
                c.Telefon.ToLower().Contains(query) || c.Email.ToLower().Contains(query) ||
                c.Adres.ToLower().Contains(query));

        if (_sortField == "Nazwisko")
            filtered = _sortAsc ? filtered.OrderBy(c => c.Nazwisko) : filtered.OrderByDescending(c => c.Nazwisko);
        else if (_sortField == "DataUrodzin")
            filtered = _sortAsc ? filtered.OrderBy(c => ParseDate(c.DataUrodzin)) : filtered.OrderByDescending(c => ParseDate(c.DataUrodzin));

        _displayed.Clear();
        foreach (var c in filtered) _displayed.Add(c);
    }

    private void ClearFields()
    {
        TxtImie.Text = TxtNazwisko.Text = TxtTelefon.Text = "";
        TxtEmail.Text = TxtAdres.Text = TxtDataUr.Text = "";
    }

    private static DateTime ParseDate(string s) =>
        DateTime.TryParseExact(s, "dd.MM.yyyy", CultureInfo.InvariantCulture, DateTimeStyles.None, out var d) ? d : DateTime.MaxValue;
}
