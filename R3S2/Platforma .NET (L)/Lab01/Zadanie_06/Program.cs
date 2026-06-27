using MojaLibrary;

Console.WriteLine("=== Zadanie 06: Korzystanie z biblioteki DLL ===\n");

// Silnia
Console.WriteLine("--- Silnia ---");
for (int i = 0; i <= 10; i++)
    Console.WriteLine($"  {i}! = {Matematyka.Silnia(i)}");

// Liczby pierwsze
Console.WriteLine("\n--- Liczby pierwsze od 1 do 50 ---");
var pierwsze = Enumerable.Range(1, 50).Where(Matematyka.CzyPierwsza);
Console.WriteLine($"  {string.Join(", ", pierwsze)}");

// Pole kola
Console.WriteLine("\n--- Pole kola ---");
double[] promienie = [1, 2.5, 5, 10];
foreach (var r in promienie)
    Console.WriteLine($"  r = {r,5:F1}  =>  pole = {Matematyka.PoleKola(r):F4}");
