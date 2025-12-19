package zad03.p3;

/**
 * Adapter dla logowania challenge-response
 * Adaptuje ChallengeResponseLogin do interfejsu LoginService
 */
public class ChallengeResponseLoginAdapter implements LoginService {
    private final ChallengeResponseLogin challengeResponseLogin;
    
    public ChallengeResponseLoginAdapter() {
        this.challengeResponseLogin = new ChallengeResponseLogin();
    }
    
    @Override
    public boolean authenticate() {
        return challengeResponseLogin.interactiveLogin();
    }
    
    @Override
    public String getLoginMethodName() {
        return "Challenge-Response (aplikacja komórkowa)";
    }
}