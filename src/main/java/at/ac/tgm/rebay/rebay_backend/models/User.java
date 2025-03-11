package at.ac.tgm.rebay.rebay_backend.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Table(name = "users")
@Entity
public class User implements UserDetails {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Generiert automatisch eine ID
    @Column(unique = true, nullable = false, name = "user_id") //Spalte erlaubt keine NULL Werte, ist eindeutig und hat den Namen user_id
    private int id;

    @Column(length = 100, nullable = false, name = "name") //Spalte erlaubt keine NULL Werte und hat den Namen name
    private String name;

    @Column(length = 100, nullable = false, name = "address") //Spalte erlaubt keine NULL Werte und hat den Namen address
    private String address;

    @Column(length = 6, nullable = false, name = "postal_code") //Spalte erlaubt keine NULL Werte, eine maximale Länge von 5 Zeichen und hat den Namen postal_code
    private String postalCode;

    @Column(length = 100, nullable = false, name = "city") //Spalte erlaubt keine NULL Werte und hat den Namen city
    private String city;

    @Column(length = 15, nullable = false, name = "contact_phonenumber") //Spalte erlaubt keine NULL Werte, eine maximale Länge von 15 Zeichen und hat den Namen contact_phonenumber
    private String contactPhonenumber;

    @Column(unique = true, length = 100, nullable = false, name = "login_and_contact_email") //Spalte erlaubt keine NULL Werte, eine maximale Länge von 100 Zeichen, ist eindeutig und hat den Namen login_contact_email
    private String loginContactEmail;

    @Column(nullable = false, name = "login_password") //Spalte erlaubt keine NULL Werte und hat den Namen login_password
    private String password;

    @Column(nullable = false, name = "enabled") //Spalte erlaubt keine NULL Werte und hat den Namen enabled
    private boolean enabled = false;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Request> requests;

    @ManyToOne(cascade = CascadeType.MERGE) //Beziehung zu einer anderen Entität. Eine Rolle kann von mehreren Benutzern verwendet werden. Wenn der User aktualisiert wird, wird auch die Rolle aktualisiert.
    @JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false) //Fremdschlüsselbeziehung zur Role-Entität
    private Role role;

    @CreationTimestamp //Erstellungsdatum
    @Column(updatable = false, name = "user_created_at") //Spalte wird beim Erstellen des Datensatzes gesetzt und hat den Namen created_at
    private Date createdAt;

    @UpdateTimestamp //Aktualisierungsdatum
    @Column(name = "user_updated_at") //Spalte wird beim Aktualisieren des Datensatzes gesetzt und hat den Namen updated_at
    private Date updatedAt;

    //------------------------------------------------------------------------------------------------------------------

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + this.role.getRoleName().toString());
        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    //Eindeutige Identifikation des Benutzers. Im Normalfall der Username, in diesem Fall die E-Mail Adresse. Ist gleich zu getLoginContactEmail()
    @Override
    public String getUsername() {
        return this.loginContactEmail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Prüft auf Identität
        if (obj == null || getClass() != obj.getClass()) return false; // Prüft auf null und gleiche Klasse
        User user = (User) obj; // Cast des Objekts

        return id == user.id && loginContactEmail.equals(user.loginContactEmail);
    }

    //------------------------------------------------------------------------------------------------------------------
    //Getter und Setter für die Attribute der Klasse User, abgesehen von dem Attribut Rolle

    /**
     * Gibt die eindeutige Kennung des Benutzers zurück.
     * @return die Benutzer-ID
     */
    public int getId() {
        return id;
    }

    /**
     * Setzt die eindeutige Kennung des Benutzers.
     * @param id die Benutzer-ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gibt die Rolle des Benutzers zurück.
     * @return die Rolle
     */
    public Role getRole() {
        return role;
    }

    /**
     * Setzt die Rolle des Benutzers.
     * @param role die Benutzer-ID
     */
    public User setRole(Role role) {
        this.role = role;
        return this;
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
     * Setzt das Login-Passwort des Benutzers.
     * @param password das Passwort
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gibt das Datum und die Uhrzeit zurück, wann der Benutzer erstellt wurde.
     * @return das Erstellungsdatum und die Uhrzeit
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Setzt das Datum und die Uhrzeit, wann der Benutzer erstellt wurde.
     * @param createdAt das Erstellungsdatum und die Uhrzeit
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * Gibt das Datum und die Uhrzeit zurück, wann der Benutzer zuletzt aktualisiert wurde.
     * @return das letzte Aktualisierungsdatum und die Uhrzeit
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Setzt das Datum und die Uhrzeit, wann der Benutzer zuletzt aktualisiert wurde.
     * @param updatedAt das letzte Aktualisierungsdatum und die Uhrzeit
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}
