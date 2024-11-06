# Tworzymy katalog "PS" na dysku C:, jeśli jeszcze nie istnieje
$folderPath = "C:\PS"
if (-not (Test-Path -Path $folderPath)) {
    New-Item -Path $folderPath -ItemType Directory
}

# Pobieramy listę procesów posortowaną według zużycia pamięci i wybieramy 10 pierwszych
$topProcesses = Get-Process | Sort-Object WorkingSet -Descending | Select-Object -First 10

# Tworzymy nazwę pliku
$filePath = "$folderPath\Top10MemoryProcesses.txt"

# Zapisujemy informacje o procesach do pliku
$topProcesses | Select-Object Name, Id, @{Name="Memory(GB)"; Expression={"{0:N2}" -f ($_.WorkingSet / 1GB)}} | 
    Out-File -FilePath $filePath

# Informujemy o zakończeniu operacji
Write-Host "Zapisano 10 najbardziej pamięciożernych procesów w pliku: $filePath"
