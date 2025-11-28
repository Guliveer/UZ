package zad02.p2;

/**
 * Klasa odpowiedzialna za przetwarzanie i obsługę poleceń użytkownika
 */
public class KomendyProcessor {
    
    // Dane osób implementujących rozwiązanie
    private static final String[] IMPLEMENTATORZY = {
        "Kowalski", "Nowak", "Wiśniewski"
    };
    
    // Grupa dziekańska
    private static final String GRUPA_DZIEKANSKA = "Grupa 3A";
    
    /**
     * Przetwarza podane polecenie i zwraca odpowiedź
     * @param polecenie polecenie wprowadzone przez użytkownika
     * @return odpowiedź na polecenie lub komunikat o błędzie
     */
    public String przetworzPolecenie(String polecenie) {
        if (polecenie == null || polecenie.trim().isEmpty()) {
            return "Błąd: Puste polecenie. Wpisz 'pomoc' aby zobaczyć dostępne polecenia.";
        }
        
        // Normalizacja polecenia - usunięcie nadmiarowych spacji i konwersja na małe litery
        String normalizedPolecenie = polecenie.trim().toLowerCase();
        
        // Obsługa polecenia "pomoc"
        if (normalizedPolecenie.equals("pomoc")) {
            return obsluzPoleceniePomoc();
        }
        
        // Obsługa polecenia "grupa"
        if (normalizedPolecenie.equals("grupa")) {
            return obsluzPolecenieGrupa();
        }
        
        // Obsługa polecenia "czesc + nazwisko"
        if (normalizedPolecenie.startsWith("czesc")) {
            if (normalizedPolecenie.equals("czesc")) {
                return "Błąd: Brak nazwiska. Użyj formatu: 'czesc Nazwisko'";
            }
            if (normalizedPolecenie.startsWith("czesc ")) {
                return obsluzPolecenieCzesc(polecenie);
            }
        }
        
        // Obsługa poleceń wyjścia
        if (normalizedPolecenie.equals("exit") || normalizedPolecenie.equals("quit") || 
            normalizedPolecenie.equals("wyjscie") || normalizedPolecenie.equals("koniec")) {
            return "EXIT";
        }
        
        // Nieznane polecenie
        return "Błąd: Nieznane polecenie '" + polecenie + "'. Wpisz 'pomoc' aby zobaczyć dostępne polecenia.";
    }
    
    /**
     * Obsługuje polecenie "pomoc" - wypisuje nazwiska implementatorów
     * @return lista nazwisk osób implementujących rozwiązanie
     */
    private String obsluzPoleceniePomoc() {
        StringBuilder wynik = new StringBuilder();
        wynik.append("Rozwiązanie zaimplementowali:\n");
        for (int i = 0; i < IMPLEMENTATORZY.length; i++) {
            wynik.append("- ").append(IMPLEMENTATORZY[i]);
            if (i < IMPLEMENTATORZY.length - 1) {
                wynik.append("\n");
            }
        }
        return wynik.toString();
    }
    
    /**
     * Obsługuje polecenie "grupa" - wypisuje grupę dziekańską
     * @return nazwa grupy dziekańskiej
     */
    private String obsluzPolecenieGrupa() {
        return "Grupa dziekańska: " + GRUPA_DZIEKANSKA;
    }
    
    /**
     * Obsługuje polecenie "czesc + nazwisko" - wypisuje powitanie
     * @param polecenie pełne polecenie wprowadzone przez użytkownika
     * @return powitanie z nazwiskiem lub komunikat o błędzie
     */
    private String obsluzPolecenieCzesc(String polecenie) {
        // Wyciągnięcie nazwiska z polecenia
        String[] czesci = polecenie.trim().split("\\s+");
        
        if (czesci.length < 2) {
            return "Błąd: Brak nazwiska. Użyj formatu: 'czesc Nazwisko'";
        }
        
        if (czesci.length > 2) {
            return "Błąd: Za dużo argumentów. Użyj formatu: 'czesc Nazwisko'";
        }
        
        String nazwisko = czesci[1];
        
        // Walidacja nazwiska - sprawdzenie czy zawiera tylko litery
        if (!nazwisko.matches("[a-zA-ZąćęłńóśźżĄĆĘŁŃÓŚŹŻ]+")) {
            return "Błąd: Nazwisko może zawierać tylko litery.";
        }
        
        // Kapitalizacja pierwszej litery nazwiska
        String skapitalizowaneNazwisko = nazwisko.substring(0, 1).toUpperCase() + 
                                        nazwisko.substring(1).toLowerCase();
        
        return "Witaj " + skapitalizowaneNazwisko;
    }
    
    /**
     * Sprawdza czy polecenie jest poleceniem wyjścia z programu
     * @param polecenie polecenie do sprawdzenia
     * @return true jeśli to polecenie wyjścia, false w przeciwnym razie
     */
    public boolean czyPolecenieWyjscia(String polecenie) {
        if (polecenie == null) return false;
        String normalized = polecenie.trim().toLowerCase();
        return normalized.equals("exit") || normalized.equals("quit") || 
               normalized.equals("wyjscie") || normalized.equals("koniec");
    }
}