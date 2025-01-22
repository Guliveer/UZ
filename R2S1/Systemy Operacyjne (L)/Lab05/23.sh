# Napisać skrypt sprawdzający temperaturę pracy procesora. Jeśli jest ona większa niż podany przez użytkownika próg to skrypt powinien informować o tym fakcie użytkownika.
# Wskazówka: wykorzystać polecenie sensors. Rozszerzyć funkcjonalność skryptu na sprawdzanie temperatury pracy innych urządzeń.

#!/bin/bash

# Sprawdzenie czy podano argument
if [ $# -eq 0 ]; then
	echo "Nie podano argumentu"
	exit 1
fi

# Sprawdzenie czy podano poprawny argument
if [ $1 -lt 0 ]; then
	echo "Podano złą wartość"
	exit 1
fi

# Wykorzystanie polecenia sensors
sensors | grep $1
if [ $? -eq 0 ]; then
	echo "Temperatura przekroczyła podany próg"
fi

exit 0