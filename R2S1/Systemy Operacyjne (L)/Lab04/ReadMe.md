# Lab04

## 1. Manipulowanie plikami i katalogami

### 1.1. Wyświetlić zawartość swojego katalogu domowego tak, aby:

- (a) pojawiły się wszystkie informacje na temat plików i katalogów;
  > `ls -l ~`
- (b) wyświetlone zostały pliki i katalogi ukryte;
  > `ls -la ~`
- (c) wyświetlane elementy były posortowane wg rozmiaru;
  > `ls -lS ~`
- (d) wyświetlane elementy były posortowane wg daty;
  > `ls -lt ~`
- (e) wyświetlone zostały tylko pliki o rozszerzeniu txt;
  > `find ~ -type f -name "*.txt`
- (f) wyświetlone zostały tylko pliki, które w swojej nazwie jako trzecią literę zawierają i.
  > `find ~ -type f -name "??i*`

### 1.2. Wyświetlić zawartość bieżącego katalogu:

- (a) wykorzystując do tego celu polecenie `more`, które pobierze dane do wyświetlenia z polecenia `ls` poprzez łącze systemowe;
- (b) zapamietaj wynik wykonania operacji `ls` w pliku _wynik.dat_.
  > `ls | more > wynik.dat`

### 1.3. Stworzyć w katalogu katalog miękki link do pliku _plik.txt_. Sprawdzić w jaki sposób link ten jest wyświetlany przez polecenie `ls` oraz dokonać próby modyfikacji tego pliku poprzez stworzony link.

> ```
> ln -s plik.txt link_plik.txt
> ls -l
> echo "test" > link_plik.txt
> ```

### 1.4. Korzystając z polecenia `cat` stworzyć dwa pliki _dane1.dat_ oraz _dane2.dat_ a nastepnie połączyć oba pliki w jeden pod nazwą _dane3.dat_.

> ```
> touch dane1.dat
> touch dane2.dat
> cat dane1.dat dane2.dat > dane3.dat
> ```

### 1.5. Przy użyciu polecenia `file` sprawdzić jakie informacje zostaną wyświetlone o pliku _plik.txt_, a jakie o linku do tego pliku.

> ```
> file plik.txt
> file link_plik.txt
> ```

### 1.6. Za pomocą polecenia `diff` porównać pliki _dane2.dat_ oraz _dane3.dat_.

> `diff dane2.dat dane3.dat`

### 1.7. Przeszukaj (polecenie `find`) swoje konto w poszukiwaniu:

- (a) wszystkich plików z rozszerzeniem dat;
  > `find ~ -type f -name "*.dat"`
- (b) wszystkich plików, które w swojej nazwie mają jako drugą literę a lub i;
  > `find ~ -type f -name "?[ai]*"`
- (c) wszystkich plików, których zawartość zmieniła się w ciągu ostatnich 5 minut;
  > `find ~ -type f -mmin -5`
- (d) wszystkich plików, których zawartość zmieniła się 10 lub więcej minut temu a dodatkowo ich nazwy zaczynają się na literę p;
  > `find ~ -type f -mmin +10 -name "p*"`
- (e) wszystkich plików, które maja prawa 644.
  > `find ~ -type f -perm 644`

### 1.8. Sprawdzić pełną nazwę (bezwzględną ścieżkę dostępu) do _katalogu domowego_.

> `echo $HOME`

### 1.9. Przejść do katalogu domowego, utworzyć za pomocą jednego polecenia następującą gałąź podkatalogów: _zajecia/zadania/lab4_.

> `mkdir -p zajecia/zadania/lab4`

### 1.10. Sprawdzić działanie poleceń `basename` i `dirname` w następujący sposob:

- (a) napisać polecenie: `echo $HISTFILE`. Co jest jego wynikiem?
  > `echo $HISTFILE`; Historia poleceń zapisywana jest w pliku ~/.bash_history.
- (b) napisać polecenia: `basename $HISTFILE` i `dirname $HISTFILE`.
  > `basename $HISTFILE`; .bash_history  
  > `dirname $HISTFILE`; /home/$USER

### 1.11. Wyświetlić listę plików w katalogu domowym, uporządkowaną według czasu tworzenia/modyfikacji plików, ale w kolejnosci odwrotnej.

> `ls -lt -r ~`

### 1.12. Wyświetlić listę plików w katalogu domowym w formacie wielokolumnowym opatrzonym specjalnymi znakami, tj. `/` dla katalogow, `*` dla plików wykonywalnych, `@` dla linków, itd. (wykorzystać jedną z opcji polecenia `ls`).

> `ls -F ~`

### 1.13. Za pomocą polecenia `echo "napis" > nazwa_pliku` utworzyć pliki testowe _test1_, _test2_ i _test3_, założyć katalog _tmp_ i skopiować do niego te pliki.

> ```
> echo "napis" > test1
> echo "napis" > test2
> echo "napis" > test3
> mkdir tmp
> cp test1 test2 test3 tmp
> ```

