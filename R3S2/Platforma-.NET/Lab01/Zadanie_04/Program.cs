// Zadanie 04 - Odpowiednik polecenia ls / dir (bez rekurencji)

var showAll = false;
var longFormat = false;
var sortBySize = false;
var sortByDate = false;
string? targetPath = null;

foreach (var arg in args)
    if (arg.StartsWith('-') && arg.Length > 1 && arg[1] != '-')
    {
        foreach (var flag in arg[1..])
            switch (flag)
            {
                case 'a': showAll = true; break;
                case 'l': longFormat = true; break;
                case 's': sortBySize = true; break;
                case 't': sortByDate = true; break;
                case 'h':
                    PrintUsage();
                    return 0;
                default:
                    Error($"Nieznana flaga: -{flag}");
                    return 1;
            }
    }
    else if (arg is "--help")
    {
        PrintUsage();
        return 0;
    }
    else if (targetPath is null)
    {
        targetPath = arg;
    }
    else
    {
        Error("Podano wiecej niz jedna sciezke.");
        return 1;
    }

targetPath ??= Directory.GetCurrentDirectory();

if (!Directory.Exists(targetPath))
{
    Error($"Katalog nie istnieje: {targetPath}");
    return 1;
}

var dirInfo = new DirectoryInfo(targetPath);

var entries = dirInfo.GetFileSystemInfos()
    .Where(e => showAll || !e.Attributes.HasFlag(FileAttributes.Hidden))
    .ToList();

if (sortBySize)
    entries = entries
        .OrderByDescending(e => e is FileInfo f ? f.Length : -1)
        .ThenBy(e => e.Name)
        .ToList();
else if (sortByDate)
    entries = entries
        .OrderByDescending(e => e.LastWriteTime)
        .ToList();
else
    entries = entries
        .OrderBy(e => e is DirectoryInfo ? 0 : 1)
        .ThenBy(e => e.Name, StringComparer.OrdinalIgnoreCase)
        .ToList();

WriteColor($" {dirInfo.FullName}", ConsoleColor.White);
Console.WriteLine();

if (entries.Count == 0)
{
    WriteColor("  (katalog pusty)", ConsoleColor.DarkGray);
    Console.WriteLine();
    return 0;
}

if (longFormat)
    PrintLongFormat(entries);
else
    PrintCompactFormat(entries);

return 0;

static void PrintLongFormat(List<FileSystemInfo> entries)
{
    var maxNameLen = entries.Max(e => e.Name.Length);
    long totalSize = 0;
    var fileCount = 0;
    var dirCount = 0;

    Console.ForegroundColor = ConsoleColor.DarkGray;
    Console.WriteLine($"  {"Uprawnienia",-12} {"Rozmiar",10}  {"Zmodyfikowano",-20}  Nazwa");
    Console.WriteLine(
        $"  {new string('-', 12)} {new string('-', 10)}  {new string('-', 20)}  {new string('-', Math.Min(maxNameLen, 40))}");
    Console.ForegroundColor = ConsoleColor.Gray;

    foreach (var entry in entries)
    {
        var isDir = entry is DirectoryInfo;
        var perms = FormatPermissions(entry.Attributes);
        var size = isDir ? "-" : FormatSize(((FileInfo)entry).Length);
        var date = entry.LastWriteTime.ToString("yyyy-MM-dd HH:mm");
        var name = entry.Name;
        var nameColor = GetEntryColor(entry);

        if (!isDir)
        {
            totalSize += ((FileInfo)entry).Length;
            fileCount++;
        }
        else
        {
            dirCount++;
        }

        Console.Write($"  {perms,-12} ");
        Console.Write($"{size,10}  ");
        Console.Write($"{date,-20}  ");
        WriteColor(isDir ? name + "/" : name, nameColor);
    }

    Console.WriteLine();
    Console.ForegroundColor = ConsoleColor.DarkGray;
    Console.WriteLine($"  {dirCount} katalogow, {fileCount} plikow ({FormatSize(totalSize)})");
    Console.ForegroundColor = ConsoleColor.Gray;
    Console.WriteLine();
}

