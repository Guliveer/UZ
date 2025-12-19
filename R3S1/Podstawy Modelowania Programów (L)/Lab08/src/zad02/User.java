package zad02;

/**
 * Abstrakcyjna klasa reprezentująca użytkownika czatu.
 * 
 * Użytkownik komunikuje się z innymi użytkownikami wyłącznie przez mediatora,
 * co jest kluczową cechą wzorca Mediator.
 */
public abstract class User {
    
    /** Mediator zarządzający komunikacją */
    protected ChatMediator mediator;
    
    /** Nazwa użytkownika */
    protected String name;
    
    /**
     * Konstruktor tworzący użytkownika z przypisanym mediatorem.
     * 
     * @param mediator mediator zarządzający komunikacją
     * @param name nazwa użytkownika
     */
    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }
    
    /**
     * Zwraca nazwę użytkownika.
     * 
     * @return nazwa użytkownika
     */
    public String getName() {
        return name;
    }
    
    /**
     * Wysyła wiadomość do konkretnego użytkownika.
     * 
     * @param message treść wiadomości
     * @param toUserName nazwa odbiorcy
     */
    public abstract void sendMessage(String message, String toUserName);
    
    /**
     * Wysyła wiadomość do wszystkich użytkowników (broadcast).
     * 
     * @param message treść wiadomości
     */
    public abstract void sendBroadcastMessage(String message);
    
    /**
     * Odbiera wiadomość od innego użytkownika.
     * 
     * @param message treść wiadomości
     * @param fromUser nadawca wiadomości
     */
    public abstract void receiveMessage(String message, User fromUser);
}