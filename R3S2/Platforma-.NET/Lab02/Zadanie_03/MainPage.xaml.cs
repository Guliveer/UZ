namespace Zadanie_03;

public partial class MainPage : ContentPage
{
    private static readonly string[] PaletteHexColors =
    [
        "#EF4444", "#F97316", "#F59E0B", "#EAB308", "#84CC16",
        "#22C55E", "#10B981", "#14B8A6", "#06B6D4", "#0EA5E9",
        "#3B82F6", "#6366F1", "#8B5CF6", "#A855F7", "#D946EF",
        "#EC4899", "#F43F5E", "#78716C", "#64748B", "#000000",
        "#FFFFFF", "#FCA5A5", "#FDE68A", "#BBF7D0", "#BAE6FD",
        "#C4B5FD", "#FBCFE8", "#A3E635", "#2DD4BF", "#818CF8"
    ];

    private Color? _selectedColor;

    public MainPage()
    {
        InitializeComponent();
        BuildColorPalette();
    }

    private void BuildColorPalette()
    {
        foreach (var hex in PaletteHexColors)
        {
            var color = Color.FromArgb(hex);
            var box = new BoxView
            {
                Color = color,
                WidthRequest = 44,
                HeightRequest = 44,
                CornerRadius = 6,
                Margin = new Thickness(3)
            };

            var tap = new TapGestureRecognizer();
            tap.Tapped += (_, _) => OnPaletteColorTapped(color, hex);
            box.GestureRecognizers.Add(tap);

            ColorPalette.Children.Add(box);
        }
    }

    private void OnPaletteColorTapped(Color color, string hex)
    {
        _selectedColor = color;
        ColorPreview.Color = color;
        ColorHexLabel.Text = hex;
        ColorOkBtn.IsEnabled = true;
    }

    private async void OnSimpleAlert(object? sender, EventArgs e)
    {
        await DisplayAlertAsync("Informacja", "To jest prosty alert z tekstem.", "OK");
        SetResult("Wynik: OK (prosty alert)");
    }

    private async void OnConfirmAlert(object? sender, EventArgs e)
    {
        var result = await DisplayAlertAsync("Pytanie", "Czy kontynuowac?", "Tak", "Nie");
        SetResult($"Wynik: {(result ? "Tak" : "Nie")}");
    }

    private async void OnSaveAlert(object? sender, EventArgs e)
    {
        var result = await DisplayAlertAsync("Zapisz", "Czy chcesz zapisac zmiany?", "Zapisz", "Anuluj");
        SetResult($"Wynik: {(result ? "Zapisz" : "Anuluj")}");
    }

    private async void OnDeleteAlert(object? sender, EventArgs e)
    {
        var result = await DisplayAlertAsync("Usuwanie", "Czy na pewno chcesz usunac ten element?", "Usun", "Anuluj");
        SetResult($"Wynik: {(result ? "Usun" : "Anuluj")}");
    }

    private async void OnCloseAlert(object? sender, EventArgs e)
    {
        var result = await DisplayAlertAsync("Zamykanie", "Czy na pewno chcesz zamknac aplikacje?", "Tak", "Nie");
        SetResult($"Wynik: {(result ? "Tak" : "Nie")}");
    }

    private void OnColorSheet(object? sender, EventArgs e)
    {
        _selectedColor = null;
        ColorPreview.Color = Colors.Transparent;
        ColorHexLabel.Text = "";
        ColorOkBtn.IsEnabled = false;
        ColorOverlay.IsVisible = true;
    }

    private void OnColorOverlayOk(object? sender, EventArgs e)
    {
        ColorOverlay.IsVisible = false;
        SetResult($"Wynik: {ColorHexLabel.Text}", _selectedColor);
    }

    private void OnColorOverlayCancel(object? sender, EventArgs e)
    {
        ColorOverlay.IsVisible = false;
        SetResult("Wynik: (anulowano)");
    }

    private void SetResult(string text, Color? color = null)
    {
        ResultLabel.Text = text;
        ResultLabel.TextColor = color ?? Colors.DarkBlue;
    }

    private async void OnActionSheet(object? sender, EventArgs e)
    {
        var result = await DisplayActionSheetAsync("Co chcesz zrobic?", "Anuluj", "Usun wszystko",
            "Kopiuj", "Wklej", "Wytnij");
        SetResult($"Wynik: {result ?? "(brak)"}");
    }

    private async void OnFormatSheet(object? sender, EventArgs e)
    {
        var result = await DisplayActionSheetAsync("Eksportuj jako:", "Anuluj", null,
            "PDF", "DOCX", "TXT", "HTML", "CSV");
        SetResult($"Wynik: {result ?? "(brak)"}");
    }

    private async void OnNamePrompt(object? sender, EventArgs e)
    {
        var result = await DisplayPromptAsync("Imie", "Jak masz na imie?",
            "OK", "Anuluj", "Wpisz imie");
        SetResult($"Wynik: {result ?? "(anulowano)"}");
    }

    private void OnNumberPrompt(object? sender, EventArgs e)
    {
        NumberEntry.Text = string.Empty;
        NumberOverlay.IsVisible = true;
        NumberEntry.Focus();
    }

    private async void OnNumberOverlayOk(object? sender, EventArgs e)
    {
        var text = NumberEntry.Text;

        if (string.IsNullOrEmpty(text) || !double.TryParse(text, out _))
        {
            await DisplayAlertAsync("Blad", "Wprowadzona wartosc nie jest liczba.", "OK");
            return;
        }

        NumberOverlay.IsVisible = false;
        SetResult($"Wynik: {text}");
    }

    private void OnNumberOverlayCancel(object? sender, EventArgs e)
    {
        NumberOverlay.IsVisible = false;
        SetResult("Wynik: (anulowano)");
    }

    private void OnPasswordPrompt(object? sender, EventArgs e)
    {
        PasswordEntry.Text = string.Empty;
        PasswordOverlay.IsVisible = true;
        PasswordEntry.Focus();
    }

    private void OnPasswordOverlayOk(object? sender, EventArgs e)
    {
        PasswordOverlay.IsVisible = false;
        var text = PasswordEntry.Text;
        SetResult(string.IsNullOrEmpty(text) ? "Wynik: (puste)" : $"Wynik: {text}");
    }

    private void OnPasswordOverlayCancel(object? sender, EventArgs e)
    {
        PasswordOverlay.IsVisible = false;
        SetResult("Wynik: (anulowano)");
    }
}