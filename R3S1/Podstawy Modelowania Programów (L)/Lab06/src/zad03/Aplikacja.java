package zad03;

/**
 * Główna klasa aplikacji wykorzystująca wzorzec Dependency Injection.
 * 
 * Ta klasa otrzymuje zależność (TrybPracy) przez konstruktor (Constructor Injection),
 * co pozwala na:
 * - Łatwe testowanie (można wstrzyknąć mock'i)
 * - Luźne powiązanie między komponentami
 * - Łatwe rozszerzanie o nowe tryby pracy (np. REST API)
 * 
 * Klasa nie wie, jaki konkretny tryb pracy będzie używany - zna tylko interfejs.
 * Decyzja o tym, która implementacja zostanie użyta, jest podejmowana
 * w klasie Main (Composition Root).
 */
public class Aplikacja {
    
    // Zależność wstrzyknięta przez konstruktor
    private final TrybPracy trybPracy;
    
    // Procesor poleceń - może być również wstrzyknięty, ale dla uproszczenia
    // tworzymy go wewnętrznie
    private final KomendyProcessor processor;
    
    /**
     * Konstruktor z wstrzykiwaniem zależności (Constructor Injection).
     * 
     * @param trybPracy tryb pracy do użycia (wstrzyknięta zależność)
     */
    public Aplikacja(TrybPracy trybPracy) {
        this(trybPracy, new KomendyProcessor());
    }
    
    /**
     * Konstruktor z pełnym wstrzykiwaniem zależności.
     * Pozwala na wstrzyknięcie zarówno trybu pracy, jak i procesora poleceń.
     * Przydatne do testowania.
     * 
     * @param trybPracy tryb pracy do użycia
     * @param processor procesor poleceń do użycia
     */
    public Aplikacja(TrybPracy trybPracy, KomendyProcessor processor) {
        // Walidacja - nie akceptujemy null'i
        if (trybPracy == null) {
            throw new IllegalArgumentException("TrybPracy nie może być null");
        }
        if (processor == null) {
            throw new IllegalArgumentException("KomendyProcessor nie może być null");
        }
        
        this.trybPracy = trybPracy;
        this.processor = processor;
    }
    
    /**
     * Uruchamia aplikację z wstrzykniętym trybem pracy.
     * 
     * Metoda deleguje wykonanie do wstrzykniętego trybu pracy,
     * przekazując mu procesor poleceń.
     */
    public void uruchom() {
        System.out.println("Uruchamianie aplikacji...");
        System.out.println("Aktywny tryb: " + trybPracy.getNazwaTrybu());
        System.out.println();
        
        // Delegacja do wstrzykniętego trybu pracy
        trybPracy.uruchom(processor);
    }
    
    /**
     * Zwraca aktualnie używany tryb pracy.
     * 
     * @return aktualny tryb pracy
     */
    public TrybPracy getTrybPracy() {
        return trybPracy;
    }
    
    /**
     * Zwraca procesor poleceń.
     * 
     * @return procesor poleceń
     */
    public KomendyProcessor getProcessor() {
        return processor;
    }
}