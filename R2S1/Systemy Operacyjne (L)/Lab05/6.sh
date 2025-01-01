# Zmodyfikować skrypt szukaj utworzony w poprzednim zadaniu tak, aby dał użytkownikom opcję umożliwiającą podanie katalogu od którego rozpocząć poszukiwania, użyj składni: `szukaj plik [katalog]`
#
# Skrypt powinien sprawdzać:
# - (a) liczbę argumentów (jeśli nie podano nazwy katalogu, przeszukiwanie ma rozpocząć się od katalogu bieżącego);
# - (b) istnienie podanego katalogu (jeżeli nie istnieje, wyświetlić komunikat o błędzie i zakończyć).

#!/bin/bash

# Sprawdzamy, czy użytkownik podał nazwę pliku
if [ -z "$1" ]; then
    echo "Błąd: Musisz podać nazwę pliku do wyszukania."
    echo "Użycie: $0 <nazwa_pliku> [katalog]"
    exit 1
fi

# Sprawdzamy, czy podano drugi argument (katalog)
if [ -z "$2" ]; then
    katalog="."  # Jeśli nie podano katalogu, używamy katalogu bieżącego
else
    katalog="$2"  # Jeśli podano katalog, przypisujemy go do zmiennej
fi

# Sprawdzamy, czy podany katalog istnieje
if [ ! -d "$katalog" ]; then
    echo "Błąd: Podany katalog '$katalog' nie istnieje."
    exit 1
fi

# Używamy polecenia find, aby wyszukać plik w wybranym katalogu
find "$katalog" -type f -name "$1" 2>/dev/null