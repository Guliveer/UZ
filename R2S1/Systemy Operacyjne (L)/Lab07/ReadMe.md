# Lab07 - [Systemy plików]

### 1. Uruchomić program [**OS Sim**](https://sourceforge.net/projects/oscsimulator/files/) i wybrać opcję _File system_ lub ikonę z rysunkiem folderów.

### 2. Konfiguracja systemu. Pamięć masowa ma rozmiar `4×1024 jednostki`. Początkowo pamięć jest wolna. Adresy `@0` do `@127` są zarezerwowane i nie można ich użyć.

### 3. Przeanalizować przydział ciągły plików na dysku twardym (system _FAT_). Rozmiar bloku jest równy `1 jednostce`. Do pamięci należy zapisać obiekty plikowe zgodnie z _tabelą 1_.

[*Tabela 1: Struktura katalogowa*]

| Drzewo                                             |             Typ pliku             | Rozmiar |
| :------------------------------------------------- | :-------------------------------: | :-----: |
| `C:`                                               |              katalog              |    1    |
| &nbsp; \|- `folder1`                               |              katalog              |    1    |
| &nbsp; \|&nbsp; &nbsp; \|- `plik1.txt`             |               plik                |   101   |
| &nbsp; \|&nbsp; &nbsp; L `plik2.txt`               |               plik                |   198   |
| &nbsp; L `folder2`                                 |              katalog              |    1    |
| &nbsp; &nbsp; &nbsp; \|- `plik3.txt`               |               plik                |   35    |
| &nbsp; &nbsp; &nbsp; \|- `folder3`                 |              katalog              |    1    |
| &nbsp; &nbsp; &nbsp; \|&nbsp; &nbsp; L `plik4.txt` |               plik                |   117   |
| &nbsp; &nbsp; &nbsp; L `link1`                     | dowiązanie miękkie do `plik1.txt` |    0    |

- (a) Co oznaczają symbole `.` i `..`?
  > `.` - bieżący katalog  
  > `..` - katalog nadrzędny
- (b) Wskaż rozmiar w blokach dla każdego obiektu i określ pierwszy blok alokacji. Wyniki umieść w _tabeli 2_.
- (c) Dla każdego obiektu sprawdź zawartość tablicy alokacji pliku.
- (d) Jaki jest maksymalny rozmiar pliku?
  > `3967` jednostek
- (e) Jak dużo bloków może zawierać badany system plików?
  > Całkowita liczba bloków to 4 × 1024, minus bloki zarezerwowane (128), czyli `3968`.
- (f) Usunąć plik o nazwie `plik1.txt`. Następnie utworzyć w folderze `folder3` plik o nazwie `plik5.dat` o rozmiarze `300 jednostek`. Sprawdzić sposób przechowywania numerów bloków przydzielonych temu plikowi. Sprawdź czy `link1` dalej w systemie funkcjonuje.
  > `plik5.dat` został podzielony na dwie części i zapisany na miejscu pozostałym po `plik1.txt` (130 - 230) oraz na nowych blokach (584 - 782). `link1` istnieje, ale nie spełnia swojej funkcji, ponieważ wskazuje na plik, który nie istnieje.
- (g) Usuń `folder2`. Zaobserwuj co dzieje się z zawartością tego katalogu. Jak wygląda tablica FAT po usunięciu tego katalogu?
  > Po usunięciu `folder2` zawartość katalogu również zostaje usunięta. Z tablicy FAT usunięte elementy zostały 'wycięte', a ich miejsce zostało oznaczone jako wolne, więc zajęte są tylko bloki 128, 129 oraz 231 - 428
- (h) Czy system plików typu FAT umożliwia utworzenie połączenia twardego do pliku?
  > Nie, system plików FAT nie wspiera połączeń twardych.

[*Tabela 2: Tabela wyników*]

|    Typ    | Rozmiar | Blok początkowy |
| :-------: | :-----: | :-------------: |
|    C:     |    1    |       128       |
|  folder1  |    1    |       129       |
| plik1.txt |   101   |       130       |
| plik2.txt |   198   |       231       |
|  folder2  |    1    |       429       |
| plik3.txt |   35    |       430       |
|  folder3  |    1    |       465       |
| plik4.txt |   117   |       466       |
|   link1   |    1    |       583       |

