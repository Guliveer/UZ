package zad02;

/**
 * Interfejs Mediatora dla systemu czatu.
 * Definiuje kontrakt dla pośrednika w komunikacji między użytkownikami.
 * 
 * Wzorzec Mediator - mediator centralizuje komunikację między obiektami,
 * dzięki czemu obiekty nie muszą znać się nawzajem bezpośrednio.
 */
public interface ChatMediator {
    
    /**
     * Rejestruje użytkownika w pokoju czatu.
     * 
     * @param user użytkownik do zarejestrowania
     */
    void registerUser(User user);
    
    /**
     * Wysyła wiadomość do konkretnego użytkownika.
     * 
     * @param message treść wiadomości
     * @param fromUser nadawca wiadomości
     * @param toUser odbiorca wiadomości
     */
    void sendMessage(String message, User fromUser, User toUser);
    
    /**
     * Wysyła wiadomość do wszystkich użytkowników (broadcast).
     * 
     * @param message treść wiadomości
     * @param fromUser nadawca wiadomości
     */
    void sendBroadcastMessage(String message, User fromUser);
    
    /**
     * Zwraca użytkownika o podanej nazwie.
     * 
     * @param name nazwa użytkownika
     * @return użytkownik o podanej nazwie lub null jeśli nie istnieje
     */
    User getUserByName(String name);
}