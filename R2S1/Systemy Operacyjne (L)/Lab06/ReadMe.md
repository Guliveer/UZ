# Linux - programowanie systemowe

### 1. Napisać program w języku C lub C++ wyświetlający na ekranie tekst np. Witaj szkoło, a następnie:

- (a) skompilować i uruchomić go (kompilator `gcc` lub `g++`),
- (b) funkcję wypisującą tekst umieścić w osobnym pliku (w pliku `glowny` — program, a w pliku `funkcja` — funkcja wyświetlająca),
- (c) funkcję wypisującą tekst umieścić w bibliotece statycznej.

> [`./zad01/`](./zad01/)
>
> ```bash
> g++ glowny.cpp funkcja.cpp -o glowny
> ./glowny
> ```

### 2. Prześledzić (programem `gdb`) realizację dowolnie wybranej, prostej aplikacji.

> [`./zad02/`](./zad02/)
>
> ```bash
> g++ -g program.cpp -o program
> gdb ./program
> (gdb) break main
> (gdb) run
> (gdb) step
> (gdb) next
> (gdb) print x
> (gdb) print y
> (gdb) continue
> (gdb) quit
> ```

### 3. Dokonać profilowania (programem `gprof`) dowolnie wybranej, prostej aplikacji — przedstawić raport profilera i omówić go.

> [`./zad03/`](./zad03/)
>
> ```bash
> g++ -pg program.cpp -o program
> ./program
> gprof ./program gmon.out > raport.txt
> ```

### 4. Napisać program, który podaje informacje o uzytkowniku na podstawie jego identyfikatora (login, katalog domowy, grupy, powłoka, ...).

> [`./zad04/`](./zad04/)
>
> ```bash
> gcc user_info.c -o user_info
> ./user_info <nazwa_uzytkownika>
> ```

### 5. Napisać program podający informacje dotyczące komputera na którym został uruchomiony (nazwa hosta, system operacyjny, numer wersji, ...).

> [`./zad05/`](./zad05/)
>
> ```bash
> gcc system_info.c -o system_info
> ./system_info
> ```

### 6. Przeanalizować poniższy program.

```
#include <fcntl.h>
int main(void)
{
int dp;
char buf[1024];
dp = creat("smiecie", 0666);
lseek(dp, 2000L, 2); /* przejdź do bajtu 2000 */
write(dp, "czesc", 5);
close(dp);
dp = open("smiecie", O_RDONLY);
read(dp, buf, 1024); /* czytaj zera */
read(dp, buf, 1024); /* złap coś */
read(dp, buf, 1024);
}
```

- (a) Jakie wartości są przekazywane ze wszystkich wywołań `read` i jaka jest zawartość bufora?
  > Pierwsze wywołanie read(dp, buf, 1024):
  >
  > > Zwracana wartość: 1024 (odczytano 1024 bajty zer).  
  > > Bufor: 1024 bajty \0.
  >
  > Drugie wywołanie read(dp, buf, 1024):
  >
  > > Zwracana wartość: 981 (odczytano 976 zer i 5 bajtów "czesc").  
  > > Bufor: 976 bajtów \0 + 5 bajtów "czesc".
  >
  > Trzecie wywołanie read(dp, buf, 1024):
  >
  > > Zwracana wartość: 0 (koniec pliku).  
  > > Bufor: brak zmian (pozostaje w stanie sprzed odczytu).
- (b) Rozważyć ponownie powyższy program, przy założeniu, że przed pierwszym wywołaniem `read znaj duje się lseek(dp, 9000L, 0)`
  > Dodanie lseek(dp, 9000L, 0) przed pierwszym read ustawia wskaźnik pliku na pozycję 9000 bajtów od początku pliku.
  >
  > [Efekty na read]  
  > Pierwsze wywołanie read(dp, buf, 1024):
  >
  > > Odczytuje od pozycji 9000.
  > > Plik kończy się na pozycji 2005, więc brak danych do odczytu.
  > > Zwracana wartość: 0.
  > > Bufor: brak zmian (pozostaje pusty).
  >
  > Drugie i trzecie wywołanie read(dp, buf, 1024):
  >
  > > Odczyty zaczynają się poza końcem pliku.
  > > Zwracana wartość: 0 dla obu odczytów.
  > > Bufor: brak zmian.

### 7. Proces może otworzyć plik w trybie pisania na końcu, co oznacza, że każda operacja pisania zaczyna się od adresu bajtowego wskazującego bieżący koniec pliku. Tak więc dwa procesy mogą otworzysz plik w trybie pisania na końcu i dopisywać do niego nie niszcząc zawartości. Co się stanie, jeżeli proces otworzy plik w trybie pisania na końcu i wykona `lseek` z parametrem wskazującym początek pliku?

> Jeśli proces otworzy plik w trybie pisania na końcu (`O_APPEND`), a następnie wykona operację `lseek` ustawiając wskaźnik pliku na początek (`lseek(fd, 0, SEEK_SET)`), to:
>
> 1. **Tryb `O_APPEND` nadal obowiązuje**:
>
> - `O_APPEND` wymusza, aby każda operacja zapisu była wykonywana na końcu pliku, niezależnie od ustawienia wskaźnika pliku za pomocą `lseek`.
>
> 2. **Działanie `lseek`**:
>
> - `lseek` ustawia wskaźnik pliku na podaną pozycję (w tym przypadku początek pliku).
> - Jednak przy otwarciu w trybie `O_APPEND`, pozycja wskaźnika pliku nie ma wpływu na miejsce, w którym dane zostaną zapisane.
>
> 3. **Skutki operacji zapisu**:
>
> - Zapis danych mimo wszystko zostanie wykonany na końcu pliku, ponieważ tryb `O_APPEND` nadpisuje ustawienie wskaźnika.
> - Wskaźnik pliku pozostanie zmieniony po operacji `lseek`, ale zapis ignoruje tę pozycję.

