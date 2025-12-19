package zad02;

import java.util.ArrayList;
import java.util.List;

/**
 * Konkretna implementacja użytkownika czatu.
 * 
 * ChatUser komunikuje się z innymi użytkownikami wyłącznie przez mediatora (ChatRoom).
 * Przechowuje historię otrzymanych wiadomości.
 */
public class ChatUser extends User {
    
    /** Historia otrzymanych wiadomości */
    private final List<String> messageHistory;
    
    /**
     * Konstruktor tworzący użytkownika czatu.
     * 
     * @param mediator mediator zarządzający komunikacją
     * @param name nazwa użytkownika
     */
    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
        this.messageHistory = new ArrayList<>();
    }
    
    /**
     * Wysyła wiadomość do konkretnego użytkownika przez mediatora.
     * 
     * @param message treść wiadomości
     * @param toUserName nazwa odbiorcy
     */
    @Override
    public void sendMessage(String message, String toUserName) {
        System.out.println("[" + name + " -> " + toUserName + "] Wysyłam: \"" + message + "\"");
        
        User toUser = mediator.getUserByName(toUserName);
        if (toUser == null) {
            System.out.println("[BŁĄD] Użytkownik '" + toUserName + "' nie istnieje.");
            return;
        }
        
        mediator.sendMessage(message, this, toUser);
    }
    
    /**
     * Wysyła wiadomość do wszystkich użytkowników (broadcast) przez mediatora.
     * 
     * @param message treść wiadomości
     */
    @Override
    public void sendBroadcastMessage(String message) {
        System.out.println("[" + name + " -> WSZYSCY] Wysyłam broadcast: \"" + message + "\"");
        mediator.sendBroadcastMessage(message, this);
    }
    
    /**
     * Odbiera wiadomość od innego użytkownika.
     * Wiadomość jest zapisywana w historii i wyświetlana na konsoli.
     * 
     * @param message treść wiadomości
     * @param fromUser nadawca wiadomości
     */
    @Override
    public void receiveMessage(String message, User fromUser) {
        String formattedMessage = "[" + fromUser.getName() + " -> " + name + "] " + message;
        messageHistory.add(formattedMessage);
        System.out.println("  >> " + name + " otrzymał(a): \"" + message + "\" od " + fromUser.getName());
    }
    
    /**
     * Wyświetla historię otrzymanych wiadomości.
     */
    public void showMessageHistory() {
        System.out.println("\n=== Historia wiadomości użytkownika " + name + " ===");
        if (messageHistory.isEmpty()) {
            System.out.println("  (brak wiadomości)");
        } else {
            for (int i = 0; i < messageHistory.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + messageHistory.get(i));
            }
        }
        System.out.println("================================================\n");
    }
    
    /**
     * Zwraca liczbę otrzymanych wiadomości.
     * 
     * @return liczba wiadomości w historii
     */
    public int getMessageCount() {
        return messageHistory.size();
    }
    
    /**
     * Czyści historię wiadomości.
     */
    public void clearMessageHistory() {
        messageHistory.clear();
        System.out.println("[INFO] Historia wiadomości użytkownika " + name + " została wyczyszczona.");
    }
    
    @Override
    public String toString() {
        return "ChatUser{name='" + name + "', messages=" + messageHistory.size() + "}";
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ChatUser chatUser = (ChatUser) obj;
        return name.equals(chatUser.name);
    }
    
    @Override
    public int hashCode() {
        return name.hashCode();
    }
}