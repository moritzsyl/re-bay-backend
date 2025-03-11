package at.ac.tgm.rebay.rebay_backend.dtos;

public class LoginUserDto {

    private String loginContactEmail;

    private String password;

    //------------------------------------------------------------------------------------------------------------------
    //Getter und Setter für die Attribute der Klasse LoginUserDto

    /**
     * Gibt die Login- und Kontakt-E-Mail des Benutzers zurück.
     * @return die Login- und Kontakt-E-Mail
     */
    public String getLoginContactEmail() {
        return loginContactEmail;
    }

    /**
     * Setzt die Login- und Kontakt-E-Mail des Benutzers.
     * @param loginContactEmail die Login- und Kontakt-E-Mail
     */
    public void setLoginContactEmail(String loginContactEmail) {
        this.loginContactEmail = loginContactEmail;
    }

    /**
     * Gibt das Login-Passwort des Benutzers zurück.
     * @return das Login-Passwort
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Setzt das Login-Passwort des Benutzers.
     * @param loginPassword das Login-Passwort
     */
    public void setLoginPassword(String loginPassword) {
        this.password = loginPassword;
    }
}
