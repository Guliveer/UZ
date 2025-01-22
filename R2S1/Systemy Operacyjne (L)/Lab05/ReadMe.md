# Lab05 [Linux - skrypty powłoki]

### 1. Zapoznać się z podstawowymi konstrukcjami języka powłoki, oraz z grupowaniem komend w plikach (skryptach):

- konstrukcje: `for`, `case`, `if`, `while`, `until`;
- używanie zmiennych;
- wywoływanie skryptów z argumentami;
- korzystanie ze zmiennych środowiska;
- sprawdzanie statusu zakończenia poleceń skryptów;
- używanie znaków specjalnych `;` `&` `( )` `^` `<` `>` `’` `‘` `"` `nowa linia` `tabulator` `spacja`
- funkcje w skryptach;
- wywoływanie innych programów ze skryptów;
- śledzenie wykonywania skryptów — polecenie `set`
- polecenie `test`
- dodatkowe polecenia: `tty`, `id`, `logname`, `basename`, `dirname`, ...
- inne przydatne programy: `awk`, `sed`, ...

> 1. Podstawowe konstrukcje języka powłoki:
>    > - Pętle i warunki:
>    >   > - `for`, `while`, `until` – iteracje.
>    >   > - `if`, `case` – warunki.
>    > - Zmienne: Tworzenie, przypisywanie (`zmienna=wartość`), użycie (`$zmienna`).
>    > - Wywoływanie skryptów z argumentami: Dostęp przez `$1`, `$2`, ...
>    > - Zmienne środowiska: Użycie np. `$PATH`, `$USER`.
>    > - Status zakończenia: `$?` – kod wyjścia ostatniego polecenia.
> 2. Znaki specjalne:
>    > - `;` – separacja poleceń.
>    > - `&` – uruchomienie w tle.
>    > - `()` – grupowanie poleceń.
>    > - `<` / `>` – przekierowania wejścia/wyjścia.
>    > - Cudzysłowy:
>    > - `"` – interpretacja zmiennych.
>    > - `'` – dosłowny tekst.
>    > - `nowa linia` - `\n`
>    > - `tabulator` - `\t`
>    > - `spacja` - `\s`
> 3. Funkcje w skryptach:
>    > ```bash
>    > funkcja() {
>    >    polecenia
>    > }
>    > ```
> 4. Śledzenie wykonywania:
>    > - `set -x` – śledzenie poleceń podczas ich wykonywania.
>    > - `set +x` – wyłączanie śledzenia.
> 5. Polecenie test:
>    > - Sprawdzanie warunków:
>    >   > - pliki: `-e` (istnieje), `-f` (jest plikiem), `-d` (jest katalogiem), ...
>    >   > - liczby: `-eq` (równe), `-ne` (różne), `-lt` (mniejsze), ...
>    >   > - łańcuchy: `=` (równe), `!=` (różne), `-z` (pusty), ...
> 6. Dodatkowe polecenia:
>    > - `tty` – nazwa terminala.
>    > - `id` – informacje o użytkowniku.
>    > - `logname` – login użytkownika.
>    > - `basename`, `dirname` – operacje na ścieżkach plików.
> 7. Programy awk, sed:
>    > - `awk` – przetwarzanie danych.
>    > - `sed` – edycja tekstu w strumieniu.

### 2. Zmienić znaki zachęty na dowolne inne oraz udostępnić je powłokom potomnym.

> ```bash
> PS1="[\u@\h \W]\$ " # [użytkownik@host katalog]$
> export PS1
> ```

### 3. Dokonać następujących zmian w pliku `.bash_profile` (`.profile`):

- (a) zmienić wartości znaków zachęty na dowolny;
- (b) wyświetlić aktualną datę i godzinę;
- (c) wygenerować komunikat powitalny.

> ```bash
> # otwarcie pliku .bash_profile
> nano ~/.bash_profile
>
> # edycja pliku .bash_profile
> PS1="[\u@\h \W]\$ " # [użytkownik@host katalog]$
> export PS1
> echo "Data: `date`"
> echo "Witaj w systemie `uname -s`"
> ```

### 4. Napisać skrypt, który będzie sprawdzał składnie argumentów wywołania, zwracając kod wyjścia równy 0, jeżeli był argument lub kod wyjścia 1, jeśli nie było argumentu.

> [4.sh](./4.sh)

### 5. Napisać skrypt o nazwie `szukaj`, wykorzystujący polecenie `find`, w sposób przyjazny dla użytkownika. Argumentem skryptu ma być nazwa pliku do wyszukania, np.:

```bash
$ szukaj plik
$ /home/user1/bin/plik
```

> [5.sh](./5.sh)

### 6. Zmodyfikować skrypt szukaj utworzony w poprzednim zadaniu tak, aby dał użytkownikom opcję umożliwiającą podanie katalogu od którego rozpocząć poszukiwania, użyj składni: `szukaj plik [katalog]`, np.:

```bash
$ szukaj plik /home/user1
```

Skrypt powinien sprawdzać:

- (a) liczbę argumentów (jeśli nie podano nazwy katalogu, przeszukiwanie ma rozpocząć się od katalogu bieżącego);
- (b) istnienie podanego katalogu (jeżeli nie istnieje, wyświetlić komunikat o błędzie i zakończyć).

> [6.sh](./6.sh)

### 7. Napisać funkcję, która po wywołaniu będzie pytała, czy użytkownik chce zmienić `PS1` lub `PS2` (znaki zachęty). Jeżeli użytkownik nie życzy sobie zmiany znaku zachęty, powinien wcisnąć `<RETURN>`, jeżeli chce go zmienić, powinien wpisać jego nową wartość.

