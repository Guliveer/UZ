namespace MojaLibrary;

public static class Matematyka
{
    public static double Silnia(int n)
    {
        if (n < 0) throw new ArgumentException("Liczba nie moze byc ujemna", nameof(n));
        double wynik = 1;
        for (int i = 2; i <= n; i++)
            wynik *= i;
        return wynik;
    }

    public static bool CzyPierwsza(int n)
    {
        if (n < 2) return false;
        for (int i = 2; i <= Math.Sqrt(n); i++)
            if (n % i == 0) return false;
        return true;
    }

    public static double PoleKola(double promien)
    {
        if (promien < 0) throw new ArgumentException("Promien nie moze byc ujemny", nameof(promien));
        return Math.PI * promien * promien;
    }
}
