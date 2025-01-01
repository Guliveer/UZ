# Zmodyfikować skrypt z poprzedniego zadania tak, aby można było wyświetlać datę w różnych formatach, w zależności od podanego przełącznika:
#
# - `-d` — długa data np.: Poniedziałek, 3 styczeń 1999
# - `-k` — krótka informacja, np.: 1999-01-03

#!/bin/bash

# Ustawienie lokalizacji na polski (pl_PL.UTF-8)
export LANG=pl_PL.UTF-8

# Funkcja wyświetlająca długą datę
long_date() {
    date "+%A, %d %B %Y"
}

# Funkcja wyświetlająca krótką datę
short_date() {
    date "+%Y-%m-%d"
}

# Sprawdzanie przełączników
if [ "$1" == "-d" ]; then
    long_date  # Wyświetl długą datę
elif [ "$1" == "-k" ]; then
    short_date  # Wyświetl krótką datę
else
    echo "Użycie: $0 [-d | -k]"
    echo "-d  Długa data (np. Poniedziałek, 3 styczeń 1999)"
    echo "-k  Krótka data (np. 1999-01-03)"
    exit 1
fi