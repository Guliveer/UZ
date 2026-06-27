using System;
using System.Threading;

namespace Zadanie_05
{
    internal class Program
    {
        static int[] tablica;
        static int[] lokalneMin;
        static int[] lokalneMaks;

        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;

            int rozmiar = 10_000_000;
            int liczbaWatkow = Environment.ProcessorCount;

            Console.WriteLine($"Rozmiar tablicy: {rozmiar:N0}");
            Console.WriteLine($"Liczba wątków:   {liczbaWatkow}");
            Console.WriteLine();

            // Generowanie tablicy
            tablica = new int[rozmiar];
            var rng = new Random(42);
            for (int i = 0; i < rozmiar; i++)
                tablica[i] = rng.Next(int.MinValue, int.MaxValue);

            lokalneMin = new int[liczbaWatkow];
            lokalneMaks = new int[liczbaWatkow];

            // Wyszukiwanie wielowątkowe
            Thread[] watki = new Thread[liczbaWatkow];
            int porcja = rozmiar / liczbaWatkow;

            var start = DateTime.Now;

            for (int i = 0; i < liczbaWatkow; i++)
            {
                int indeksWatku = i;
                int poczatek = i * porcja;
                int koniec = (i == liczbaWatkow - 1) ? rozmiar : poczatek + porcja;

                watki[i] = new Thread(() => SzukajMinMaks(indeksWatku, poczatek, koniec));
                watki[i].Start();
            }

            for (int i = 0; i < liczbaWatkow; i++)
                watki[i].Join();

            int globalMin = lokalneMin[0];
            int globalMaks = lokalneMaks[0];
            for (int i = 1; i < liczbaWatkow; i++)
            {
                if (lokalneMin[i] < globalMin) globalMin = lokalneMin[i];
                if (lokalneMaks[i] > globalMaks) globalMaks = lokalneMaks[i];
            }

            var czas = DateTime.Now - start;

            Console.WriteLine("--- Wyniki wątków ---");
            for (int i = 0; i < liczbaWatkow; i++)
                Console.WriteLine($"  Wątek {i}: min = {lokalneMin[i],15:N0}, maks = {lokalneMaks[i],15:N0}");

            Console.WriteLine();
            Console.WriteLine($"Globalne minimum:  {globalMin:N0}");
            Console.WriteLine($"Globalne maksimum: {globalMaks:N0}");
            Console.WriteLine($"Czas: {czas.TotalMilliseconds:F1} ms");

            // Porównanie z jednowątkowym
            start = DateTime.Now;
            int seqMin = tablica[0], seqMaks = tablica[0];
            for (int i = 1; i < tablica.Length; i++)
            {
                if (tablica[i] < seqMin) seqMin = tablica[i];
                if (tablica[i] > seqMaks) seqMaks = tablica[i];
            }
            var czasSeq = DateTime.Now - start;

            Console.WriteLine();
            Console.WriteLine($"Jednowątkowo: min = {seqMin:N0}, maks = {seqMaks:N0}, czas = {czasSeq.TotalMilliseconds:F1} ms");

            Console.ReadKey();
        }

        static void SzukajMinMaks(int indeks, int poczatek, int koniec)
        {
            int min = tablica[poczatek];
            int maks = tablica[poczatek];

            for (int i = poczatek + 1; i < koniec; i++)
            {
                if (tablica[i] < min) min = tablica[i];
                if (tablica[i] > maks) maks = tablica[i];
            }

            lokalneMin[indeks] = min;
            lokalneMaks[indeks] = maks;

            Console.WriteLine($"  Wątek {indeks} zakończył przeszukiwanie zakresu [{poczatek:N0}..{koniec - 1:N0}]");
        }
    }
}
