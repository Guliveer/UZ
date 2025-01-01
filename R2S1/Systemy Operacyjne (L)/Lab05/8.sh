# Napisać skrypt datapl wyświetlający datę pobieraną z systemu, w języku polskim np.:
# $ datapl
# Poniedziałek, 3 styczeń 2005

#!/bin/bash

# Ustawienie lokalizacji na polski (pl_PL.UTF-8)
export LANG=pl_PL.UTF-8

# Wyświetlenie daty w formacie: "Dzień, dd miesiąc RRRR"
date "+%A, %d %B %Y"