### 8. Napisać program wyświetlający informacje dotyczące wskazanego pliku:

- typ pliku;
- właściciel/grupa;
- prawa dostępu;
- czasy dostępu (dokładniej: dostępu, modyfikacji i modyfikacji i-węzła);
- rozmiar pliku.

> [`./zad08/`](./zad08/)
>
> ```bash
> gcc file_info.c -o file_info
> ./file_info /ścieżka/do/pliku
> ```

### 9. Napisać program wyświetlający zawartość wskazanego katalogu, a po podaniu opcji `R` program ma wyświetlać zawartość wskazanego katalogu wraz z podkatalogami. Napisać program a) kopiujący, b) usuwający c) przenoszący:

- wybrany plik;
- wybrany katalog;
- po podaniu opcji `R`, kopiujący/usuwający/przenoszący wybrany katalog wraz z podkatalogami.

> [`./zad09/`](./zad09/)
>
> ```bash
> gcc list_dir.c -o list_dir
> gcc copy_items.c -o copy_items
> gcc del_items.c -o del_items
> gcc move_items.c -o move_items
>
> ./list_dir <katalog> [R]
> ./copy_items <źródło> <cel>
> ./del_items <plik/katalog> [R]
> ./move_items <źródło> <cel>
> ```

### 10. Przeanalizować działanie poniższego programu. Jakie jest jego zadanie?

```c
#include <unistd.h>
#include <stdio.h>
int main(){
	int pid;
	printf( "Proces uruchomiony %d\n", getpid() );
	if( pid == -1 ){
		perror( "Błąd utworzenia procesu potomnego!" );
		return 1;
	}
	pid = fork();
	if( pid == 0 ){
		printf( "Zgłasza się proces potomny %d\n", getpid() );
		sleep( 10 );
	} else {
		printf("Zgłasza się proces macierzysty %d. Potomek %d\n",getpid(),pid);
		sleep( 9 );
	}
}
```

> Program tworzy proces potomny za pomocą funkcji `fork()`. Proces macierzysty i potomny wyświetlają swoje identyfikatory PID. Proces macierzysty czeka 9 sekund, a proces potomny 10 sekund. Po zakończeniu procesu potomnego, proces macierzysty kończy swoje działanie.

### 11. Przeanalizować działanie poniższego programu. Jakie jest jego zadanie?

```c
#include <unistd.h>
#include <stdio.h>
int main(){
	if( fork() == 0 ){
		printf( "proces potomny pid: %d ppid: %d\n", getpid(), getppid() );
		sleep( 10 );
		printf( "proces potomny kończy działanie\n" );
		return 0;
	} else {
		printf( "proces macierzysty pid: %d ppid: %d\n", getpid(), getppid() );
		sleep( 5 );
		printf( "proces macierzysty kończy działanie\n" );
		return 0;
	}
}
```

> Program tworzy proces potomny za pomocą funkcji `fork()`. Proces macierzysty i potomny wyświetlają swoje identyfikatory PID i PPID. Proces macierzysty czeka 5 sekund, a proces potomny 10 sekund. Po zakończeniu procesu potomnego, proces macierzysty kończy swoje działanie.

### 12. Wykonać ponizszy program i zinterpretować generowane przez niego wyniki. Powtórzyć eksperyment stosując funcje execl(), execle(), execv(), execvp() i execve().

```c
#include <unistd.h>
#include <stdio.h>
int main(){
	if( fork() == 0 ){
		printf( "proces potomny pid: %d ppid: %d\n",getpid(), getppid() );
		execlp( "ps", "-u student", "--forest", NULL );
		return 0;
	} else {
		printf("proces macierzysty pid: %d ppid: %d\n",getpid(),getppid());
		sleep( 5 );
		return 0;
	}
}
```

> Program tworzy proces potomny za pomocą funkcji `fork()`. Proces macierzysty i potomny wyświetlają swoje identyfikatory PID i PPID. Proces potomny wykonuje program `ps` z argumentami `-u student` i `--forest`. Proces macierzysty czeka 5 sekund, a po zakończeniu procesu potomnego kończy swoje działanie.

### 13. Jakie jest zadanie poniższego programu?

```c
#include <unistd.h>
#include <stdio.h>
int main(){
	int pid = fork();
	if( pid == 0 ){
		printf("proces potomny pid: %d ppid: %d\n",getpid(),getppid());
		sleep( 5 );
		execlp( "ps", "-u student", "-l", "--forest", NULL);
		return 0;
	} else {
		waitpid( pid, NULL, 0 );
		printf( "proces potomny %d zakończony\n", pid );
		return 0;
	}
}
```

> Program tworzy proces potomny za pomocą funkcji `fork()`. Proces macierzysty czeka na zakończenie procesu potomnego za pomocą `waitpid()`. Proces potomny wyświetla swoje identyfikatory PID i PPID, czeka 5 sekund, a następnie wykonuje program `ps` z argumentami `-u student`, `-l` i `--forest`.

### 14. Napisać program, który rozwidlając się, przy użyciu funkcji fork(), tworzy przedstawiony na schemacie poniżej strukturę procesów. Każdy proces po wyświetleniu informacji o swoim numerze PID kończy działanie. Proces, który rozpoczął rozwidlanie (proces 1) zakończy działanie po otrzymaniu informacji, że nie ma już żadnych procesów potomnych. (w Linux)

```
Process 0
 |
 |---- Process 1
        |
        |---- Process 2
               |
               |---- ...
                     |
                     |---- Process 10
```

> [`./zad14/`](./zad14/)
