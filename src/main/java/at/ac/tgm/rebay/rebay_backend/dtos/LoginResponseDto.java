package at.ac.tgm.rebay.rebay_backend.dtos;

import at.ac.tgm.rebay.rebay_backend.models.User;

public class LoginResponseDto {

    private User user;

    private String token;

    private long expires;

    //------------------------------------------------------------------------------------------------------------------
    //Getter und Setter für die Attribute der Klasse LoginResponseDto

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
    public long getExpires() {
        return expires;
    }

    /**
     * Setzt die Ablaufzeit in Millisekunden.
     * @param expires die Ablaufzeit in Millisekunden
     */
    public void setExpiresIn(long expires) {
        this.expires = expires;
    }
}