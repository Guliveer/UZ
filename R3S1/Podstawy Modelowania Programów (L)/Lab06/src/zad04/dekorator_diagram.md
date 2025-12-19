# Diagram klas wzorca Dekorator

```mermaid
classDiagram
    note "Diagram klas wzorca Dekorator - Sklep komputerowy"

    class Produkt {
        <<interface>>
        +getCena() double
        +getOpis() String
        +getOpisZDodatkami() String
    }

    class PamiecRAM {
        -cena: double = 1000
        -opis: String = "Pamięć RAM 1 MB"
        +getCena() double
        +getOpis() String
        +getOpisZDodatkami() String
    }

    class KartaGraficzna {
        -cena: double = 1500
        -opis: String = "Karta graficzna Nvidia Riva 128"
        +getCena() double
        +getOpis() String
        +getOpisZDodatkami() String
    }

    class PapierPakowy {
        -cena: double = 10
        -opis: String = "Złoty papier do pakowania"
        +getCena() double
        +getOpis() String
        +getOpisZDodatkami() String
    }

    class DekoratorProduktu {
        <<abstract>>
        #produkt: Produkt
        +DekoratorProduktu(Produkt produkt)
        +getCena() double
        +getOpis() String
        +getOpisZDodatkami() String
    }

    class MaskotkaSklep {
        +MaskotkaSklep(Produkt produkt)
        +getCena() double
        +getOpis() String
        +getOpisZDodatkami() String
    }

    class SmyczPendrive {
        +SmyczPendrive(Produkt produkt)
        +getCena() double
        +getOpis() String
        +getOpisZDodatkami() String
    }

    class DodatkowyTransport {
        -kosztTransportu: double = 13
        +DodatkowyTransport(Produkt produkt)
        +getCena() double
        +getOpis() String
        +getOpisZDodatkami() String
    }

    Produkt <|.. PamiecRAM : implements
    Produkt <|.. KartaGraficzna : implements
    Produkt <|.. PapierPakowy : implements
    Produkt <|.. DekoratorProduktu : implements
    DekoratorProduktu <|-- MaskotkaSklep : extends
    DekoratorProduktu <|-- SmyczPendrive : extends
    DekoratorProduktu <|-- DodatkowyTransport : extends
    DekoratorProduktu o-- Produkt : zawiera
```

## Opis wzorca Dekorator

Wzorzec **Dekorator** (ang. Decorator) jest strukturalnym wzorcem projektowym, który pozwala dynamicznie dodawać nowe zachowania do obiektów poprzez umieszczanie ich w specjalnych obiektach opakowujących (dekoratorach).

### Elementy wzorca:

1. **Produkt (Component)** - interfejs definiujący wspólne operacje dla obiektów bazowych i dekoratorów
2. **Konkretne produkty (Concrete Components)** - klasy implementujące interfejs Produkt (PamiecRAM, KartaGraficzna, PapierPakowy)
3. **DekoratorProduktu (Decorator)** - abstrakcyjna klasa bazowa dla wszystkich dekoratorów, przechowuje referencję do dekorowanego obiektu
4. **Konkretne dekoratory (Concrete Decorators)** - klasy dodające nowe funkcjonalności (MaskotkaSklep, SmyczPendrive, DodatkowyTransport)

### Zalety:

- Możliwość dynamicznego dodawania funkcjonalności bez modyfikacji istniejącego kodu
- Zgodność z zasadą otwarte-zamknięte (Open/Closed Principle)
- Możliwość łączenia wielu dekoratorów w dowolnej kolejności
