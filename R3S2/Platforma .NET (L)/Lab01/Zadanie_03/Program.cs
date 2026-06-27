// Zadanie 03 - Parametry wejsciowe i zmienne srodowiskowe

using System.Collections;

if (args is ["--help" or "-h", ..])
{
    PrintUsage();
    return 0;
}

var command = args.Length > 0 ? args[0].ToLower() : "all";
var filter = args.Length > 1 ? args[1] : null;

switch (command)
{
    case "args" or "all":
        PrintArgs(args);
        if (command == "all") PrintEnv(filter);
        break;
    case "env":
        PrintEnv(filter);
        break;
    default:
        WriteColor($"Nieznane polecenie: '{command}'", ConsoleColor.Red);
        PrintUsage();
        return 1;
}

return 0;

static void PrintArgs(string[] args)
{
    WriteHeader("Parametry wejściowe");

    if (args.Length == 0)
    {
        WriteColor("  (brak argumentów)", ConsoleColor.DarkGray);
        Console.WriteLine();
        return;
    }

    Console.WriteLine($"  Liczba: {args.Length}");
    Console.WriteLine();

    for (var i = 0; i < args.Length; i++)
    {
        Console.Write($"  argv[{i}] = ");
        WriteColor($"\"{args[i]}\"", ConsoleColor.Cyan);
    }

    Console.WriteLine();
}

static void PrintEnv(string? filter)
{
    WriteHeader("Zmienne środowiskowe");

    var vars = Environment.GetEnvironmentVariables()
        .Cast<DictionaryEntry>()
        .Select(e => (Key: (string)e.Key, Value: (string?)e.Value))
        .Where(e => filter is null
                    || e.Key.Contains(filter, StringComparison.OrdinalIgnoreCase)
                    || (e.Value?.Contains(filter, StringComparison.OrdinalIgnoreCase) ?? false))
        .OrderBy(e => e.Key, StringComparer.OrdinalIgnoreCase)
        .ToList();

    if (filter is not null)
    {
        Console.Write("  Filtr: ");
        WriteColor($"\"{filter}\"", ConsoleColor.Yellow);
    }

    Console.WriteLine($"  Znaleziono: {vars.Count} zmiennych");
    Console.WriteLine();

    var maxKeyLen = vars.Count > 0 ? Math.Min(vars.Max(v => v.Key.Length), 30) : 0;

    foreach (var (key, value) in vars)
    {
        var displayKey = key.Length > 30 ? key[..27] + "..." : key;
        var displayVal = Truncate(value ?? "(null)", Console.WindowWidth - maxKeyLen - 8);

        WriteColor($"  {displayKey.PadRight(maxKeyLen)}", ConsoleColor.Green);
        Console.Write(" = ");

        if (key.Contains("PATH", StringComparison.OrdinalIgnoreCase))
            PrintPathVar(displayVal, maxKeyLen);
        else
            Console.WriteLine(displayVal);
    }

    Console.WriteLine();
}

static void PrintPathVar(string value, int indent)
{
    var separator = OperatingSystem.IsWindows() ? ';' : ':';
    var parts = value.Split(separator);

    if (parts.Length <= 1)
    {
        Console.WriteLine(value);
        return;
    }

    Console.WriteLine();
    foreach (var part in parts)
    {
        if (string.IsNullOrWhiteSpace(part)) continue;
        Console.Write(new string(' ', indent + 5));
        WriteColor(part, ConsoleColor.DarkCyan);
    }
}

static void WriteHeader(string title)
{
    Console.WriteLine();
    WriteColor($"  -- {title} ", ConsoleColor.White);
    Console.WriteLine(new string('-', Math.Max(0, 60 - title.Length)));
    Console.WriteLine();
}

static void WriteColor(string text, ConsoleColor color)
{
    var prev = Console.ForegroundColor;
    Console.ForegroundColor = color;
    Console.WriteLine(text);
    Console.ForegroundColor = prev;
}

static string Truncate(string value, int maxLen)
{
    maxLen = Math.Max(maxLen, 20);
    return value.Length > maxLen ? value[..(maxLen - 3)] + "..." : value;
}

static void PrintUsage()
{
    Console.WriteLine();
    Console.WriteLine("Użycie: dotnet run -- [polecenie] [filtr]");
    Console.WriteLine();
    Console.WriteLine("Polecenia:");
    Console.WriteLine("  all            Wyświetl argumenty i zmienne środowiskowe (domyślne)");
    Console.WriteLine("  args           Wyświetl tylko argumenty programu");
    Console.WriteLine("  env [filtr]    Wyświetl zmienne środowiskowe (opcjonalny filtr)");
    Console.WriteLine("  --help, -h     Wyświetl tę pomoc");
    Console.WriteLine();
    Console.WriteLine("Przykłady:");
    Console.WriteLine("  dotnet run -- env PATH       # zmienne zawierające 'PATH'");
    Console.WriteLine("  dotnet run -- env dotnet     # zmienne związane z .NET");
    Console.WriteLine("  dotnet run -- args foo bar   # wyświetl przekazane argumenty");
    Console.WriteLine();
}