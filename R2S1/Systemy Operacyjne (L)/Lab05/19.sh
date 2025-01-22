# Napisać skrypt dokonujący operacji arytmetycznej na dwóch argumentach. Możliwe do wykonania operacje powinny obejmować: dodawanie, odejmowanie, mnożenie i dzielenie. Argumenty powinny zostać podane do skryptu jako parametry wejściowe. Wywołanie skryptu powinno wyglądać w podany sposób. Skrypt powinien kontrolować liczbę podanych parametrów i wypisywać odpowiednie komunikaty na ich brak:
# 
# - (a) w przypadku braku pierwszego parametru: „Brak parametrów wejściowych”,
# - (b) w przypadku braku drugiego parametru: „Brak operatora”,
# - (c) w przypadku braku trzeciego parametru: „Brak drugiego argumentu”. Skrypt powinien działać tak długo, aż użytkownik nie wyrazi chęci zakończenia obliczeń.

#!/bin/bash

if [ $# -ne 3 ]; then
	echo "Brak parametrów wejściowych"
	exit 1
fi

if [ $2 != "+" ] && [ $2 != "-" ] && [ $2 != "*" ] && [ $2 != "/" ]; then
	echo "Brak operatora"
	exit 1
fi

if [ $# -ne 3 ]; then
	echo "Brak drugiego argumentu"
	exit 1
fi

if [ $2 == "+" ]; then
	echo $(($1+$3))
elif [ $2 == "-" ]; then
	echo $(($1-$3))
elif [ $2 == "*" ]; then
	echo $(($1*$3))
elif [ $2 == "/" ]; then
	echo $(($1/$3))
fi