### 1.14. Usunąć pliki testowe (z katalogu domowego) poleceniem `rm` bez opcji.

> `rm test1 test2 test3`

### 1.15. Usunąć pliki testowe (z katalogu _tmp_) przy pomocy polecenia `rm` z opcjami powodującymi odpytanie użytkownika o zgodę.

> `rm -i tmp/test1 tmp/test2 tmp/test3`

### 1.16. Utworzyć link symboliczny o nazwie zu wskazujący na katalog _~/zadania/unix_, sprawdzić wynik polecenia `cd zu`. Uwaga! Najpierw należy utworzyć w/w katalog.

> ```
> mkdir -p zadania/unix
> ln -s zadania/unix zu
> cd zu
> ```

### 1.17. Utworzyć katalog _test2_. Przy pomocy polecenia `touch nazwa_pliku nazwa_pliku2 ...` utworzyc w nim pliki _ala.test_, _bela.test_, _cela.test_, _dela.test_ i _mela.test_. Utworzyć w katalogu domowym link symboliczny _a_sym_ wskazujący na plik _ala.test_ i link twardy _a_szt_ wskazujący na ten sam plik. Zbadać właściwości linków (np. dopisać coś do treści pliku lub skasować plik wskazywany przez linki).

> ```
> mkdir test2
> touch test2/ala.test test2/bela.test test2/cela.test test2/dela.test test2/mela.test
> ln -s test2/ala.test a_sym
> ln test2/ala.test a_szt
> echo "test" > a_sym
> rm a_szt
> ```

### 1.18. Skasować jednym poleceniem pliki: _bela.test_, _cela.test_, _dela.test_, ale nie _mela.test_.

> `rm test2/bela.test test2/cela.test test2/dela.test`

### 1.19. Zapisać listing pełny katalogu _/bin_ do pliku _zad_lab4_.

> `ls -l /bin > zad_lab4`

### 1.20. Wyświetlić 10 pierwszych linii pliku _zad_lab4_.

> `head -n 10 zad_lab4`

### 1.21. Wyświetlić 15 ostatnich linii pliku _zad_lab4_.

> `tail -n 15 zad_lab4`

### 1.22. Wyświetlić 10 ostatnich linii pliku _zad_lab4_ w odwrotnej kolejności.

> `tail -n 10 zad_lab4 | tac`

### 1.23. Utworzyć plik tekstowy o nazwie _.profile_. Wpisac w nim linię: `alias rm=’rm -i’`. Wylogować się i zalogować ponownie w celu uruchomienia ustawień zawartych w pliku startowym _.profile_. Odpowiedzieć na pytania:

- (a) do czego sluzy plik .profile?
  > Plik .profile zawiera ustawienia środowiskowe dla powłoki bash.
- (b) czy celowe jest wpisanie do tego pliku linii alias rm=’rm -i’?
  > Tak, ponieważ alias rm=’rm -i’ powoduje, że polecenie rm będzie zawsze pytane o potwierdzenie usunięcia pliku.

### 1.24. Przeglądnąć zawartość plików _.bash_profile_ oraz _.bash_logout_, które odpowiednio wykonują się przy logowaniu oraz przy wyjściu z konta. Zmodyfikować tak te pliki, aby przy logowaniu pojawiał się komunikat powitalny, a przy wyjściu komunikat pożegnalny.

> ```
> echo "Witaj w systemie!" >> ~/.bash_profile
> echo "Do zobaczenia!" >> ~/.bash_logout
> ```

### 1.25. Posortować plik _dane3.dat_:

- (a) rosnąco;
  > `sort dane3.dat`
- (b) malejąco;
  > `sort -r dane3.dat`
- (c) rosnąco według trzeciej litery wiersza.
  > `sort -k 3 dane3.dat`

### 1.26. Korzystając z polecenia `grep` wyświetlić z pliku _dane3.dat_:

- (a) wszystkie linie zawierające cyfry;
  > `grep [0-9] dane3.dat`
- (b) wszystkie linie zawierające literę a;
  > `grep a dane3.dat`
- (c) wszystkie linie zawierające litery od a do h;
  > `grep [a-h] dane3.dat`
- (d) wszystkie linie nie zawierające cyfr i zapamiętać je w pliku filtr1.dat.
  > `grep -v [0-9] dane3.dat > filtr1.dat`

### 1.27. Za pomocą polecenia `dd` zamienić wszystkie male litery w pliku _filtr1.dat_ na litery duże.

> `dd if=filtr1.dat of=filtr1.dat conv=ucase`

### 1.28. Wyświetlić nazwy wszystkich plików w katalogu domowym użytkownika, nie modyfikowanych od 7 dni.

> `find ~ -type f -mtime +7`

### 1.29. Odszukać wszystke pliki _\*.c_ położone w katalogu domowym użytkownika, a następnie wykorzystując wyrażenie pierwotne `-exec` wypisać ich listę w długim formacie. Uwaga: jeśli w katalogu nie ma plików z rozszerzeniem _.c_, należy kilka utworzyć.

