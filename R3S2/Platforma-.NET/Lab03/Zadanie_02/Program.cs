using System;
using System.Text.RegularExpressions;

namespace Zadanie_02
{
    internal class Program
    {
        static readonly Regex regexIP = new Regex(
            @"^((25[0-5]|2[0-4]\d|[01]?\d\d?)\.){3}(25[0-5]|2[0-4]\d|[01]?\d\d?)$");

        static readonly Regex regexEmail = new Regex(
            @"^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$");

        static void Main(string[] args)
        {
            Console.OutputEncoding = System.Text.Encoding.UTF8;

            Console.WriteLine("=== Weryfikacja adresu IP ===");
            Console.Write("Podaj adres IP (XXX.XXX.XXX.XXX): ");
            string ip = Console.ReadLine();

            if (regexIP.IsMatch(ip))
                Console.WriteLine($"  Adres IP \"{ip}\" jest poprawny.");
            else
                Console.WriteLine($"  Adres IP \"{ip}\" jest NIEPOPRAWNY.");

            Console.WriteLine();
            Console.WriteLine("=== Weryfikacja adresu e-mail ===");
            Console.Write("Podaj adres e-mail: ");
            string email = Console.ReadLine();

            if (regexEmail.IsMatch(email))
                Console.WriteLine($"  Adres e-mail \"{email}\" jest poprawny.");
            else
                Console.WriteLine($"  Adres e-mail \"{email}\" jest NIEPOPRAWNY.");

            Console.WriteLine();
            Console.WriteLine("--- Testy automatyczne ---");
            TestIP("192.168.1.1");
            TestIP("255.255.255.255");
            TestIP("0.0.0.0");
            TestIP("256.1.1.1");
            TestIP("12.34.56");
            TestIP("192.168.1.1.1");
            TestIP("abc.def.ghi.jkl");

            Console.WriteLine();
            TestEmail("jan@example.com");
            TestEmail("anna.kowalska@firma.pl");
            TestEmail("test@test");
            TestEmail("@brak.pl");
            TestEmail("spacja w@mail.com");

            Console.ReadKey();
        }

        static void TestIP(string ip)
        {
            string wynik = regexIP.IsMatch(ip) ? "OK" : "NIEPOPRAWNY";
            Console.WriteLine($"  IP: {ip,-25} -> {wynik}");
        }

        static void TestEmail(string email)
        {
            string wynik = regexEmail.IsMatch(email) ? "OK" : "NIEPOPRAWNY";
            Console.WriteLine($"  Email: {email,-30} -> {wynik}");
        }
    }
}
