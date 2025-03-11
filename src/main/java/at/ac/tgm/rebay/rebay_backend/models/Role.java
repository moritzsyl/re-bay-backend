package at.ac.tgm.rebay.rebay_backend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.Date;

@Table(name = "roles")
@Entity
public class Role {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Generiert automatisch eine ID
    @Column(nullable = false, name = "role_id") //Spalte erlaubt keine NULL Werte
    private int id;

    @Column(unique = true, nullable = false, name = "role_name") //Spalte erlaubt keine NULL Werte und ist eindeutig
    @Enumerated(EnumType.STRING)
    private RoleEnum roleName;

    @Column(nullable = false, name = "role_description") //Spalte erlaubt keine NULL Werte
    private String description;

    @CreationTimestamp //Erstellungsdatum
    @Column(updatable = false, name = "role_created_at") //Spalte wird beim Erstellen des Datensatzes gesetzt und hat den Namen created_at
    private Date createdAt;

    @UpdateTimestamp //Aktualisierungsdatum
    @Column(name = "role_updated_at") //Spalte wird beim Aktualisieren des Datensatzes gesetzt und hat den Namen updated_at
    private Date updatedAt;

    //------------------------------------------------------------------------------------------------------------------
    //Getter und Setter für die Attribute der Klasse Role

    /**
     * Gibt die eindeutige Kennung der Rolle zurück.
     * @return die Rollen-ID
     */
    public int getId() {
        return id;
    }

    /**
     * Setzt die eindeutige Kennung der Rolle.
     * @param id die Rollen-ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gibt den Namen der Rolle zurück.
     * @return der Rollenname
     */
    public RoleEnum getRoleName() {
        return roleName;
    }

    /**
     * Setzt den Namen der Rolle.
     * @param roleName der Rollenname
     */
    public void setRoleName(RoleEnum roleName) {
        this.roleName = roleName;
    }

    /**
     * Gibt die Beschreibung der Rolle zurück.
     * @return die Rollenbeschreibung
     */
    public String getDescription() {
        return description;
    }

    /**
     * Setzt die Beschreibung der Rolle.
     * @param description die Rollenbeschreibung
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gibt das Datum und die Uhrzeit zurück, wann die Rolle zuletzt aktualisiert wurde.
     * @return das letzte Aktualisierungsdatum und die Uhrzeit
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * Setzt das Datum und die Uhrzeit, wann die Rolle zuletzt aktualisiert wurde.
     * @param updatedAt das letzte Aktualisierungsdatum und die Uhrzeit
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * Gibt das Datum und die Uhrzeit zurück, wann die Rolle erstellt wurde.
     * @return das Erstellungsdatum und die Uhrzeit
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * Setzt das Datum und die Uhrzeit, wann die Rolle erstellt wurde.
     * @param createdAt das Erstellungsdatum und die Uhrzeit
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
