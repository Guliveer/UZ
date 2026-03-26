namespace Zadanie_04;

public partial class MainPage : ContentPage
{
    private double _currentValue;
    private string _pendingOp = "";
    private bool _newEntry = true;

    public MainPage()
    {
        InitializeComponent();
    }

    private void OnDigit(object? sender, EventArgs e)
    {
        if (sender is not Button btn) return;
        string digit = btn.Text;
        if (_newEntry) { Display.Text = digit; _newEntry = false; }
        else if (Display.Text == "0") Display.Text = digit;
        else Display.Text += digit;
    }

    private void OnDecimal(object? sender, EventArgs e)
    {
        if (_newEntry) { Display.Text = "0,"; _newEntry = false; }
        else if (!Display.Text.Contains(',')) Display.Text += ",";
    }

    private void OnToggleSign(object? sender, EventArgs e)
    {
        if (Display.Text != "0")
            Display.Text = Display.Text.StartsWith('-') ? Display.Text[1..] : "-" + Display.Text;
    }

    private void OnBackspace(object? sender, EventArgs e)
    {
        if (_newEntry) return;
        Display.Text = Display.Text[..^1];
        if (Display.Text is "" or "-") Display.Text = "0";
    }

    private void OnClearEntry(object? sender, EventArgs e) { Display.Text = "0"; _newEntry = true; }

    private void OnClear(object? sender, EventArgs e)
    {
        Display.Text = "0"; _currentValue = 0; _pendingOp = ""; _newEntry = true;
    }

    private void OnOpAdd(object? sender, EventArgs e) => SetOp("+");
    private void OnOpSub(object? sender, EventArgs e) => SetOp("-");
    private void OnOpMul(object? sender, EventArgs e) => SetOp("*");
    private void OnOpDiv(object? sender, EventArgs e) => SetOp("/");

    private void SetOp(string op)
    {
        if (!_newEntry && _pendingOp != "") Evaluate();
        if (double.TryParse(Display.Text, out double val)) _currentValue = val;
        _pendingOp = op;
        _newEntry = true;
    }

    private void OnEquals(object? sender, EventArgs e) => Evaluate();

    private void Evaluate()
    {
        if (_pendingOp == "" || _newEntry) return;
        if (!double.TryParse(Display.Text, out double b)) return;

        double result = _pendingOp switch
        {
            "+" => _currentValue + b,
            "-" => _currentValue - b,
            "*" => _currentValue * b,
            "/" when b == 0 => double.NaN,
            "/" => _currentValue / b,
            _ => b
        };

        Display.Text = double.IsNaN(result) ? "Nie mozna dzielic przez zero" : result.ToString("G10");
        _currentValue = result;
        _pendingOp = "";
        _newEntry = true;
    }

    private void OnPercent(object? sender, EventArgs e)
    {
        if (double.TryParse(Display.Text, out double val))
        { Display.Text = (_currentValue * val / 100).ToString("G10"); _newEntry = true; }
    }

    private void OnInverse(object? sender, EventArgs e) => UnaryOp(v => v == 0 ? double.NaN : 1 / v);
    private void OnSquare(object? sender, EventArgs e) => UnaryOp(v => v * v);
    private void OnSqrt(object? sender, EventArgs e) => UnaryOp(v => v < 0 ? double.NaN : Math.Sqrt(v));

    private void UnaryOp(Func<double, double> op)
    {
        if (!double.TryParse(Display.Text, out double val)) return;
        double result = op(val);
        Display.Text = double.IsNaN(result) ? "Blad" : result.ToString("G10");
        _newEntry = true;
    }
}
