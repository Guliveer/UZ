# Lab04 [Linux - praca w konsoli]

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
- (b) zapamietaj wynik wykonania operacji `ls` w pliku `wynik.dat`.

> `ls | more > wynik.dat`

### 1.3. Stworzyć w katalogu katalog miękki link do pliku `plik.txt`. Sprawdzić w jaki sposób link ten jest wyświetlany przez polecenie `ls` oraz dokonać próby modyfikacji tego pliku poprzez stworzony link.

> ```bash
> ln -s plik.txt link_plik.txt
> ls -l
> echo "test" > link_plik.txt
> ```

### 1.4. Korzystając z polecenia `cat` stworzyć dwa pliki `dane1.dat` oraz `dane2.dat` a nastepnie połączyć oba pliki w jeden pod nazwą `dane3.dat`.

> ```bash
> cat > dane1.dat
> cat > dane2.dat
> cat dane1.dat dane2.dat > dane3.dat
> ```

### 1.5. Przy użyciu polecenia `file` sprawdzić jakie informacje zostaną wyświetlone o pliku `plik.txt`, a jakie o linku do tego pliku.

> ```bash
> file plik.txt
> file link_plik.txt
> ```

### 1.6. Za pomocą polecenia `diff` porównać pliki `dane2.dat` oraz `dane3.dat`.

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

### 1.8. Sprawdzić pełną nazwę (bezwzględną ścieżkę dostępu) do `katalogu domowego`.

> `echo $HOME`

### 1.9. Przejść do katalogu domowego, utworzyć za pomocą jednego polecenia następującą gałąź podkatalogów: `zajecia/zadania/lab4`.

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

### 1.13. Za pomocą polecenia `echo "napis" > nazwa_pliku` utworzyć pliki testowe `test1`, `test2` i `test3`, założyć katalog `tmp` i skopiować do niego te pliki.

> ```bash
> cd ~
> echo "napis" > test1
> echo "napis" > test2
> echo "napis" > test3
> mkdir tmp
> cp test1 test2 test3 tmp
> ```

### 1.14. Usunąć pliki testowe (z katalogu domowego) poleceniem `rm` bez opcji.

> `rm ~/test[1-3]`

### 1.15. Usunąć pliki testowe (z katalogu `tmp`) przy pomocy polecenia `rm` z opcjami powodującymi odpytanie użytkownika o zgodę.

> `rm -i ~/tmp/test[1-3]`

### 1.16. Utworzyć link symboliczny o nazwie zu wskazujący na katalog `~/zadania/unix`, sprawdzić wynik polecenia `cd zu`. Uwaga! Najpierw należy utworzyć w/w katalog.

> ```bash
> mkdir -p zadania/unix
> ln -s zadania/unix zu
> cd zu
> ```

### 1.17. Utworzyć katalog `test2`. Przy pomocy polecenia `touch nazwa_pliku nazwa_pliku2 ...` utworzyć w nim pliki `ala.test`, `bela.test`, `cela.test`, `dela.test` i `mela.test`. Utworzyć w katalogu domowym link symboliczny `a_sym` wskazujący na plik `ala.test` i link twardy `a_szt` wskazujący na ten sam plik. Zbadać właściwości linków (np. dopisać coś do treści pliku lub skasować plik wskazywany przez linki).

> ```bash
> mkdir test2
> touch test2/ala.test test2/bela.test test2/cela.test test2/dela.test test2/mela.test
> ln -s test2/ala.test a_sym
> ln test2/ala.test a_szt
> echo "test" > a_sym
> rm a_szt
> ```

### 1.18. Skasować jednym poleceniem pliki: `bela.test`, `cela.test`, ` dela.test`, ale nie `mela.test`.

> `rm test2/[bcd]ela.test`

### 1.19. Zapisać listing pełny katalogu `/bin` do pliku `zad_lab4`.

> `ls -l /bin > zad_lab4`