static void PrintCompactFormat(List<FileSystemInfo> entries)
{
    var maxLen = entries.Max(e => e.Name.Length) + 3;
    var columns = Math.Max(1, (Console.WindowWidth - 2) / maxLen);

    var col = 0;
    foreach (var entry in entries)
    {
        var isDir = entry is DirectoryInfo;
        var name = isDir ? entry.Name + "/" : entry.Name;
        var color = GetEntryColor(entry);

        Console.Write("  ");
        var prev = Console.ForegroundColor;
        Console.ForegroundColor = color;
        Console.Write(name.PadRight(maxLen));
        Console.ForegroundColor = prev;

        col++;
        if (col >= columns)
        {
            Console.WriteLine();
            col = 0;
        }
    }

    if (col > 0) Console.WriteLine();
    Console.WriteLine();
}

static string FormatPermissions(FileAttributes attr)
{
    return string.Concat(
        attr.HasFlag(FileAttributes.Directory) ? "d" : ".",
        attr.HasFlag(FileAttributes.ReadOnly) ? "r" : "w",
        attr.HasFlag(FileAttributes.Hidden) ? "h" : ".",
        attr.HasFlag(FileAttributes.System) ? "s" : ".",
        attr.HasFlag(FileAttributes.Archive) ? "a" : "."
    );
}

static string FormatSize(long bytes)
{
    return bytes switch
    {
        >= 1L << 30 => $"{bytes / (double)(1L << 30):F1} G",
        >= 1L << 20 => $"{bytes / (double)(1L << 20):F1} M",
        >= 1L << 10 => $"{bytes / (double)(1L << 10):F1} K",
        _ => $"{bytes} B"
    };
}

static ConsoleColor GetEntryColor(FileSystemInfo entry)
{
    return entry switch
    {
        DirectoryInfo => ConsoleColor.Blue,
        FileInfo f when f.Extension is ".exe" or ".sh" or ".bat" => ConsoleColor.Green,
        FileInfo f when f.Extension is ".dll" or ".so" or ".dylib" => ConsoleColor.DarkYellow,
        FileInfo f when f.Extension is ".zip" or ".tar" or ".gz" => ConsoleColor.Red,
        FileInfo f when f.Extension is ".cs" or ".csproj" or ".sln" => ConsoleColor.Cyan,
        FileInfo f when f.Name.StartsWith('.') => ConsoleColor.DarkGray,
        _ => ConsoleColor.Gray
    };
}

static void Error(string message)
{
    var prev = Console.ForegroundColor;
    Console.ForegroundColor = ConsoleColor.Red;
    Console.Error.WriteLine($"  Blad: {message}");
    Console.ForegroundColor = prev;
    Console.Error.WriteLine("  Uzyj --help aby zobaczyc dostepne opcje.");
}

static void WriteColor(string text, ConsoleColor color)
{
    var prev = Console.ForegroundColor;
    Console.ForegroundColor = color;
    Console.WriteLine(text);
    Console.ForegroundColor = prev;
}

static void PrintUsage()
{
    Console.WriteLine();
    Console.WriteLine("Uzycie: dotnet run -- [opcje] [sciezka]");
    Console.WriteLine();
    Console.WriteLine("Opcje:");
    Console.WriteLine("  -a          Pokaz ukryte pliki i katalogi");
    Console.WriteLine("  -l          Format dlugi (uprawnienia, rozmiar, data)");
    Console.WriteLine("  -s          Sortuj wg rozmiaru (malejaco)");
    Console.WriteLine("  -t          Sortuj wg daty modyfikacji (najnowsze)");
    Console.WriteLine("  -h, --help  Wyswietl pomoc");
    Console.WriteLine();
    Console.WriteLine("Flagi mozna laczyc: -la, -las, -lt");
    Console.WriteLine();
    Console.WriteLine("Przyklady:");
    Console.WriteLine("  dotnet run -- -la /home       # pelna lista z ukrytymi");
    Console.WriteLine("  dotnet run -- -ls             # biezacy katalog wg rozmiaru");
    Console.WriteLine("  dotnet run -- -t ~/projekty   # najnowsze pliki na gorze");
    Console.WriteLine();
}