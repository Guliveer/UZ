# Napisać skrypt czyszczący pamięć podręczną. Wskazówka: wykorzystać plik drop caches. Rozszerzyć funkcjonalność skryptu na czyszczenie innych elementów systemu, np. buforów, czy pamięci wirtualnej.

#!/bin/bash

echo "Czyszczenie pamięci podręcznej"
echo "1. Pamięć podręczna"
echo "2. Bufory"
echo "3. Pamięć wirtualna"
echo "4. Wszystko"
read -p "Wybierz opcję: " option

case $option in
	1)
		sudo echo 1 > /proc/sys/vm/drop_caches
		;;
	2)
		sudo echo 2 > /proc/sys/vm/drop_caches
		;;
	3)
		sudo echo 3 > /proc/sys/vm/drop_caches
		;;
	4)
		sudo echo 3 > /proc/sys/vm/drop_caches
		;;
	*)
		echo "Nieprawidłowa opcja"
		sleep 1
		exec "$0"
		;;
esac
echo "Pamięć została wyczyszczona"

exit 0