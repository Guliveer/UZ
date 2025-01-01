# Zmienić skrypty konfiguracyjne tak, aby w każdy wtorek system przypominał użytkownikowi o zajęciach z systemów operacyjnych. Jeżeli użytkownik zaloguje się w trakcie zajęć komunikat powinien informować, że zajęcia już trwają.

#!/bin/bash

# Sprawdzanie czy dzisiaj jest wtorek
if [ $(date +%u) -eq 2 ]; then
	# Sprawdzanie czy jest godzina zajęć
	if [ $(date +%H:%M) \> 7:30 ] && [ $(date +%H:%M) \< 9:00 ]; then
		echo "Zajęcia z systemów operacyjnych trwają!"
	else
		echo "Dzisiaj są zajęcia z systemów operacyjnych!"
	fi
fi

exit 0