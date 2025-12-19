package zad02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Konkretna implementacja mediatora - pokój czatu.
 * 
 * ChatRoom zarządza listą użytkowników i pośredniczy w komunikacji między nimi.
 * Użytkownicy nie komunikują się bezpośrednio, tylko przez ten mediator.
 */
public class ChatRoom implements ChatMediator {
    
    /** Mapa użytkowników (nazwa -> użytkownik) dla szybkiego wyszukiwania */
    private final Map<String, User> usersByName;
    
    /** Lista wszystkich zarejestrowanych użytkowników */
    private final List<User> users;
    
    /** Nazwa pokoju czatu */
    private final String roomName;
    
    /**
     * Konstruktor tworzący pokój czatu z domyślną nazwą.
     */
    public ChatRoom() {
        this("Główny pokój czatu");
    }
    
    /**
     * Konstruktor tworzący pokój czatu z podaną nazwą.
     * 
     * @param roomName nazwa pokoju czatu
     */
    public ChatRoom(String roomName) {
        this.roomName = roomName;
        this.usersByName = new HashMap<>();
        this.users = new ArrayList<>();
        System.out.println("=== Utworzono pokój czatu: " + roomName + " ===");
    }
    
    /**
     * Rejestruje użytkownika w pokoju czatu.
     * Jeśli użytkownik o takiej nazwie już istnieje, rejestracja jest odrzucana.
     * 
     * @param user użytkownik do zarejestrowania
     */
    @Override
    public void registerUser(User user) {
        if (user == null) {
            System.out.println("[BŁĄD] Nie można zarejestrować pustego użytkownika.");
            return;
        }
        
        String userName = user.getName();
        
        if (usersByName.containsKey(userName)) {
            System.out.println("[BŁĄD] Użytkownik o nazwie '" + userName + "' już istnieje w pokoju.");
            return;
        }
        
        usersByName.put(userName, user);
        users.add(user);
        System.out.println("[INFO] Użytkownik '" + userName + "' dołączył do pokoju '" + roomName + "'.");
        
        // Powiadom innych użytkowników o nowym uczestniku
        notifyUsersAboutNewMember(user);
    }
    
    /**
     * Powiadamia wszystkich użytkowników o nowym członku pokoju.
     * 
     * @param newUser nowy użytkownik
     */
    private void notifyUsersAboutNewMember(User newUser) {
        for (User user : users) {
            if (!user.equals(newUser)) {
                user.receiveMessage("dołączył(a) do pokoju czatu.", newUser);
            }
        }
    }
    
    /**
     * Wysyła wiadomość do konkretnego użytkownika.
     * 
     * @param message treść wiadomości
     * @param fromUser nadawca wiadomości
     * @param toUser odbiorca wiadomości
     */
    @Override
    public void sendMessage(String message, User fromUser, User toUser) {
        if (fromUser == null || toUser == null) {
            System.out.println("[BŁĄD] Nadawca lub odbiorca wiadomości jest pusty.");
            return;
        }
        
        if (!usersByName.containsKey(fromUser.getName())) {
            System.out.println("[BŁĄD] Nadawca '" + fromUser.getName() + "' nie jest zarejestrowany w pokoju.");
            return;
        }
        
        if (!usersByName.containsKey(toUser.getName())) {
            System.out.println("[BŁĄD] Odbiorca '" + toUser.getName() + "' nie jest zarejestrowany w pokoju.");
            return;
        }
        
        // Przekaż wiadomość do odbiorcy
        toUser.receiveMessage(message, fromUser);
    }
    
    /**
     * Wysyła wiadomość do wszystkich użytkowników (broadcast).
     * Wiadomość nie jest wysyłana do nadawcy.
     * 
     * @param message treść wiadomości
     * @param fromUser nadawca wiadomości
     */
    @Override
    public void sendBroadcastMessage(String message, User fromUser) {
        if (fromUser == null) {
            System.out.println("[BŁĄD] Nadawca wiadomości jest pusty.");
            return;
        }
        
        if (!usersByName.containsKey(fromUser.getName())) {
            System.out.println("[BŁĄD] Nadawca '" + fromUser.getName() + "' nie jest zarejestrowany w pokoju.");
            return;
        }
        
        System.out.println("[BROADCAST] " + fromUser.getName() + " wysyła wiadomość do wszystkich.");
        
        // Wyślij wiadomość do wszystkich użytkowników oprócz nadawcy
        for (User user : users) {
            if (!user.equals(fromUser)) {
                user.receiveMessage(message, fromUser);
            }
        }
    }
    
    /**
     * Zwraca użytkownika o podanej nazwie.
     * 
     * @param name nazwa użytkownika
     * @return użytkownik o podanej nazwie lub null jeśli nie istnieje
     */
    @Override
    public User getUserByName(String name) {
        return usersByName.get(name);
    }
    
    /**
     * Zwraca liczbę zarejestrowanych użytkowników.
     * 
     * @return liczba użytkowników
     */
    public int getUserCount() {
        return users.size();
    }
    
    /**
     * Zwraca nazwę pokoju czatu.
     * 
     * @return nazwa pokoju
     */
    public String getRoomName() {
        return roomName;
    }
    
    /**
     * Wyświetla listę wszystkich użytkowników w pokoju.
     */
    public void showUsers() {
        System.out.println("\n=== Użytkownicy w pokoju '" + roomName + "' ===");
        if (users.isEmpty()) {
            System.out.println("  (brak użytkowników)");
        } else {
            for (int i = 0; i < users.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + users.get(i).getName());
            }
        }
        System.out.println("==========================================\n");
    }
}