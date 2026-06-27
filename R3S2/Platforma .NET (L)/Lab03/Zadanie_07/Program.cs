using System;
using System.Collections;
using System.Collections.Generic;

namespace Zadanie_07
{
    class MojaKolekcja<T> : IEnumerable<T>
    {
        private T[] elementy;
        private int licznik = 0;

        public MojaKolekcja(int pojemnosc)
        {
            elementy = new T[pojemnosc];
        }

        public int Licznik { get { return licznik; } }

        public void Dodaj(T element)
        {
            if (licznik >= elementy.Length)
            {
                T[] nowa = new T[elementy.Length * 2];
                Array.Copy(elementy, nowa, licznik);
                elementy = nowa;
            }
            elementy[licznik++] = element;
        }

        public T this[int indeks]
        {
            get
            {
                if (indeks < 0 || indeks >= licznik)
                    throw new IndexOutOfRangeException("Indeks poza zakresem.");
                return elementy[indeks];
            }
        }

        public IEnumerator<T> GetEnumerator()
        {
            return new MojEnumerator(this);
        }

        IEnumerator IEnumerable.GetEnumerator()
        {
            return GetEnumerator();
        }

        private class MojEnumerator : IEnumerator<T>
        {
            private MojaKolekcja<T> kolekcja;
            private int pozycja = -1;

            public MojEnumerator(MojaKolekcja<T> kolekcja)
            {
                this.kolekcja = kolekcja;
            }

            public T Current
            {
                get { return kolekcja.elementy[pozycja]; }
            }

            object IEnumerator.Current
            {
                get { return Current; }
            }

            public bool MoveNext()
            {
                pozycja++;
                return pozycja < kolekcja.licznik;
            }

            public void Reset()
            {
                pozycja = -1;
            }

            public void Dispose() { }
        }
    }

    internal class Program
    {
        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;

            Console.WriteLine("=== Własna kolekcja zgodna z foreach ===");
            Console.WriteLine();

            // Kolekcja liczb
            var liczby = new MojaKolekcja<int>(4);
            liczby.Dodaj(10);
            liczby.Dodaj(20);
            liczby.Dodaj(30);
            liczby.Dodaj(40);
            liczby.Dodaj(50); // wymusza rozszerzenie tablicy

            Console.WriteLine("Liczby w kolekcji (foreach):");
            foreach (int n in liczby)
                Console.WriteLine($"  {n}");

            Console.WriteLine($"Liczba elementów: {liczby.Licznik}");
            Console.WriteLine($"Element [2]: {liczby[2]}");

            Console.WriteLine();

            // Kolekcja stringów
            var imiona = new MojaKolekcja<string>(2);
            imiona.Dodaj("Anna");
            imiona.Dodaj("Jan");
            imiona.Dodaj("Katarzyna");
            imiona.Dodaj("Piotr");

            Console.WriteLine("Imiona w kolekcji (foreach):");
            foreach (string imie in imiona)
                Console.WriteLine($"  {imie}");

            Console.ReadKey();
        }
    }
}
