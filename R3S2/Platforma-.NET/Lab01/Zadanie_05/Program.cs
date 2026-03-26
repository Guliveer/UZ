// Zadanie 05 - Zamiana slow w strumieniu (stdin -> stdout)

using System.Text.RegularExpressions;

var ignoreCase = false;
var wholeWord = false;
var showLineNums = false;
var countOnly = false;
var positional = new List<string>();

foreach (var arg in args)
{
    if (arg is "--help")
    {
        PrintUsage();
        return 0;
    }

    if (arg.StartsWith('-') && arg.Length > 1 && arg[1] != '-')
        foreach (var flag in arg[1..])
            switch (flag)
            {
                case 'i': ignoreCase = true; break;
                case 'w': wholeWord = true; break;
                case 'n': showLineNums = true; break;
                case 'c': countOnly = true; break;
                case 'h':
                    PrintUsage();
                    return 0;
                default:
                    Error($"Nieznana flaga: -{flag}");
                    return 1;
            }
    else if (arg is "--ignore-case") ignoreCase = true;
    else if (arg is "--whole-word") wholeWord = true;
    else if (arg is "--line-numbers") showLineNums = true;
    else if (arg is "--count") countOnly = true;
    else
        positional.Add(arg);
}

if (positional.Count < 2)
{
    Error("Wymagane dwa argumenty pozycyjne: <szukane> <zamiana>");
    PrintUsage();
    return 1;
}

var search = positional[0];
var replace = positional[1];

if (search.Length == 0)
{
    Error("Szukane slowo nie moze byc puste.");
    return 1;
}

var regexOpts = RegexOptions.Compiled | RegexOptions.CultureInvariant;
if (ignoreCase) regexOpts |= RegexOptions.IgnoreCase;

var pattern = wholeWord
    ? $@"\b{Regex.Escape(search)}\b"
    : Regex.Escape(search);

var regex = new Regex(pattern, regexOpts);

if (!Console.IsInputRedirected && !Console.IsOutputRedirected)
{
    Hint("Tryb interaktywny -- wpisuj tekst, Ctrl+D aby zakonczyc.");
    Hint($"Zamiana: \"{search}\" -> \"{replace}\"" +
         $"{(ignoreCase ? " [case-insensitive]" : "")}" +
         $"{(wholeWord ? " [whole-word]" : "")}");
    Console.Error.WriteLine();
}

long lineNum = 0;
long matchCount = 0;
long matchedLines = 0;

using var reader = new StreamReader(Console.OpenStandardInput());
using var writer = new StreamWriter(Console.OpenStandardOutput()) { AutoFlush = false };

while (reader.ReadLine() is { } line)
{
    lineNum++;
    var matches = regex.Matches(line);

    if (matches.Count == 0)
    {
        if (!countOnly) writer.WriteLine(showLineNums ? $"{lineNum,6}: {line}" : line);
        continue;
    }

    matchCount += matches.Count;
    matchedLines++;

    if (!countOnly)
    {
        var result = regex.Replace(line, replace);
        writer.WriteLine(showLineNums ? $"{lineNum,6}: {result}" : result);
    }
}

writer.Flush();

if (!Console.IsErrorRedirected)
{
    var err = Console.Error;
    err.WriteLine();

    var prev = Console.ForegroundColor;
    Console.ForegroundColor = ConsoleColor.DarkGray;

    err.WriteLine("  -- Statystyki ------------------------------------");
    err.WriteLine($"  Przetworzono linii:   {lineNum}");
    err.WriteLine($"  Linii z dopasowaniem: {matchedLines}");
    err.WriteLine($"  Laczna liczba zamian: {matchCount}");
    err.WriteLine($"  Wzorzec:              \"{search}\" -> \"{replace}\"");
    err.WriteLine("  --------------------------------------------------");

    Console.ForegroundColor = prev;
    err.WriteLine();
}

return 0;

static void Error(string message)
{
    var prev = Console.ForegroundColor;
    Console.ForegroundColor = ConsoleColor.Red;
    Console.Error.WriteLine($"  Blad: {message}");
    Console.ForegroundColor = prev;
}

static void Hint(string text)
{
    var prev = Console.ForegroundColor;
    Console.ForegroundColor = ConsoleColor.DarkGray;
    Console.Error.WriteLine($"  {text}");
    Console.ForegroundColor = prev;
}

static void PrintUsage()
{
    var err = Console.Error;
    err.WriteLine();
    err.WriteLine("Uzycie: Zadanie05 [opcje] <szukane> <zamiana> <plik_we >plik_wy");
    err.WriteLine();
    err.WriteLine("Argumenty:");
    err.WriteLine("  szukane              Slowo / fraza do znalezienia");
    err.WriteLine("  zamiana              Tekst zastepujacy");
    err.WriteLine();
    err.WriteLine("Opcje:");
    err.WriteLine("  -i, --ignore-case    Ignoruj wielkosc liter");
    err.WriteLine("  -w, --whole-word     Dopasuj tylko cale slowa (granice \\b)");
    err.WriteLine("  -n, --line-numbers   Dolacz numery linii do wyjscia");
    err.WriteLine("  -c, --count          Tylko statystyki, bez wyjscia");
    err.WriteLine("  -h, --help           Wyswietl pomoc");
    err.WriteLine();
    err.WriteLine("Flagi mozna laczyc: -iw, -iwn");
    err.WriteLine();
    err.WriteLine("Przyklady:");
    err.WriteLine("  Zadanie05 foo bar <in.txt >out.txt          # zamiana podstawowa");
    err.WriteLine("  cat dane.txt | Zadanie05 -iw stary nowy     # case-insensitive, whole-word");
    err.WriteLine("  Zadanie05 -c blad fix <log.txt              # tylko policz wystapienia");
    err.WriteLine("  echo \"Hello World\" | Zadanie05 Hello Czesc  # pipe jednolinijkowy");
    err.WriteLine();
}