### 1.20. Wyświetlić 10 pierwszych linii pliku `zad_lab4`.

> `head -n 10 zad_lab4`

### 1.21. Wyświetlić 15 ostatnich linii pliku `zad_lab4`.

> `tail -n 15 zad_lab4`

### 1.22. Wyświetlić 10 ostatnich linii pliku `zad_lab4` w odwrotnej kolejności.

> `tail -n 10 zad_lab4 | tac`

### 1.23. Utworzyć plik tekstowy o nazwie `.profile`. Wpisac w nim linię: `alias rm=’rm -i’`. Wylogować się i zalogować ponownie w celu uruchomienia ustawień zawartych w pliku startowym `.profile`. Odpowiedzieć na pytania:

- (a) do czego sluzy plik `.profile`?
  > Zawiera ustawienia środowiskowe dla powłoki bash.
- (b) czy celowe jest wpisanie do tego pliku linii alias `rm=’rm -i’`?
  > Tak, ponieważ spowoduje to, że polecenie `rm` będzie zawsze pytało o potwierdzenie usunięcia pliku.

### 1.24. Przeglądnąć zawartość plików _.bash_profile_ oraz _.bash_logout_, które odpowiednio wykonują się przy logowaniu oraz przy wyjściu z konta. Zmodyfikować tak te pliki, aby przy logowaniu pojawiał się komunikat powitalny, a przy wyjściu komunikat pożegnalny.

> ```bash
> echo "Witaj w systemie!" >> ~/.bash_profile
> echo "Do zobaczenia!" >> ~/.bash_logout
> ```

### 1.25. Posortować plik `dane3.dat`:

- (a) rosnąco;
  > `sort dane3.dat`
- (b) malejąco;
  > `sort -r dane3.dat`
- (c) rosnąco według trzeciej litery wiersza.
  > `sort -k 3 dane3.dat`

### 1.26. Korzystając z polecenia `grep` wyświetlić z pliku `dane3.dat`:

- (a) wszystkie linie zawierające cyfry;
  > `grep [0-9] dane3.dat`
- (b) wszystkie linie zawierające literę a;
  > `grep a dane3.dat`
- (c) wszystkie linie zawierające litery od a do h;
  > `grep [a-h] dane3.dat`
- (d) wszystkie linie nie zawierające cyfr i zapamiętać je w pliku filtr1.dat.
  > `grep -v [0-9] dane3.dat > filtr1.dat`

### 1.27. Za pomocą polecenia `dd` zamienić wszystkie male litery w pliku `filtr1.dat` na litery duże.

> `dd if=filtr1.dat of=filtr1.dat conv=ucase`

### 1.28. Wyświetlić nazwy wszystkich plików w katalogu domowym użytkownika, nie modyfikowanych od 7 dni.

> `find ~ -type f -mtime +7`

### 1.29. Odszukać wszystke pliki `*.c` położone w katalogu domowym użytkownika, a następnie wykorzystując wyrażenie pierwotne `-exec` wypisać ich listę w długim formacie. Uwaga: jeśli w katalogu nie ma plików z rozszerzeniem `.c`, należy kilka utworzyć.

> ```bash
> touch ~/plik.c
> find ~ -type f -name "*.c*" -exec ls -l {} \;
> ```

### 1.30. Odszukać w katalogu `/tmp` wszystkie pliki `*.txt` mniejsze niż 1000 bajtow i wyświetlić o nich informacje w długim formacie.

> `find /tmp -type f -name "*.txt" -size -1000c -exec ls -l {} \;`

### 1.31. Wyświetlić nazwy i zawartość plików `*.txt` we własnym katalogu domowym. Jeśli nie ma takich plików, należy wcześniej utworzyć dwa pliki tekstowe: `test1.txt` i `test2.txt`.

> ```bash
> echo "test1" > test1.txt
> echo "test2" > test2.txt
> cat *.txt
> ```

## 2. Zarządzanie zadaniami

### 2.1. Sprawdzić jak działają polecenia `ps`, `top` i `kill`.

