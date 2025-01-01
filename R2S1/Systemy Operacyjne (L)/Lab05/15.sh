# Napisać skrypt sprawdzający, czy w systemie istnieją użytkownicy nie posiadający hasła. Po znalezieniu takiego użytkownika skrypt powinien wysłać do niego list z informacją.

#!/bin/bash

for user in $(cat /etc/passwd | cut -d: -f1)
do
	if [ -z $(cat /etc/shadow | grep ^$user: | cut -d: -f2) ]
	then
		echo "Użytkownik $user nie posiada hasła"
		echo "Wysyłam maila do $user"
	fi
done