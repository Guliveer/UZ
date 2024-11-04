param (
    [string]$filePath
)

# Sprawdzenie, czy podano parametr
if (-not $filePath) {
    Write-Host "Proszê podaæ nazwê pliku jako parametr wejœciowy."
    exit
}

# Sprawdzenie, czy plik istnieje
if (-not (Test-Path $filePath)) {
    Write-Host "Podany plik nie istnieje w podanej lokalizacji."
    exit
}

# Wczytanie u¿ytkowników z pliku
$users = Get-Content $filePath

foreach ($user in $users) {
    $fields = $user -split ','

    # Sprawdzenie poprawnoœci danych
    if ($fields.Count -ne 3) {
        Write-Host "Niepoprawny format danych dla: $user"
        continue
    }

    $username = $fields[2]
    $fullName = $fields[1]

    try {
        # Tworzenie konta u¿ytkownika
        net user $username /add /fullname:"$fullName" /active:yes
        Write-Host "Utworzono konto dla: $fullName ($username)"
    } catch {
        Write-Host "B³¹d podczas tworzenia konta dla: $fullName ($username) - $_"
    }
}