> ```
> touch ~/plik.c
> find ~ -type f -name "*.c*" -exec ls -l {} \;
> ```

### 1.30. Odszukać w katalogu _/tmp_ wszystkie pliki _\*.txt_ mniejsze niż 1000 bajtow i wyświetlić o nich informacje w długim formacie.

> `find /tmp -type f -name "*.txt" -size -1000c -exec ls -l {} \;`

### 1.31. Wyświetlić nazwy i zawartość plików _\*.txt_ we własnym katalogu domowym. Jeśli nie ma takich plików, należy wcześniej utworzyć dwa pliki tekstowe: _test1.txt_ i _test2.txt_.

> ```
> echo "test1" > test1.txt
> echo "test2" > test2.txt
> cat *.txt
> ```

## 2. Zarządzanie zadaniami

### 2.1. Sprawdzić jak działają polecenia `ps`, `top` i `kill`.

> ```
> ps
> top
> kill
> ```

### 2.2. Jaka aplikacja wykorzystuje w danej chwili najwięcej pamięci w systemie?

> `top`

### 2.3. Wyświetlić listę wszystkich uruchomionych procesów.

> `ps -e`

### 2.4. Wyświetlić listę wszystkich programów uruchomionych przez danego użytkownika.

> `ps -u $USER`

### 2.5. Przeanalizować działanie polecenia `pstree`.

> `pstree`

### 2.6. Wyświetlić hierarchię procesów za pomocą polecenia `ps`. Porównać z wynikami uzyskanymi w poprzednim zadaniu.

> `ps -e --forest`

### 2.7. Wyświetlić listę wątków działających w ramach danego procesu.

> `ps -eLf`

### 2.8. Wyświetlić listę 10 zadań z największym wykorzystaniem procesora.

> `ps -e --sort=-pcpu | head -n 11`

### 2.9. Wyświetlić listę 10 zadań z największym wykorzystaniem pamięci operacyjnej.

> `ps -e --sort=-rss | head -n 11`

### 2.10. Za pomocą polecenia `watch` dokonać monitorowania wykorzystania pamięci operacyjnej z interwałem 1 sekundy.

> `watch -n 1 free -m`

### 2.11. Sprawdzić działanie polecenia `sleep 5`.

> `sleep 5`

### 2.12. Uruchomić to polecenia w tle używając znaku `&`.

> `sleep 5 &`

### 2.13. Uruchomić polecenie `sleep 15` w procesie pierwszoplanowym, zawiesić jego działanie sekwencją _Ctrl-Z_ i następnie umieścić w tle poleceniem `bg`. Następnie sprawdzić wynik działania poleceń `jobs` i `ps`. Sprowadzić z powrotem polecenie na pierwszy plan z użyciem `fg`.

> ```
> sleep 15
> ^Z
> bg
> jobs
> ps
> fg
> ```

### 2.14. Uruchomić `sleep 15` w tle z użyciem `&` i następnie zakończyć działanie procesu poleceniem `kill` z użyciem numeru zadania. Następnie powtórzyć czynności zamykając proces za pomocą jego numeru PID.

> ```
> sleep 15 &
> kill %1
> sleep 15 &
> kill $!
> ```

### 2.15. Uruchomić `sleep 15` w tle z użyciem `&` i następnie zawiesić jego działanie za pomocą polecenia `kill`. Użyć polecenia `bg`, aby kontynuować działanie procesu w tle.

> ```
> sleep 15 &
> kill %1
> bg
> ```

### 2.16. Napisać skrypt uruchamiający 20 poleceń `sleep n`. Zatrzymać wszystkie uruchomione polecenia za pomocą `pkill`.

> 🔗 [2_16.sh](./2_16.sh)

### 2.17. Za pomocą `ps`, `w` i `top` wskazać, które procesy aktualnie się wykonują.

> ```
> ps
> w
> top
> ```

### 2.18. Przy użyciu `ps -aeH` wyświetlić hierarchię procesów. Znaleźć proces _init_ (lub _systemd_). Sprawdzić czy można zidentyfikować ważne demony systemowe. Czy można zidentyfikować powłokę i jej procesy potomne?

> `ps -aeH`

### 2.19. Za pomocą kombinacji poleceń `ps -fae` i `grep` wskazać wszystkie procesy, które się wykonują. Listing nie powinien zawierać poleceń `ps -fae` i `grep`.

> `ps -fae | grep -v "ps -fae" | grep -v "grep"`

### 2.20. Uruchomić process `sleep 300` pracujący w tle. Wylogować się z systemu i zalogować się ponownie. Wylistować listę wszystkich uruchomionych procesów. Co się stało z procesem `sleep 300`? Powtórzyć całą procedurę uruchamiając process `nohup sleep 300`.

> ```
> sleep 300 &
> logout
> ps -e
> nohup sleep 300
> logout
> ps -e
> ```