### 4. Powtórzyć alokację z zadania 3 zakładając blok o rozmiarze 4 jednostek. Jak kształtuje się fragmentacja wewnętrzna? Jak kształtowała się fragmentacja w przykładzie 3?

> Fragmentacja wewnętrzna zwiększa się, ponieważ niewykorzystane miejsce w ostatnim bloku każdego pliku jest większe.

### 5. Przeanalizować przydział indeksowy plików na dysku twardym (system _UNIX_). Numery i-węzłów są przydzielane począwszy od bloku 0. Rozmiar bloku jest równy `1 jednostce`. Do pamięci należy zapisać obiekty zgodnie z tabelą 3.

[*Tabela 3: Struktura katalogowa*]

| Drzewo                                             |         Typ pliku          | Rozmiar |
| :------------------------------------------------- | :------------------------: | :-----: |
| `C:`                                               |          katalog           |    1    |
| &nbsp; \|- `folder1`                               |          katalog           |    1    |
| &nbsp; \|&nbsp; &nbsp; \|- `plik1.txt`             |            plik            |   101   |
| &nbsp; \|&nbsp; &nbsp; L `plik2.txt`               |            plik            |   198   |
| &nbsp; \|- `link1`                                 | link twardy do `plik1.txt` |    1    |
| &nbsp; L `folder2`                                 |          katalog           |    1    |
| &nbsp; &nbsp; &nbsp; \|- `plik3.txt`               |            plik            |   463   |
| &nbsp; &nbsp; &nbsp; \|- `folder3`                 |          katalog           |    1    |
| &nbsp; &nbsp; &nbsp; \|&nbsp; &nbsp; L `plik4.txt` |            plik            |   117   |
| &nbsp; &nbsp; &nbsp; L `link2`                     |   link twardy do `link1`   |    0    |

- (a) Określ maksymalny rozmiar pliku w rozważanym systemie plików.
  > 3967 jednostek
- (b) Sprawdź postać listy i-węzłów. Przeanalizuj strukturę opisującą i-węzeł. Co zawiera pierwsza pozycja w tabeli?
  > Pierwsza pozycja tabeli opisuje i-węzeł główny systemu plików (root)
- (c) Podaj całkowity rozmiar obiektów (w blokach) oraz numery ich i-węzłów. Wyniki umieść w _tabeli 4_.
- (d) Dla których plików należało użyć bloków pośrednich. Ile takich bloków zostało użytych?
  > Dla plików przekraczających liczbę bezpośrednich wskaźników w i-węźle (12). W sumie użyto 9 bloków pośrednich.
- (e) Dodaj miękkie dowiązanie o nazwie `link3` do pliku `plik2.txt`. Przeanalizuj jakie wpisy na jego potrzeby dokonywane są w systemie plików. Jaka jest różnica pomiędzy dowiązaniem twardym, a miękkim?
  > Miękkie dowiązanie zapisuje ścieżkę do pliku docelowego, w przeciwieństwie do twardego, które wskazuje na ten sam i-węzeł
- (f) Dodaj miękkie dowiązanie `link4` do dowiązania twardego `link2`. Jaka jest interpretacja takiego działania? Jak zmieni się struktura i-węzłów?
  > Dowiązanie miękkie `link4` wskazuje na dowiązanie twarde `link2`. Zostaje dodany nowy i-węzeł dla `link4`.
- (g) Usuń `plik2.txt` i zapisz na dysku `plik5.txt` o rozmiarze 500 jednostek. Przeanalizuj przydział bloków.
  > `plik5.txt` został podzielony na 15 bloków, z czego 12 to bloki bezpośrednie, a 3 pośrednie. Część pliku została zapisana na miejscu `plik2.txt`, a reszta na nowych blokach.
- (h) Czy można utworzyć dowiązanie do katalogu? Jakiego typu?
  > Tak, twarde oraz miękkie

[*Tabela 4: Tabela wyników*]

|    Typ    | Rozmiar | I-node |
| :-------: | :-----: | :----: |
|    C:     |    1    |   0    |
|  folder1  |    1    |   1    |
| plik1.txt |   107   |   2    |
| plik2.txt |   209   |   3    |
|   link1   |    0    |   2    |
|  folder2  |    1    |   4    |
| plik3.txt |   489   |   5    |
|  folder3  |    1    |   6    |
| plik4.txt |   124   |   7    |
|   link2   |    0    |   2    |
