using System;
using System.Collections.Generic;
using System.Linq;

namespace Zadanie_04
{
    class Osoba
    {
        public string Imie { get; set; }
        public string Nazwisko { get; set; }
        public string Adres { get; set; }
        public string Telefon { get; set; }

        public override string ToString()
        {
            return $"{Imie} {Nazwisko} | Adres: {Adres} | Tel: {Telefon}";
        }
    }

    internal class Program
    {
        static Dictionary<int, Osoba> baza = new Dictionary<int, Osoba>();
        static int nastepneId = 1;

        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;

            bool dzialaj = true;
            while (dzialaj)
            {
                Console.WriteLine();
                Console.WriteLine("=== Baza danych osób ===");
                Console.WriteLine("1. Dodaj osobę");
                Console.WriteLine("2. Wyświetl wszystkie osoby");
                Console.WriteLine("3. Wyszukaj po nazwisku");
                Console.WriteLine("4. Edytuj osobę");
                Console.WriteLine("5. Usuń osobę");
                Console.WriteLine("6. Sortuj wg nazwiska");
                Console.WriteLine("0. Wyjście");
                Console.Write("Wybór: ");

                switch (Console.ReadLine())
                {
                    case "1": DodajOsobe(); break;
                    case "2": WyswietlWszystkie(); break;
                    case "3": WyszukajPoNazwisku(); break;
                    case "4": EdytujOsobe(); break;
                    case "5": UsunOsobe(); break;
                    case "6": SortujWgNazwiska(); break;
                    case "0": dzialaj = false; break;
                    default: Console.WriteLine("Nieprawidłowy wybór."); break;
                }
            }
        }

        static void DodajOsobe()
        {
            var osoba = new Osoba();
            Console.Write("Imię: ");
            osoba.Imie = Console.ReadLine();
            Console.Write("Nazwisko: ");
            osoba.Nazwisko = Console.ReadLine();
            Console.Write("Adres: ");
            osoba.Adres = Console.ReadLine();
            Console.Write("Telefon: ");
            osoba.Telefon = Console.ReadLine();

            baza[nastepneId] = osoba;
            Console.WriteLine($"Dodano osobę z ID: {nastepneId}");
            nastepneId++;
        }

        static void WyswietlWszystkie()
        {
            if (baza.Count == 0)
            {
                Console.WriteLine("Baza jest pusta.");
                return;
            }

            Console.WriteLine($"{"ID",-5} {"Imię",-15} {"Nazwisko",-15} {"Adres",-25} {"Telefon",-15}");
            Console.WriteLine(new string('-', 75));
            foreach (var wpis in baza)
            {
                var o = wpis.Value;
                Console.WriteLine($"{wpis.Key,-5} {o.Imie,-15} {o.Nazwisko,-15} {o.Adres,-25} {o.Telefon,-15}");
            }
        }

        static void WyszukajPoNazwisku()
        {
            Console.Write("Podaj nazwisko (lub fragment): ");
            string fraza = Console.ReadLine().ToLower();

            var wyniki = baza.Where(w => w.Value.Nazwisko.ToLower().Contains(fraza)).ToList();

            if (wyniki.Count == 0)
            {
                Console.WriteLine("Nie znaleziono.");
                return;
            }

            foreach (var w in wyniki)
                Console.WriteLine($"  [{w.Key}] {w.Value}");
        }

        static void EdytujOsobe()
        {
            Console.Write("Podaj ID osoby do edycji: ");
            int id;
            if (!int.TryParse(Console.ReadLine(), out id) || !baza.ContainsKey(id))
            {
                Console.WriteLine("Nie znaleziono osoby o takim ID.");
                return;
            }

            var osoba = baza[id];
            Console.WriteLine($"Edytujesz: {osoba}");
            Console.WriteLine("(Enter = zostaw bez zmian)");

            Console.Write($"Imię [{osoba.Imie}]: ");
            string val = Console.ReadLine();
            if (!string.IsNullOrEmpty(val)) osoba.Imie = val;

            Console.Write($"Nazwisko [{osoba.Nazwisko}]: ");
            val = Console.ReadLine();
            if (!string.IsNullOrEmpty(val)) osoba.Nazwisko = val;

            Console.Write($"Adres [{osoba.Adres}]: ");
            val = Console.ReadLine();
            if (!string.IsNullOrEmpty(val)) osoba.Adres = val;

            Console.Write($"Telefon [{osoba.Telefon}]: ");
            val = Console.ReadLine();
            if (!string.IsNullOrEmpty(val)) osoba.Telefon = val;

            Console.WriteLine("Zaktualizowano.");
        }

        static void UsunOsobe()
        {
            Console.Write("Podaj ID osoby do usunięcia: ");
            int id;
            if (!int.TryParse(Console.ReadLine(), out id) || !baza.ContainsKey(id))
            {
                Console.WriteLine("Nie znaleziono osoby o takim ID.");
                return;
            }

            Console.WriteLine($"Usuwam: {baza[id]}");
            baza.Remove(id);
            Console.WriteLine("Usunięto.");
        }

        static void SortujWgNazwiska()
        {
            if (baza.Count == 0)
            {
                Console.WriteLine("Baza jest pusta.");
                return;
            }

            var posortowane = baza.OrderBy(w => w.Value.Nazwisko).ThenBy(w => w.Value.Imie);

            Console.WriteLine("--- Posortowane wg nazwiska ---");
            foreach (var w in posortowane)
                Console.WriteLine($"  [{w.Key}] {w.Value}");
        }
    }
}
