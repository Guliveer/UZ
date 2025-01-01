# Napisać skrypt, zmieniający wszystkie nazwy plików zawierające wielkie litery na małe.

#!/bin/bash

for file in *
do
	if [[ $file =~ [A-Z] ]]
	then
		mv $file $(echo $file | tr 'A-Z' 'a-z')
	fi
done