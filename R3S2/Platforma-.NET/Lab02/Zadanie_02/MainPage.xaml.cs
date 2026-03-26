namespace Zadanie_02;

public partial class MainPage : ContentPage
{
    public MainPage()
    {
        InitializeComponent();
    }

    private void OnAdd(object? sender, EventArgs e) => Calculate((a, b) => a + b);
    private void OnSub(object? sender, EventArgs e) => Calculate((a, b) => a - b);
    private void OnMul(object? sender, EventArgs e) => Calculate((a, b) => a * b);

    private void OnDiv(object? sender, EventArgs e) => Calculate((a, b) =>
    {
        if (b == 0) throw new DivideByZeroException();
        return a / b;
    });

    private void Calculate(Func<double, double, double> operation)
    {
        if (!double.TryParse(EntryA.Text, out double a))
        {
            ShowError("Nieprawidlowa wartosc argumentu A.");
            return;
        }
        if (!double.TryParse(EntryB.Text, out double b))
        {
            ShowError("Nieprawidlowa wartosc argumentu B.");
            return;
        }
        try
        {
            double result = operation(a, b);
            ResultLabel.Text = result.ToString("G10");
            ResultLabel.TextColor = Colors.DarkBlue;
        }
        catch (DivideByZeroException)
        {
            ShowError("Dzielenie przez zero!");
        }
    }

    private void ShowError(string message)
    {
        ResultLabel.Text = message;
        ResultLabel.TextColor = Colors.Red;
    }
}
