# Napisać skrypt o nazwie szukaj, wykorzystujący polecenie `find`, w sposób przyjazny dla użytkownika. Argumentem skryptu ma być nazwa pliku do wyszukania

#!/bin/bash

# Sprawdzamy, czy użytkownik podał argument
if [ -z "$1" ]; then
    echo "Błąd: Musisz podać nazwę pliku do wyszukania."
    echo "Użycie: $0 <nazwa_pliku>"
    exit 1
fi

# Używamy polecenia find, aby wyszukać plik w systemie
find / -type f -name "$1" 2>/dev/null