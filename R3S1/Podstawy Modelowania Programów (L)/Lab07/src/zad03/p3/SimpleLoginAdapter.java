package zad03.p3;

/**
 * Adapter dla prostego logowania login/hasło
 * Adaptuje SimpleLogin do interfejsu LoginService
 */
public class SimpleLoginAdapter implements LoginService {
    private final SimpleLogin simpleLogin;
    
    public SimpleLoginAdapter() {
        this.simpleLogin = new SimpleLogin();
    }
    
    @Override
    public boolean authenticate() {
        System.out.println("(Poprawne dane: login=admin, hasło=haslo123)");
        return simpleLogin.interactiveLogin();
    }
    
    @Override
    public String getLoginMethodName() {
        return "Proste logowanie (login/hasło)";
    }
}