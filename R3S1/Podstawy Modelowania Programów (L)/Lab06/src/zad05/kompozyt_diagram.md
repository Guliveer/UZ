# Wzorzec Kompozyt - Kalkulator

## Diagram struktury wzorca

```mermaid
classDiagram
    note "Diagram klas wzorca Kompozyt - Kalkulator"

    class ElementMenu {
        <<abstract>>
        #nazwa: String
        #indeks: String
        +ElementMenu(nazwa, indeks)
        +wyswietl(poziom: int) void*
        +wykonaj() void*
        +getNazwa() String
        +getIndeks() String
        #generujWciecie(poziom: int) String
    }

    class OpcjaMenu {
        -akcja: Runnable
        +OpcjaMenu(nazwa, indeks, akcja)
        +wyswietl(poziom: int) void
        +wykonaj() void
    }

    class GrupaMenu {
        -dzieci: List~ElementMenu~
        -scanner: Scanner
        +GrupaMenu(nazwa, indeks, scanner)
        +dodaj(element: ElementMenu) void
        +usun(element: ElementMenu) void
        +pobierzOpcje(indeks: String) ElementMenu
        +wyswietl(poziom: int) void
        +wykonaj() void
        +getDzieci() List~ElementMenu~
    }

    ElementMenu <|-- OpcjaMenu : extends
    ElementMenu <|-- GrupaMenu : extends
    GrupaMenu o-- ElementMenu : zawiera
```

## Diagram klas operacji

```mermaid
classDiagram
    note "Klasy operacji implementujące Runnable"

    class Runnable {
        <<interface>>
        +run() void
    }

    class OperacjaDodawanie {
        -scanner: Scanner
        +OperacjaDodawanie(scanner)
        +run() void
        -formatujLiczbe(liczba: double) String
    }

    class OperacjaOdejmowanie {
        -scanner: Scanner
        +OperacjaOdejmowanie(scanner)
        +run() void
        -formatujLiczbe(liczba: double) String
    }

    class OperacjaMnozenie {
        -scanner: Scanner
        +OperacjaMnozenie(scanner)
        +run() void
        -formatujLiczbe(liczba: double) String
    }

    class OperacjaDzielenie {
        -scanner: Scanner
        +OperacjaDzielenie(scanner)
        +run() void
        -formatujLiczbe(liczba: double) String
    }

    class StalaMatematyczna {
        -nazwa: String
        -wartosc: double
        +StalaMatematyczna(nazwa, wartosc)
        +run() void
        +getNazwa() String
        +getWartosc() double
        +pi()$ StalaMatematyczna
        +e()$ StalaMatematyczna
        +pierwiastekZ2()$ StalaMatematyczna
    }

    Runnable <|.. OperacjaDodawanie : implements
    Runnable <|.. OperacjaOdejmowanie : implements
    Runnable <|.. OperacjaMnozenie : implements
    Runnable <|.. OperacjaDzielenie : implements
    Runnable <|.. StalaMatematyczna : implements
```

## Struktura menu kalkulatora

```
GrupaMenu: "Kalkulator" (główne)
├── GrupaMenu: "Operacje" [1]
│   ├── OpcjaMenu: "Dodawanie" [a] → OperacjaDodawanie
│   ├── OpcjaMenu: "Odejmowanie" [b] → OperacjaOdejmowanie
│   ├── OpcjaMenu: "Mnożenie" [c] → OperacjaMnozenie
│   └── OpcjaMenu: "Dzielenie" [d] → OperacjaDzielenie
│
└── GrupaMenu: "Stałe" [2]
    ├── OpcjaMenu: "Pi" [a] → StalaMatematyczna.pi()
    ├── OpcjaMenu: "e" [b] → StalaMatematyczna.e()
    └── OpcjaMenu: "pierwiastek z 2" [c] → StalaMatematyczna.pierwiastekZ2()
```

## Zalety wzorca Kompozyt w tym rozwiązaniu

1. **Jednolity interfejs** - zarówno pojedyncze opcje jak i grupy są traktowane tak samo
2. **Łatwa rozbudowa** - dodanie nowej operacji wymaga tylko utworzenia nowej klasy i dodania do menu
3. **Rekurencyjna struktura** - menu może mieć dowolną głębokość zagnieżdżenia
4. **Separacja odpowiedzialności** - każda klasa ma jedną odpowiedzialność

## Przykład użycia

```
=== KALKULATOR ===
1. Operacje
2. Stałe
0. Wyjście
> 1

=== OPERACJE ===
a) Dodawanie
b) Odejmowanie
c) Mnożenie
d) Dzielenie
0. Powrót
> a

=== DODAWANIE ===
Podaj pierwszą liczbę: 5
Podaj drugą liczbę: 3
Wynik: 5 + 3 = 8
```

## Uruchomienie programu

```bash
cd src
javac zad05/*.java
java zad05.Main
```
