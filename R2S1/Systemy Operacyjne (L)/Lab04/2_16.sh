# Napisać skrypt uruchamiający 20 poleceń 'sleep n'. Zatrzymać wszystkie uruchomione polecenia za pomocą 'pkill'.

#!/bin/bash

for i in {1..20}
do
	sleep $i &
done

pkill sleep

exit 0