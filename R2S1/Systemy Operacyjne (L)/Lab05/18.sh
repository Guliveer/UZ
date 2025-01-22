# Napisać skrypt wykonujący kopię zapasową danego katalogu. Kopie powinny znajdować się w katalogu `kopie_projektow`. Każda nowo utworzona kopia ma znajdować się w odrębnym katalogu o nazwie zawierającej datę wykonania kopii. Skrypt ma sprawdzać czy katalog z kopiami istnieje. Jeżeli nie, to skrypt ma utworzyć taki katalog. Katalog do archiwizacji ma być podany jako parametr wejściowy. Skrypt ma również sprawdzać poprawność składni wywołania.

#!/bin/bash

if [ $# -ne 1 ]; then
	echo "Niepoprawne wywołanie skryptu. Poprawne wywołanie: $0 katalog"
	exit 1
fi

if [ ! -d kopie_projektow ]; then	
	mkdir kopie_projektow
fi

if [ ! -d $1 ]; then
	echo "Podany katalog nie istnieje"
	exit 1
fi

cp -r $1 $(date +%Y-%m-%d)

exit 0