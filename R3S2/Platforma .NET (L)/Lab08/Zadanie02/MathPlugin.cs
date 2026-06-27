using System.ComponentModel;
using Microsoft.SemanticKernel;

public sealed class MathPlugin
{
    [KernelFunction, Description("Dodaje dwie liczby i zwraca wynik.")]
    public double Dodaj(
        [Description("Pierwsza liczba")] double a,
        [Description("Druga liczba")] double b) => a + b;

    [KernelFunction, Description("Odejmuje drugą liczbę od pierwszej.")]
    public double Odejmij(
        [Description("Liczba, od której odejmujemy")] double a,
        [Description("Liczba odejmowana")] double b) => a - b;

    [KernelFunction, Description("Mnoży dwie liczby i zwraca wynik.")]
    public double Pomnoz(
        [Description("Pierwsza liczba")] double a,
        [Description("Druga liczba")] double b) => a * b;

    [KernelFunction, Description("Dzieli pierwszą liczbę przez drugą. Zwraca błąd przy dzieleniu przez zero.")]
    public string Podziel(
        [Description("Liczba do podzielenia")] double a,
        [Description("Dzielnik")] double b)
        => b == 0 ? "Błąd: dzielenie przez zero" : (a / b).ToString();
}
