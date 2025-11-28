package zad01.p2;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Program główny demonstrujący zapisywanie danych do pliku tekstowego.
 * Oferuje dwie opcje:
 * 1. Zapisywanie tekstu wprowadzonego przez użytkownika
 * 2. Zapisywanie losowo wygenerowanych liczb
 * 
 * Każda opcja jest zaimplementowana w oddzielnej klasie zgodnie z zasadami OOP.
 */
public class Main {
    private static final String NAZWA_PLIKU = "dane_wyjsciowe.txt";
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=================================================");
        System.out.println("    PROGRAM ZAPISYWANIA DANYCH DO PLIKU");
        System.out.println("=================================================");
        System.out.println("Plik wyjściowy: " + NAZWA_PLIKU);
        System.out.println();
        
        // Utworzenie obiektów dla obu strategii zapisywania
        ZapisywaczTekstu zapisywaczTekstu = new ZapisywaczTekstu(NAZWA_PLIKU);
        ZapisywaczLosowychLiczb zapisywaczLiczb = new ZapisywaczLosowychLiczb(NAZWA_PLIKU);
        
        boolean kontynuuj = true;
        
        while (kontynuuj) {
            try {
                wyswietlMenu();
                int wybor = pobierzWybor();
                
                switch (wybor) {
                    case 1:
                        // Opcja 1: Zapisywanie tekstu z klawiatury
                        zapisywaczTekstu.zapiszTekstZKlawiatury();
                        break;
                        
                    case 2:
                        // Opcja 2: Zapisywanie losowych liczb (domyślne parametry)
                        zapisywaczLiczb.zapiszLosoweLiczby();
                        break;
                        
                    case 3:
                        // Opcja 3: Zapisywanie losowych liczb (parametry niestandardowe)
                        zapiszLosoweLiczbyZParametrami(zapisywaczLiczb);
                        break;
                        
                    case 4:
                        // Opcja 4: Wyświetlenie zawartości pliku
                        wyswietlZawartoscPliku();
                        break;
                        
                    case 0:
                        // Opcja 0: Zakończenie programu
                        kontynuuj = false;
                        System.out.println("Dziękujemy za skorzystanie z programu!");
                        break;
                        
                    default:
                        System.out.println("Nieprawidłowy wybór! Spróbuj ponownie.");
                }
                
                if (kontynuuj) {
                    System.out.println("\nNaciśnij Enter, aby kontynuować...");
                    scanner.nextLine();
                    System.out.println();
                }
                
            } catch (IOException e) {
                System.err.println("Błąd podczas operacji na pliku: " + e.getMessage());
            } catch (InputMismatchException e) {
                System.err.println("Błąd: Wprowadzono nieprawidłową wartość!");
                scanner.nextLine(); // Czyszczenie bufora
            } catch (Exception e) {
                System.err.println("Wystąpił nieoczekiwany błąd: " + e.getMessage());
            }
        }
        
        scanner.close();
    }
    
    /**
     * Wyświetla menu opcji programu
     */
    private static void wyswietlMenu() {
        System.out.println("=== MENU GŁÓWNE ===");
        System.out.println("1. Zapisz tekst z klawiatury do pliku");
        System.out.println("2. Zapisz losowe liczby do pliku (10 liczb, zakres 1-100)");
        System.out.println("3. Zapisz losowe liczby do pliku (parametry niestandardowe)");
        System.out.println("4. Wyświetl zawartość pliku");
        System.out.println("0. Zakończ program");
        System.out.print("Wybierz opcję (0-4): ");
    }
    
    /**
     * Pobiera wybór użytkownika z walidacją
     * @return wybór użytkownika jako liczba całkowita
     */
    private static int pobierzWybor() {
        int wybor = scanner.nextInt();
        scanner.nextLine(); // Konsumowanie znaku nowej linii
        return wybor;
    }
    
    /**
     * Obsługuje zapisywanie losowych liczb z parametrami podanymi przez użytkownika
     * @param zapisywacz obiekt klasy ZapisywaczLosowychLiczb
     * @throws IOException w przypadku problemów z zapisem do pliku
     */
    private static void zapiszLosoweLiczbyZParametrami(ZapisywaczLosowychLiczb zapisywacz) throws IOException {
        System.out.println("=== PARAMETRY NIESTANDARDOWE ===");
        
        System.out.print("Podaj ilość liczb do wygenerowania: ");
        int ilosc = scanner.nextInt();
        
        System.out.print("Podaj minimalną wartość: ");
        int min = scanner.nextInt();
        
        System.out.print("Podaj maksymalną wartość: ");
        int max = scanner.nextInt();
        
        scanner.nextLine(); // Konsumowanie znaku nowej linii
        
        // Walidacja parametrów
        if (ilosc <= 0) {
            throw new IllegalArgumentException("Ilość liczb musi być większa od 0!");
        }
        
        if (min >= max) {
            throw new IllegalArgumentException("Minimalna wartość musi być mniejsza od maksymalnej!");
        }
        
        zapisywacz.zapiszLosoweLiczby(ilosc, min, max);
    }
    
    /**
     * Wyświetla zawartość pliku wyjściowego
     */
    private static void wyswietlZawartoscPliku() {
        try {
            java.nio.file.Path sciezka = java.nio.file.Paths.get(NAZWA_PLIKU);
            
            if (!java.nio.file.Files.exists(sciezka)) {
                System.out.println("Plik " + NAZWA_PLIKU + " nie istnieje jeszcze.");
                System.out.println("Użyj opcji 1 lub 2, aby utworzyć plik z danymi.");
                return;
            }
            
            System.out.println("=== ZAWARTOŚĆ PLIKU: " + NAZWA_PLIKU + " ===");
            java.util.List<String> linie = java.nio.file.Files.readAllLines(sciezka);
            
            if (linie.isEmpty()) {
                System.out.println("Plik jest pusty.");
            } else {
                for (int i = 0; i < linie.size(); i++) {
                    System.out.printf("%3d: %s%n", i + 1, linie.get(i));
                }
            }
            
        } catch (IOException e) {
            System.err.println("Błąd podczas odczytu pliku: " + e.getMessage());
        }
    }
}
