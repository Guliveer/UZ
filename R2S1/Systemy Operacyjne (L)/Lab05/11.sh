# Napisać skrypt listing, wyświetlający zawartość podanego katalogu, dodając informację o typach plików, np.:

# $ listing
# tmp -katalog
# Mail -katalog
# list.txt -plik
# .profile -plik
# $ _

#!/bin/bash

for file in *; do
	if [ -d $file ]; then
		echo "$file -katalog"
	else
		echo "$file -plik"
	fi
done