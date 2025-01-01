# Napisać skrypt wyświetlający listę procesów podanego użytkownika.

#!/bin/bash

if [ $# -ne 1 ]; then
	echo "Nie podano użytkownika"
	exit 1
fi

ps -u $1