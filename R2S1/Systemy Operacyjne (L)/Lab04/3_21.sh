#!/bin/bash

# Napisać skrypt który utworzy grupę o nazwie studenci, utworzy konta studenckie na podstawie listy zapisanej w postaci pliku tekstowego oraz przydzieli do grupy studenci wszystkie konta studenckie.
# Skrypt ma sprawdzać czy grupa już istnieje.
# Skrypt ma również sprawdzać czy konto o podanej nazwie nie zostało już wcześniej utworzone.

if [ ! -f studenci.txt ]; then
	echo "Brak pliku studenci.txt"
	exit 1
fi

if getent group studenci > /dev/null 2>&1; then
	echo "Grupa studenci już istnieje"
	exit 1
else 
	groupadd studenci
fi

while read line; do
	if getent passwd $line > /dev/null 2>&1; then
		echo "Konto o nazwie $line już istnieje"
	else
		useradd -m -s /bin/bash -G studenci $line
	fi
done < studenci.txt

exit 0