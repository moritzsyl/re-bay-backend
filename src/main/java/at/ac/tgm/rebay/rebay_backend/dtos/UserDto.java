package at.ac.tgm.rebay.rebay_backend.dtos;

public class UserDto {

    private String loginContactEmail;

    private String password;

    private String name;

    private String address;

    private String city;

    private String postalCode;

    private String contactPhonenumber;

    private boolean role;

    //------------------------------------------------------------------------------------------------------------------
    //Getter und Setter für die Attribute der Klasse UserDto

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

    /**
     * Gibt den Namen des Benutzers zurück.
     * @return der Name
     */
    public String getName() {
        return name;
    }

    /**
     * Setzt den Namen des Benutzers.
     * @param name der Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gibt die Adresse des Benutzers zurück.
     * @return die Adresse
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setzt die Adresse des Benutzers.
     * @param address die Adresse
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gibt die Stadt des Benutzers zurück.
     * @return die Stadt
     */
    public String getCity() {
        return city;
    }

    /**
     * Setzt die Stadt des Benutzers.
     * @param city die Stadt
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gibt die Postleitzahl des Benutzers zurück.
     * @return die Postleitzahl
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Setzt die Postleitzahl des Benutzers.
     * @param postalCode die Postleitzahl
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * Gibt die Kontakttelefonnummer des Benutzers zurück.
     * @return die Kontakttelefonnummer
     */
    public String getContactPhonenumber() {
        return contactPhonenumber;
    }

    /**
     * Setzt die Kontakttelefonnummer des Benutzers.
     * @param contactPhonenumber die Kontakttelefonnummer
     */
    public void setContactPhonenumber(String contactPhonenumber) {
        this.contactPhonenumber = contactPhonenumber;
    }

    /**
     * Gibt die Rolle des Benutzers zurück.
     * @return die Rolle
     */
    public boolean getRole() {
        return this.role;
    }

    /**
     * Setzt die Rolle des Benutzers.
     * @param role die Rolle
     */
    public void setRole(boolean role) {
        this.role = role;
    }




}