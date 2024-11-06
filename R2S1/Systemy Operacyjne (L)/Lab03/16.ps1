# Definiowanie listy dostępnych kolorów
$colors = [System.Enum]::GetValues([System.ConsoleColor]) 

# Pętla generująca 10 losowych liczb i wyświetlająca je w losowych kolorach
1..10 | ForEach-Object {
    # Generowanie losowej liczby
    $randomNumber = Get-Random -Minimum 1 -Maximum 100
    
    # Wybieranie losowego koloru z listy dostępnych kolorów
    $randomColor = $colors | Get-Random
    
    # Ustawienie koloru tekstu na losowy
    $Host.UI.RawUI.ForegroundColor = $randomColor
    
    # Wyświetlanie liczby w losowym kolorze
    Write-Host $randomNumber
    
    # Przywracanie domyślnego koloru konsoli (jeśli chcesz przywrócić po każdej liczbie)
    $Host.UI.RawUI.ForegroundColor = 'White'
}
