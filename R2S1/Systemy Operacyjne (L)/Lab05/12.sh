# Napisać skrypt size, który wyświetli, dla podanego katalogu, ilość zajmowanego miejsca przez każdy z podkatalogów w kB np.:

# $ size
# tmp 400 kB
# Mail 38 kB
# $ _

#!/bin/bash

for dir in $(ls -d */); do
	echo -n "$dir "
	du -s $dir | awk '{print $1}' | xargs -I {} echo "{} kB"
done