package zad02;

/**
 * Aplikacja konsolowa demonstrująca działanie wzorca Mediator
 * na przykładzie systemu czatu.
 * 
 * Wzorzec Mediator:
 * - Definiuje obiekt (mediator), który enkapsuluje sposób interakcji między obiektami
 * - Obiekty (koledzy) nie komunikują się bezpośrednio, tylko przez mediatora
 * - Zmniejsza sprzężenie między obiektami, ułatwiając ich ponowne użycie
 * 
 * W tym przykładzie:
 * - ChatMediator - interfejs mediatora
 * - ChatRoom - konkretny mediator zarządzający komunikacją
 * - User - abstrakcyjna klasa użytkownika (kolegi)
 * - ChatUser - konkretna implementacja użytkownika
 */
public class Main {
    
    public static void main(String[] args) {
        // Tworzenie mediatora (pokoju czatu)
        ChatRoom chatRoom = new ChatRoom("Pokój programistów");
        
        System.out.println("\n--- REJESTRACJA UŻYTKOWNIKÓW ---\n");
        
        // Tworzenie użytkowników - każdy użytkownik otrzymuje referencję do mediatora
        ChatUser anna = new ChatUser(chatRoom, "Anna");
        ChatUser bartek = new ChatUser(chatRoom, "Bartek");
        ChatUser celina = new ChatUser(chatRoom, "Celina");
        ChatUser dawid = new ChatUser(chatRoom, "Dawid");
        
        // Rejestracja użytkowników w pokoju czatu
        chatRoom.registerUser(anna);
        chatRoom.registerUser(bartek);
        chatRoom.registerUser(celina);
        chatRoom.registerUser(dawid);
        
        // Próba rejestracji użytkownika o istniejącej nazwie
        ChatUser annaKopia = new ChatUser(chatRoom, "Anna");
        chatRoom.registerUser(annaKopia);
        
        // Wyświetlenie listy użytkowników
        chatRoom.showUsers();
        
        System.out.println("--- WYSYŁANIE WIADOMOŚCI PRYWATNYCH ---\n");
        
        // Wysyłanie wiadomości do konkretnego użytkownika
        anna.sendMessage("Cześć Bartek! Jak idzie projekt?", "Bartek");
        System.out.println();
        
        bartek.sendMessage("Hej Anna! Właśnie kończę implementację wzorca Mediator.", "Anna");
        System.out.println();
        
        celina.sendMessage("Dawid, masz chwilę na code review?", "Dawid");
        System.out.println();
        
        dawid.sendMessage("Jasne Celina, za 10 minut będę wolny.", "Celina");
        System.out.println();
        
        // Próba wysłania wiadomości do nieistniejącego użytkownika
        anna.sendMessage("Hej!", "Nieistniejący");
        System.out.println();
        
        System.out.println("--- WYSYŁANIE WIADOMOŚCI BROADCAST ---\n");
        
        // Wysyłanie wiadomości do wszystkich
        anna.sendBroadcastMessage("Uwaga! Za 15 minut spotkanie zespołu.");
        System.out.println();
        
        bartek.sendBroadcastMessage("Nowa wersja aplikacji jest już na serwerze testowym.");
        System.out.println();
        
        System.out.println("--- HISTORIA WIADOMOŚCI ---\n");
        
        // Wyświetlenie historii wiadomości każdego użytkownika
        anna.showMessageHistory();
        bartek.showMessageHistory();
        celina.showMessageHistory();
        dawid.showMessageHistory();
        
        System.out.println("--- DODATKOWE FUNKCJONALNOŚCI ---\n");
        
        // Demonstracja dodatkowych funkcjonalności
        System.out.println("Liczba wiadomości Anny: " + anna.getMessageCount());
        System.out.println("Liczba wiadomości Bartka: " + bartek.getMessageCount());
        System.out.println("Liczba użytkowników w pokoju: " + chatRoom.getUserCount());
        System.out.println();
        
        // Czyszczenie historii
        anna.clearMessageHistory();
        anna.showMessageHistory();
    }
}