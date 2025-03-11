package at.ac.tgm.rebay.rebay_backend.config;

import at.ac.tgm.rebay.rebay_backend.models.User;

public class LoginResponse {

    private User user;

    private String token;

    private long expiresIn;

    //------------------------------------------------------------------------------------------------------------------
    //Getter und Setter für die Attribute der Klasse LoginResponse

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gibt das Token zurück.
     * @return das Token
     */
    public String getToken() {
        return token;
    }

    /**
     * Setzt das Token.
     * @param token das Token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gibt die Ablaufzeit in Millisekunden zurück.
     * @return die Ablaufzeit in Millisekunden
     */
    public long getExpiresIn() {
        return expiresIn;
    }

    /**
     * Setzt die Ablaufzeit in Millisekunden.
     * @param expiresIn die Ablaufzeit in Millisekunden
     */
    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }
}