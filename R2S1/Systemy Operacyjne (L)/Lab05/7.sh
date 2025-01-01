# Napisać funkcję, która po wywołaniu będzie pytała, czy użytkownik chce zmienić `PS1` lub `PS2` (znaki zachęty). Jeżeli użytkownik nie życzy sobie zmiany znaku zachęty, powinien wcisnąć `<RETURN>`, jeżeli chce go zmienić, powinien wpisać jego nową wartość.

#!/bin/bash

# Funkcja zmieniająca PS1 i PS2
zmien_zachete() {
    # Zapytanie o zmianę PS1
    echo "Chcesz zmienić znak zachęty PS1? (naciśnij <RETURN>, aby pominąć lub wpisz nową wartość):"
    read nowy_ps1
    if [ -n "$nowy_ps1" ]; then
        PS1="$nowy_ps1"
        echo "Zmieniono PS1 na: $PS1"
    else
        echo "Nie zmieniono PS1."
    fi

    # Zapytanie o zmianę PS2
    echo "Chcesz zmienić znak zachęty PS2? (naciśnij <RETURN>, aby pominąć lub wpisz nową wartość):"
    read nowy_ps2
    if [ -n "$nowy_ps2" ]; then
        PS2="$nowy_ps2"
        echo "Zmieniono PS2 na: $PS2"
    else
        echo "Nie zmieniono PS2."
    fi
}

# Wywołanie funkcji
zmien_zachete