# Napisać skrypt sprawdzający czy system wykorzystuje pamięć wirtualną. Jeżeli tak to podaje informację o rozmiarze pamięci wirtualnej.

#!/bin/bash

if [ -f /proc/meminfo ]; then
	echo "System wykorzystuje pamięć wirtualną."
	cat /proc/meminfo | grep -i swap
else
	echo "System nie wykorzystuje pamięci wirtualnej."
fi

exit 0