> [7.sh](./7.sh)

### 8. Napisać skrypt `datapl` wyświetlający datę pobieraną z systemu, w języku polskim np.:

```bash
$ datapl
Poniedziałek, 3 styczeń 2005
```

> [8.sh](./8.sh)

### 9. Zmodyfikować skrypt z poprzedniego zadania tak, aby można było wyświetlać datę w różnych formatach, w zależności od podanego przełącznika:

- `-d` — długa data np.: Poniedziałek, 3 styczeń 1999
- `-k` — krótka informacja, np.: 1999-01-03

> [9.sh](./9.sh)

### 10. Napisać program `kopier`, kopiujący zawartość jednego katalogu do innego. Skrypt powinien pytać się o katalog źródłowy, następnie o katalog docelowy i skopiować wszystkie pliki. Następnie należy zmodyfikować skrypt tak, aby kopiował wraz z podkatalogami.

> [10.sh](./10.sh)

### 11. Napisać skrypt listing, wyświetlający zawartość podanego katalogu, dodając informację o typach plików, np.:

```bash
$ listing
tmp -katalog
Mail -katalog
list.txt -plik
.profile -plik
$ _
```

> [11.sh](./11.sh)

### 12. Napisać skrypt size, który wyświetli, dla podanego katalogu, ilość zajmowanego miejsca przez każdy z podkatalogów w kB np.:

```bash
$ size
tmp 400 kB
Mail 38 kB
$ _
```

> [12.sh](./12.sh)

### 13. Napisać skrypt, zmieniający wszystkie nazwy plików zawierające wielkie litery na małe.

> [13.sh](./13.sh)

### 14. Napisać skrypt wyświetlający listę procesów podanego użytkownika.

> [14.sh](./14.sh)

### 15. Napisać skrypt sprawdzający, czy w systemie istnieją użytkownicy nie posiadający hasła. Po znalezieniu takiego użytkownika skrypt powinien wysłać do niego list z informacją.

> [15.sh](./15.sh)

### 16. Zmienić skrypty konfiguracyjne tak, aby w każdy wtorek system przypominał użytkownikowi o zajęciach z systemów operacyjnych. Jeżeli użytkownik zaloguje się w trakcie zajęć komunikat powinien informować, że zajęcia już trwają.

> [16.sh](./16.sh)

### 17. Napisać skrypt wyświetlający wszystkie pliki o rozszerzeniu conf, które zaczynają się od _a_, _b_, _c_ lub _d_ i znajdują się w katalogu `/etc`. Wydruk ma być w postaci:

```bash
Plik1 : /etc/asound.conf
Plik2 : /etc/cas.conf
Plik3 : /etc/cups.conf
```

> [17.sh](./17.sh)

### 18. Napisać skrypt wykonujący kopię zapasową danego katalogu. Kopie powinny znajdować się w katalogu `kopie_projektow`. Każda nowo utworzona kopia ma znajdować się w odrębnym katalogu o nazwie zawierającej datę wykonania kopii. Skrypt ma sprawdzać czy katalog z kopiami istnieje. Jeżeli nie, to skrypt ma utworzyć taki katalog. Katalog do archiwizacji ma być podany jako parametr wejściowy. Skrypt ma również sprawdzać poprawność składni wywołania.

> [18.sh](./18.sh)

### 19. Napisać skrypt dokonujący operacji arytmetycznej na dwóch argumentach. Możliwe do wykonania operacje powinny obejmować: dodawanie, odejmowanie, mnożenie i dzielenie. Argumenty powinny zostać podane do skryptu jako parametry wejściowe. Wywołanie skryptu powinno wyglądać w podany sposób. Skrypt powinien kontrolować liczbę podanych parametrów i wypisywać odpowiednie komunikaty na ich brak:

- (a) w przypadku braku pierwszego parametru: „Brak parametrów wejściowych”,
- (b) w przypadku braku drugiego parametru: „Brak operatora”,
- (c) w przypadku braku trzeciego parametru: „Brak drugiego argumentu”. Skrypt powinien działać tak długo, aż użytkownik nie wyrazi chęci zakończenia obliczeń.

```bash
skrypt 2 + 3
```

> [19.sh](./19.sh)

### 20. Za pomocą polecenia `awk` wyznaczyć średnie ocen punktowych studentów z kolokwiów. Dane zapisane są w pliku tekstowym i mają strukturę:

```
student kolokwium1 kolokwium2 ... kolokwium
1 85 67 ... 76
2 67 56 ... 89
.
.
.
m 45 78 ... 99
```

> [20.sh](./20.sh)

### 21. Napisać skrypt sprawdzający czy system wykorzystuje pamięć wirtualną. Jeżeli tak to podaje informację o rozmiarze pamięci wirtualnej.

> [21.sh](./21.sh)

### 22. Napisać skrypt czyszczący pamięć podręczną. Wskazówka: wykorzystać plik drop caches. Rozszerzyć funkcjonalność skryptu na czyszczenie innych elementów systemu, np. buforów, czy pamięci wirtualnej.

> [22.sh](./22.sh)

### 23. Napisać skrypt sprawdzający temperaturę pracy procesora. Jeśli jest ona większa niż podany przez użytkownika próg to skrypt powinien informować o tym fakcie użytkownika.

Wskazówka: wykorzystać polecenie sensors. Rozszerzyć funkcjonalność skryptu na sprawdzanie temperatury pracy innych urządzeń.

> [23.sh](./23.sh)
