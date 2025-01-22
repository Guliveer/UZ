# Napisać skrypt wyświetlający wszystkie pliki o rozszerzeniu conf, które zaczynają się od _a_, _b_, _c_ lub _d_ i znajdują się w katalogu `/etc`. Wydruk ma być w postaci

# Plik1 : /etc/asound.conf
# Plik2 : /etc/cas.conf
# Plik3 : /etc/cups.conf

#!/bin/bash

counter=1
for file in /etc/{a,b,c,d}*.conf
do
	echo "Plik$counter : $file"
	counter=$((counter+1))
done