> ```bash
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

### 2.13. Uruchomić polecenie `sleep 15` w procesie pierwszoplanowym, zawiesić jego działanie sekwencją **Ctrl-Z** i następnie umieścić w tle poleceniem `bg`. Następnie sprawdzić wynik działania poleceń `jobs` i `ps`. Sprowadzić z powrotem polecenie na pierwszy plan z użyciem `fg`.

> ```bash
> sleep 15
> ^Z
> bg
> jobs
> ps
> fg
> ```

### 2.14. Uruchomić `sleep 15` w tle z użyciem `&` i następnie zakończyć działanie procesu poleceniem `kill` z użyciem numeru zadania. Następnie powtórzyć czynności zamykając proces za pomocą jego numeru PID.

> ```bash
> sleep 15 &
> kill %1
> sleep 15 &
> kill $!
> ```

### 2.15. Uruchomić `sleep 15` w tle z użyciem `&` i następnie zawiesić jego działanie za pomocą polecenia `kill`. Użyć polecenia `bg`, aby kontynuować działanie procesu w tle.

> ```bash
> sleep 15 &
> kill %1
> bg
> ```

### 2.16. Napisać skrypt uruchamiający 20 poleceń `sleep n`. Zatrzymać wszystkie uruchomione polecenia za pomocą `pkill`.

> 🔗 [2_16.sh](./2_16.sh)

### 2.17. Za pomocą `ps`, `w` i `top` wskazać, które procesy aktualnie się wykonują.

> ```bash
> ps
> w
> top
> ```

### 2.18. Przy użyciu `ps -aeH` wyświetlić hierarchię procesów. Znaleźć proces _init_ (lub _systemd_). Sprawdzić czy można zidentyfikować ważne demony systemowe. Czy można zidentyfikować powłokę i jej procesy potomne?

> `ps -aeH`

### 2.19. Za pomocą kombinacji poleceń `ps -fae` i `grep` wskazać wszystkie procesy, które się wykonują. Listing nie powinien zawierać poleceń `ps -fae` i `grep`.

> `ps -fae | grep -v "ps -fae" | grep -v "grep"`

### 2.20. Uruchomić process `sleep 300` pracujący w tle. Wylogować się z systemu i zalogować się ponownie. Wylistować listę wszystkich uruchomionych procesów. Co się stało z procesem `sleep 300`? Powtórzyć całą procedurę uruchamiając process `nohup sleep 300`.

> ```bash
> sleep 300 &
> logout
> ps -e
> nohup sleep 300
> logout
> ps -e
> ```

## 3. Użytkownicy, grupy i hasła

### 3.1. Korzystając z polecenia `logname` oraz `pwd` określić nazwę swojego użytkownika oraz umiejscowienie swojego katalogu domowego.

> ```bash
> logname # aktualny użytkownik
> cd ~ ; pwd # katalog domowy
> ```

### 3.2. Wyświetlić i przeanalizować zawartość pliku `/etc/group_`

> `cat /etc/group`

### 3.3. Wyszukać w pliku `/etc/passwd` w konto (wyświetlić tylko jedną linię z tego pliku). Odpowiedzieć na pytanie: jaki jest nr UID oraz GID.

> `grep $USER /etc/passwd`

### 3.4. Wyświetlić listę osób zalogowanych do systemu.

> `who` / `w`

### 3.5. Określić nazwę terminala, na którym pracujemy.

> `tty`

### 3.6. Określić jak w rzeczywistości nazywa się właściciel konta _root_.

> `cat /etc/passwd | grep root`

### 3.7. Zaloguj się jako administrator i utwórz grupy `dba` z numerem id 1001 i `stud` z numerem id 1055.

> ```bash
> sudo su
> groupadd -g 1001 dba
> groupadd -g 1055 stud
> ```

### 3.8. Utwórz następujące konta użytkowników

| Nazwa |  UID |  GID |      Powłoka | Druga grupa | Komentarz           |
| ----- | ---: | ---: | -----------: | ----------: | ------------------- |
| mac1  | 1001 | 1001 | Bourne shell |        1055 | Mac1 user           |
| mac2  | 1002 | 1001 |      C shell |        NULL | Mac2 user           |
| mac3  | 1003 | 1001 |   Bash shell |        1055 | Mac3 user           |
| user1 | 1004 | 1055 | Bourne shell |        NULL | User1 user          |
| user2 | 1005 | 1055 |   Bash shell |        NULL | User2 user          |
| shut  |    - |    - |            - |        NULL | Shutdown the system |

> ```bash
> # mac1
> useradd -u 1001 -g 1001 -s /bin/sh -G 1055 -c "Mac1 user" mac1
>
> # mac2
> useradd -u 1002 -g 1001 -s /bin/csh -c "Mac2 user" mac2
>
> # mac3
> useradd -u 1003 -g 1001 -s /bin/bash -G 1055 -c "Mac3 user" mac3
>
> # user1
> useradd -u 1004 -g 1055 -s /bin/sh -c "User1 user" user1
>
> # user2
> useradd -u 1005 -g 1055 -s /bin/bash -c "User2 user" user2
>
> # shut
> useradd -s /sbin/shutdown -c "Shutdown the system" shut
> ```

### 3.9. Przeanalizuj zawartość pliku `/etc/passwd`.

> `cat /etc/passwd`

### 3.10. Przeanalizuj zawartość pliku `/etc/shadow`. Co oznacza tekst występujący w drugiej kolumnie tego pliku?

> `cat /etc/shadow`

### 3.11. Przeanalizować zawartość pliku `/etc/login.defs`.

> `cat /etc/login.defs`

### 3.12. Ustaw hasło dla użytkowników `mac1`, `mac2`, `mac3` i `shut`.

> ```bash
> passwd mac1
> passwd mac2
> passwd mac3
> passwd shut
> ```

### 3.13. Znajdź użytkownika `user2` na liście użytkowników. Zmień wpisy dla użytkownika `user2` na następujące:

• Max inactive 2 days  
• Expiry 4 days  
Zwiększ datę systemową o 5 dni.

> ```bash
> chage -I 2 -E 4 user2
> date -s "+5 days"
> ```

### 3.14. Wyloguj się z systemu. Spróbuj zalogować się jako `user2`. Co się stanie?

> Użytkownik `user2` nie może zalogować się do systemu, ponieważ jego konto jest nieaktywne.

### 3.15. Zmień powłokę dla użytkownika `user2` na powłokę _Bourna_.

> `chsh -s /bin/sh user2`

### 3.16. Usuń użytkownika `user2` wraz z jego katalogiem domowym.

> `userdel -r user2`

### 3.17. Zablokuj konto `user1` przy użyciu jednego polecenia. Następnie zablokuj konto użytkownikowi `user2` poprzez modyfikację pliku `passwd`.

> ```bash
> passwd -l user1
> usermod -L user2
> ```

### 3.18. Wymuś na użytkownikach zastosowanie hasła długiego na co najmniej 10 znaków.

> `chage -m 10 user1 user2`

### 3.19. Wymuś na użytkowniku `mac2` zmianę hasła co 7 dni. Sprawdź działanie zmieniając datę systemową.

> ```bash
> chage -M 7 mac2
> date -s "+8 days"
> ```

### 3.20. Wpisać polecenie wymuszająca na użytkowniku `mac2` zmianę hasła przy pierwszym logowaniu.

> `chage -d 0 mac2`

### 3.21. Napisać skrypt który utworzy grupę o nazwie studenci, utworzy konta studenckie na podstawie listy zapisanej w postaci pliku tekstowego oraz przydzieli do grupy studenci wszystkie konta studenckie. Skrypt ma sprawdzać czy grupa już istnieje. Skrypt ma również sprawdzać czy konto o podanej nazwie nie zostało już wcześniej utworzone.

> 🔗 [3_21.sh](./3_21.sh)

### 3.22. Usunąć wszystkich utworzonych użytkowników i grupy robocze.

> ```bash
> userdel -r mac1
> userdel -r mac2
> userdel -r mac3
> userdel -r user1
> userdel -r user2
> userdel -r shut
> groupdel dba
> groupdel stud
> groupdel studenci
> ```

## 4. Prawa dostępu

### 4.1. Przeanalizować jakie kombinacje praw _r_, _w_, _x_ są użyteczne, a które nie w kontekście:

- pliku zwykłego,
- katalogu.

> Prawa _rwx_ są użyteczne dla pliku zwykłego, natomiast dla katalogu przydatne są prawa _rwx_ oraz _r-x_.

### 4.2. Wyświetlić nazwy wszystkich plików bądź katalogów, których właścicielem jest użytkownik. Poszukiwania rozpocząć od katalogu `/`.

> `find / -user $USER`

### 4.3. Wyświetlić nazwy wszystkich plików bądź katalogów, których właścicielem jest grupa, do której należy użytkownik. Poszukiwania rozpocząć od katalogu `/`.

> `find / -group $USER`

### 4.4. Wyświetlić nazwy wszystkich plików w systemie, nie mających właściciela i jednocześnie niemających grupy.

> `find / -nouser -nogroup`

### 4.5. Przeszukać swoje konto w poszukiwaniu:

- (a) wszystkich plików, które maja prawa _644_.
- (b) wszystkich katalogów, które można przeglądać

> ```bash
> # (a)
> find ~ -type f -perm 644
> # (b)
> find ~ -type d -perm /111
> ```

### 4.6. Utworzyć plik `wyniki.dat` Zmienić atrybuty pliku `wyniki.dat` tak aby:

- (a) nikt poza właścicielem nie mógł z niego korzystać;
- (b) właściciel miał wszystkie prawa do pliku a pozostali użytkownicy tylko prawo do czytania;
- (c) właściciel oraz jego grupa mieli prawa do czytania i pisania w pliku;
- (d) tak aby nikt nie miał żadnych praw do pliku.

> ```bash
> touch wyniki.dat
> # (a)
> chmod 600 wyniki.dat
> # (b)
> chmod 644 wyniki.dat
> # (c)
> chmod 660 wyniki.dat
> # (d)
> chmod 000 wyniki.dat
> ```

### 4.7. Utworzyć konto _stud1_ z grupą podstawową _studenci_ i konto _pracUZ_ z grupą podstawową _users_.

> ```bash
> useradd -g studenci stud1
> useradd -g users pracUZ
> ```

### 4.8. Utworzyć plik `test.txt`. Zmień właściciela pliku na _stud1_ i grupę pliku na _studenci_.

> ```bash
> touch test.txt
> chown stud1 test.txt
> chgrp studenci test.txt
> ```

### 4.9. Dane są:

- katalogi `/test` i `/test/test1`,
- plik `/test/test1/test2.txt`.

  Z poziomu katalogu głównego (`/`), za pomocą jednego polecenia zmienić właściciela katalogu `/test/test1` i pliku `/test/test1/test2.txt` na _stud1_ i grupę na _studenci_ . Właściciel i grupa katalogu `/test` powinny zostać bez zmian.

> `chown -R stud1:studenci /test/test1 /test/test1/test2.txt`

### 4.10. Dla podanych zestawów uprawnień podać ich odpowiedniki ósemkowe:

- rw-r--r--
  > 644
- r-xr-x---
  > 550
- rwxr--r--
  > 744
- r---w-r--
  > 624

### 4.11. Dla podanych uprawnień podać ich odpowiedniki symboliczne:

- 764
  > `rwxrw-r--`
- 753
  > `rwxr-x-wx`
- 624
  > `rw--w-r--`
- 531
  > `r-x-wx--x`

### 4.12. Dane są:

- katalog `/test`,
- plik `/test/test2.txt`.

  Właścicielem zasobów jest _stud1_ oraz grupa _studenci_. Ustaw prawa dostępu do katalogu i pliku tak, żeby:

- użytkownik _stud1_ mógł czytać, modyfikować i wykonywać plik `/test/test2.txt` oraz czytać i modyfikować zawartość katalogu `/test`,
- użytkownicy grupy _studenci_ mogli czytać i wykonywać plik `/test/test2.txt` oraz mogli czytać i modyfikować zawartość katalogu `/test`,
- _pozostali_ użytkownicy nie mieli żadnych praw do pliku `/test/test2.txt` oraz katalogu `/test`.

  Polecenia należy przedstawić w wersji symbolicznej i ósemkowej.

> ```bash
> # nadanie praw dostępu dla właściciela i grupy
> chown stud1:studenci /test /test/test2.txt
> # symboliczna
> chmod u=rwx,g=rx,o= /test
> chmod u=rwx,g=rx,o= /test/test2.txt
> # ósemkowa
> chmod 750 /test
> chmod 750 /test/test2.txt
> ```

### 4.13. Dane są:

- katalogi `/test` i `/test/test`,
- pliki `/test/test1`, `/test/test2` i `/test/test3`.

  Właścicielem zasobów jest _stud1_ oraz grupa _studenci_. Ustaw prawa dostępu do katalogu `/test` oraz plików tak, żeby:

- do katalogu `/test` wchodzić mogą wszyscy, czytać listę plików może _stud1_ i użytkownicy grupy _studenci_, a modyfikować listę plików może tylko _stud1_,
- pliki `/test/test2` i `/test/test3` może czytać i modyfikować tylko _stud1_ oraz użytkownicy grupy _studenci_. Pozostali użytkownicy nie mają do tych plików żadnych praw,
- prawa dostępu do katalogu `/test/test` oraz pliku `/test/test1` powinny pozostać bez zmian.

  Polecenia należy przedstawić w wersji symbolicznej i ósemkowej.

> ```bash
> # nadanie praw dostępu dla właściciela i grupy
> chown stud1:studenci /test /test/test2 /test/test3
> # symboliczna
> chmod u=rwx,g=rx,o=x /test
> chmod u=rw,g=rw,o= /test/test2 /test/test3
> # ósemkowa
> chmod 751 /test
> chmod 660 /test/test2 /test/test3
> ```

### 4.14. Dany jest katalog `/test` oraz pięć plików `/test/plik[1-5].txt`, których właścicielem jest _root_ i grupą także jest _root_. Grupa i pozostali użytkownicy nie mają do tego katalogu (i jego zawartości) żadnych praw. Korzystając z uprawnień specjalnych, udostępnij ten katalog oraz jego zawartość użytkownikom _stud2_, _stud3_ i _stud4_. Wyświetl bieżące uprawnienia specjalne. Staraj się to zadanie zrobić jak najmniejszą liczbą poleceń.

> ```bash
> chown root:root /test /test/plik[1-5].txt
> chmod 700 /test
> setfacl -m u:stud2:rwx /test
> setfacl -m u:stud3:rwx /test
> setfacl -m u:stud4:rwx /test
> ```

### 4.15. Dany jest katalog `/test` z poprzedniego zadania (wraz z odpowiednio ustawionymi uprawnieniami specjalnymi). Usuń udostępnienie dla użytkownika _stud2_ oraz zmodyfikuj prawa do katalogu tak, żeby każdy nowo utworzony w nim plik i katalog miał uprawnienia podstawowe `rw-------` i żeby był udostępniony dla użytkowników _stud3_ i _stud4_. Wyświetl bieżące uprawnienia specjalne. Staraj się to zadanie zrobić jak najmniejszą liczbą poleceń.

> ```bash
> setfacl -x u:stud2 /test
> setfacl -m d:u:stud3:rwx /test
> setfacl -m d:u:stud4:rwx /test
> ```

### 4.16. Utwórz plik `prog1.c` i skopiuj do niego następującą zawartość:

```c
int main() {
	printf("Test\n");
 	while (1);
}
```

Skompiluj pliku poleceniem `gcc -o prog1 prog1.c`  
Otrzymujemy program o nazwie _prog1_, który po uruchomieniu wypisuje na ekran komunikat 'Test', a następnie się zawiesza. Kończymy jego działanie poprzez wciśnięcie kombinacji klawiszy **Ctrl-C**. Dane są:

- użytkownik _pracUZ_ z grupą podstawową _users_,
- użytkownik _stud1_ z grupą podstawową _studenci_.

Ustaw **właściciela**, **grupę** oraz **prawa dostępu** do programu _prog1_ tak, aby:

- właścicielem programu był _pracUZ_ i program był w grupie _users_,
- czytać i wykonywać plik mógł każdy, a zapisywać do niego mógł tylko właściciel,
- po uruchomieniu programu przez użytkownika _stud1_, właścicielem procesu powinien być _pracUZ_ oraz grupą procesu powinna być grupa _users_.
  Aby wykonać zadanie, zmień na chwilę prawa dostępu tak, aby pozostali użytkownicy nie mieli żadnych praw.

  Czy _stud1_ może w tej sytuacji uruchamiać program? Polecenie do ustawienia praw dostępu należy przedstawić w wersji symbolicznej i ósemkowej.

  Uwaga! Za pomocą polecenia `ps -eo pid,euser,egroup,ruser,rgroup,cmd | grep prog1` można sprawdzić właściciela i grupę procesu.

> ```bash
> # tworzenie pliku
> echo "int main() { printf(\"Test\\n\"); while (1); }" > prog1.c
> # kompilacja
> gcc -o prog1 prog1.c
>
> # nadanie praw dostępu
> chown pracUZ:users prog1
>
> # tymczasowe nadanie praw dostępu
> # symboliczne
> chmod u=rwx,g=,o= prog1
> # ósemkowe
> chmod 700 prog1
>
> # symboliczne
> chmod u=rwx,g=rx,o=rx prog1
> # ósemkowe
> chmod 750 prog1
>
> # ustawienie bitu SUID i SGID
> # (dzięki temu właścicielem procesu będzie pracUZ, a grupą - users)
> chmod u+s,g+s prog1
>
> # sprawdzenie właściciela i grupy procesu
> ps -eo pid,euser,egroup,ruser,rgroup,cmd | grep prog1
> ```
>
> Uruchomienie programu:  
> `./prog1`
>
> Czy _stud1_ może w tej sytuacji uruchamiać program?
>
> > Tak, użytkownik _stud1_ może uruchomić program. Dzięki ustawionym prawom dostępu (`r-x` dla grupy i innych użytkowników) oraz bitowi SUID, proces uruchamiany przez _stud1_ będzie działał z uprawnieniami użytkownika _pracUZ_ oraz w grupie _users_.

### 4.17. Utwórz użytkowników _stud1_–_stud6_ oraz trzy grupy _parlament_, _koloinf_, _koloair_. Grupą podstawową dla użytkowników _stud1_ i _stud2_ jest grupa _parlament_, dla użytkowników _stud3_ i _stud4_ grupa koloinf, a dla użytkowników _stud5_ i _stud6_ grupa _koloair_. Wiadomo także, że użytkownicy _stud1_ oraz _stud5_ należą także do grupy _koloinf_. W katalogu `/test` umieszczono plik o nazwie `projekt`. Do tego pliku pełne prawa posiada powinna posiadać grupa _koloinf_ a pozostałe grupy tylko prawo czytania. Ustawić odpowiednie uprawnienia wg podanego schematu. Sprawdzić jakie uprawnienia efektywne posiadają użytkownicy _stud1_–_stud6_.

> ```bash
> # tworzenie grup
> sudo groupadd parlament
> sudo groupadd koloinf
> sudo groupadd koloair
>
> # tworzenie użytkowników
> sudo useradd -m -G parlament stud1
> sudo useradd -m -G parlament stud2
> sudo useradd -m -G koloinf stud3
> sudo useradd -m -G koloinf stud4
> sudo useradd -m -G koloair stud5
> sudo useradd -m -G koloair stud6
>
> # przypisywanie użytkowników do grup podstawowych
> sudo usermod -g parlament stud1
> sudo usermod -g parlament stud2
> sudo usermod -g koloinf stud3
> sudo usermod -g koloinf stud4
> sudo usermod -g koloair stud5
> sudo usermod -g koloair stud6
>
> # dodanie stud1 i stud5 do grupy koloinf
> sudo usermod -aG koloinf stud1
> sudo usermod -aG koloinf stud5
>
> # tworzenie pliku
> sudo mkdir /test
> sudo touch /test/projekt
>
> # nadanie praw dostępu
> sudo chown :koloinf /test/projekt
> sudo chmod 760 /test/projekt
>
> # sprawdzenie uprawnień
> getfacl /test/projekt
> ```

### 4.18. Dla użytkownika _stud4_ ustawić “sticky bit” do katalogu `/tmp`. Zalogować się do systemu jako _stud4_ i umieścić w katalogu `tmp` plik `dane.txt`. Wylogować się i zalogować na konto innego użytkownika. Usunąć plik `dane.txt`. Czy jest to możliwe?

> ```bash
> # ustawienie sticky bit i sprawdzenie uprawnień
> sudo chmod +t /tmp
> ls -ld /tmp
>
> # zalogowanie jako stud4 i umieszczenie pliku w katalogu tmp
> sudo su stud4
> echo "Dane testowe" > /tmp/dane.txt
> exit
>
> # zalogowanie jako inny użytkownik i usunięcie pliku
> sudo su stud1
> rm /tmp/dane.txt
>
> # wynik:
> # Ponieważ "sticky bit" został ustawiony dla katalogu /tmp, inny użytkownik nie jest w stanie usunąć pliku dane.txt, bo nie jest jego właścicielem
> # W systemie pojawi się komunikat podobny do:
> # rm: cannot remove '/tmp/dane.txt': Operation not permitted
> ```

## 5. Wyrażenie regularne

### 5.1. Napisać wyrażenie regularne do wyszukiwania z tekście frazy `\abc[?]`.

> `\\abc\[\?\]`

### 5.2. Za pomocą polecenia `grep` usunąć z pliku `dane1.dat` linie nie pasujące do wzorca.

> `grep -v -E "\\abc\[\?\]" dane1.dat > dane1_filtered.dat`

### 5.3. Zapoznać się z poleceniem `expr` do wykonywania operacji na łańcuchach znaków.

> `man expr`

### 5.4. Za pomocą polecenia `expr` wyświetlić rozszerzenie pliku, którego nazwa znajduje się w zmiennej plik.

> ```bash
> plik="plik.txt"
> expr "$plik" : '.*\.\(.*\)'
> ```

### 5.5. Zmodyfikować rozwiązanie zadania poprzedniego tak, aby była wyświetlana nazwa bez rozszerzenia.

> ```bash
> plik="plik.txt"
> expr "$plik" : '\(.*\)\..*'
> ```

### 5.6. Napisać wyrażenie regularne do wyszukiwania w tekście adresów witryn www. Adresy mogą zaczynać się od słów http, https lub bez nich.

> `(http(s)?:\/\/)?(www\.)?[a-zA-Z0-9\-]+(\.[a-zA-Z]{2,})(\/[^\s]*)?`

### 5.7. Napisać wyrażenie regularne do wyszukiwaniu w pliku adresów e-mail.

> RegEx: `[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}`
>
> ```bash
> # przykładowe użycie
> plik="dane.txt"
> regex='[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}'
> grep -o -E $regex $plik
> ```
