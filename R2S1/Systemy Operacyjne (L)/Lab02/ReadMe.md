# Lab02

1. **Sprawdź w linii poleceń istniejące konta i grupy użytkowników?**  
   Użyj polecenia z grupy `net`:

   > - `net accounts`
   > - `net localgroup`

2. **Utwórz następujące konta użytkowników przynależące do różnych grup**

   > [02.bat](./02.bat)

3. **Usuń założone grupy i użytkowników z linii poleceń.**  
   Sprawdź, czy operacje usunięcia się powiodły.

   > [03.bat](./03.bat)

4. **Jakim poleceniem z linii komend można otworzyć program _Zarządzanie komputerem_?**

   > `start compmgmt.msc`

5. **Otwórz program Zasady zabezpieczeń lokalnych.**  
   Włącz opcje dotyczące komplikacji hasła, długość min. 8 znaków oraz ważność hasła ustaw na 14 dni.

   > [05.bat](./05.bat)

6. **Włącz opcje blokady konta na jedną minutę po trzech błędnych hasłach**

   > [05.bat](./05.bat) _(ten sam plik co w zadaniu 5)_

7. **Jako administrator utwórz konto o nazwie Jan i przypisz mu folder macierzysty w lokalizacji `C:\Foldery_macierzyste\Jan`**

   > [07.bat](./07.bat)

8. **Przejdź do właściwości konta Jan i przetestuj wszystkie możliwe scenariusze ustalania zasad dotyczących hasła**

   > `compmgmt.msc > Narzędzia systemowe > Użytkownicy i grupy lokalne > Użytkownicy`

9. **Sprawdź, kiedy ostatnio logował się użytkownik Jan na swoje konto**

   > `net user Jan`

10. **Wyświetl wszystkie informacje o użytkowniku podanym jako parametr wejściowy do skryptu**

    > [10.bat](./10.bat)

11. **Dodaj do systemu użytkownika, który może logować się tylko w dni robocze od 8 do 15**

    > [11.bat](./11.bat)

12. **Ustaw minimalną długość hasła na 10 znaków**

    > [12.bat](./12.bat)

13. **Ustaw ważność hasła na 5 dni (wymuszona zmiana po 5 dniach)**

    > [13.bat](./13.bat)

14. **Skrypt, który tworzy konta na podstawie listy użytkowników z pliku tekstowego**

    > [14.ps1](./14.ps1) - `.\14.ps1 "C:\ścieżka\do\pliku.txt"`

15. **Prawa dostępu do zasobów**

    - Utwórz trzech użytkowników o nazwach `Student1`, `Student2`, `Student3`.
    - Utwórz dwie grupy o nazwach `Grupa1` i `Grupa2`.
    - Przypisz do grupy `Grupa1` użytkowników `Student1` i `Student2`.
    - Przypisz do grupy `Grupa2` użytkowników `Student2` i `Student3`.
    - Utwórz na dysku folder o nazwie `C:\dane_grup`.
    - Zapoznaj się z poleceniem `icacls`.
    - Z poziomu linii poleceń odczytaj właściwości folderu `C:\dane_grup`.
    - Przypisz uprawnienia domyślne wraz z prawem zapisu do folderu `Dane_grup` grupie `Grupa1`.
    - Przypisz grupie `Grupa2` prawa wyświetlania zawartości folderu oraz odczytu, a odmów prawa zapisu.
    - Sprawdź czynne uprawnienia użytkowników, szczególnie użytkownika `Student2`.
      > [15.bat](./15.bat)

16. **Skrypt do nadawania określonych praw dostępu do zasobu użytkownikowi**

    > [16.bat](./16.bat)

17. **Utwórz folder `C:\dane_admina` i nadaj użytkownikowi `admin` prawo pełnej kontroli**

    > [17.bat](./17.bat)

18. **Przetestuj dostęp do pliku `plik_admina.txt` dla różnych użytkowników**

    > - **Student1**: Może otworzyć, zmieniać i zapisać plik.
    > - **Student2**: Może otworzyć i zapisać, ale nie usunąć.
    > - **Student3**: Może tylko otworzyć.

19. **Zapoznaj się z poleceniem `takeown`**

    > `takeown /?`

20. **Zapoznaj się z programem `diskpart`**  
    Wyświetl szczegółowe informacje o dyskach, wolumenach i partycjach:

    > - `diskpart`
    > - `DISKPART> list disk`
    > - `DISKPART> list volume`
    > - `DISKPART> select disk 0`
    > - `DISKPART> list partition`
    > - `DISKPART> exit`

21. **Zapoznaj się z poleceniem `fsutil`**

    > `fsutil`

22. **Sprawdź, czy dysk obsługuje przydziały dyskowe**

    > `fsutil fsinfo volumeinfo C:`

23. **Ustaw limity przydziałów dla użytkownika Student1**

    > [23.bat](./23.bat)

24. **Zaloguj się na konto Student1.**  
    Uruchom notatnik i napisz w nim dowolny tekst bez zapisywania go. Notatnik pozostaw uruchomiony.

    - Podaj jaki masz SID
    - Przeloguj się na konto administratora (bez wylogowywania).
      > `whoami /user`

25. **Zapoznaj się z poleceniem `query`**

    - Sprawdź, ilu użytkowników jest zalogowanych w systemie.
    - Sprawdź ID sesji administratora i `Student1`, godzinę logowania, nazwę sesji oraz stan.
    - Porównaj wyniki z Menedżerem zadań i uruchom go.
      > - `query user`
      > - `query session`
      > - `taskmgr / Ctrl + Shift + Esc`

26. **Wyświetl procesy dla administratora i użytkownika Student1**
    - Wyświetl osobno procesy dla administratora i `Student1`.
    - Wyświetl procesy w sesji `console`.
    - Wyświetl procesy skojarzone z notatnikiem, z PID i użytkownikiem.
    - Zadanie domowe: wyjaśnij pojęcie PID.
    - Porównaj wyniki z Menedżerem zadań.
      > - `query process admin`
      > - `query process Student1`
      > - `query process console`
      > - `query process notepad.exe`
