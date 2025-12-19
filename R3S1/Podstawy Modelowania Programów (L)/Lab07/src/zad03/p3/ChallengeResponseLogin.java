package zad03.p3;

import java.util.Random;
import java.util.Scanner;

/**
 * Klasa implementująca logowanie typu challenge-response (Adaptee)
 * symulujące aplikację komórkową
 */
public class ChallengeResponseLogin {
    private final Random random = new Random();
    
    /**
     * Generuje losową liczbę challenge w zakresie 1-10
     * @return losowa liczba od 1 do 10
     */
    public int generateChallenge() {
        return random.nextInt(10) + 1;
    }
    
    /**
     * Weryfikuje odpowiedź użytkownika
     * @param challenge wygenerowana liczba challenge
     * @param response odpowiedź użytkownika
     * @return true jeśli odpowiedź jest poprawna (challenge + 1)
     */
    public boolean verifyResponse(int challenge, int response) {
        return response == challenge + 1;
    }
    
    /**
     * Interaktywne logowanie challenge-response z konsoli
     * @return true jeśli logowanie udane
     */
    public boolean interactiveLogin() {
        Scanner scanner = new Scanner(System.in);
        
        int challenge = generateChallenge();
        System.out.println("Challenge: " + challenge);
        System.out.print("Podaj odpowiedź (liczba o 1 większa): ");
        
        try {
            int response = Integer.parseInt(scanner.nextLine().trim());
            
            if (verifyResponse(challenge, response)) {
                System.out.println("Witamy w aplikacji");
                return true;
            } else {
                System.out.println("Nieprawidłowa odpowiedź");
                return false;
            }
        } catch (NumberFormatException e) {
            System.out.println("Nieprawidłowy format liczby");
            return false;
        }
    }
}