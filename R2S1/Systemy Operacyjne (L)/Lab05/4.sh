# Napisać skrypt, który będzie sprawdzał składnie argumentów wywołania, zwracając kod wyjścia równy 0, jeżeli był argument lub kod wyjścia 1, jeśli nie było argumentu.

#!/bin/bash

# Sprawdzenie, czy został przekazany argument
if [ $# -gt 0 ]; then
    # Argumenty zostały przekazane
    echo "Argument został przekazany: $1"
    exit 0
else
    # Brak argumentów
    echo "Brak argumentu"
    exit 1
fi