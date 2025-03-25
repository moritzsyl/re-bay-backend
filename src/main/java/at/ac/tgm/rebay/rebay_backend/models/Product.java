package at.ac.tgm.rebay.rebay_backend.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Die Klasse Product repräsentiert ein Produkt auf der ReBay-Plattform für den Tausch gebrauchter Elektronik.
 * Jedes Produkt auf der Plattform verfügt über eine ID, einen Namen, eine Kategorie, ein Modell,
 * einen Hersteller und einen Zustand, die die Informationen zur Identifikation und zum Zustand des Produkts speichern.
 *
 */
@Table(name = "products")
@Entity
public class Product {

    @Id //Primary Key
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Generiert automatisch eine ID
    @Column(unique = true, nullable = false, name = "product_id") //Spalte erlaubt keine NULL Werte
    private int id; //Eindeutige ID zur Identifikation des Produkts in der Datenbank

    @Column(length = 100, nullable = false, name = "product_name")
    private String productName; //Beschreibung oder Bezeichnung des Produkts

    @Column(length = 100, nullable = false, name = "product_model")
    private String model; //Modellbezeichnung des Produkts

    @Column(length = 100, nullable = false, name = "product_manufacturer")
    private String manufacturer; //Name des Herstellers des Produkts

    @Column(nullable = false, name = "product_description")
    private String description; //Beschreibung des Produk

    @Column(nullable = false, name = "product_stock")
    private int stock;

    @Column(nullable = false, name = "product_category")
    @Enumerated(EnumType.STRING)
    private CategoryEnum category; //Kategorie, die die Art des Produkts spezifiziert

    @Column(nullable = false, name = "product_condition")
    @Enumerated(EnumType.STRING)
    private ConditionEnum condition; //Der physische Zustand des Produkts (z.B. neu, gebraucht, aufgearbeitet)

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<ProductImage> images = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.MERGE) //Beziehung zu einer anderen Entität. Ein Produkt kann nur von einem Benutzer besessen werden. Wenn das Produkt aktualisiert wird, wird auch der Benutzer aktualisiert.
    @JoinColumn(name = "owner_user_id", referencedColumnName = "user_id", nullable = false) //Fremdschlüsselbeziehung zum Benutzer, der das Produkt besitzt
    private User owner; //Der Benutzer, der das Produkt besitzt

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Request> requests;

    @CreationTimestamp //Erstellungsdatum
    @Column(updatable = false, name = "product_created_at") //Spalte wird beim Erstellen des Datensatzes gesetzt und hat den Namen created_at
    private Date createdAt;

    @UpdateTimestamp //Aktualisierungsdatum
    @Column(name = "product_updated_at") //Spalte wird beim Aktualisieren des Datensatzes gesetzt und hat den Namen updated_at
    private Date updatedAt;

    //------------------------------------------------------------------------------------------------------------------
    //Getter und Setter für die Attribute der Klasse Product

    /**
     * Gibt die eindeutige ID des Produkts zurück.
     * @return die ID des Produkts
     */
    public int getId() { return id; }

    /**
     * Setzt eine neue ID für das Produkt.
     * @param id die neue eindeutige ID des Produkts
     */
    public void setId(int id) { this.id = id; }

    /**
     * Gibt den Namen des Produkts zurück.
     * @return der Name des Produkts, der das Gerät beschreibt
     */
    public String getProductName() { return this.productName; }

    /**
     * Setzt einen neuen Namen für das Produkt.
     * @param name der neue Name des Produkts
     */
    public void setProductName(String name) { this.productName = name; }

    /**
     * Gibt die Kategorie des Produkts zurück.
     * @return die Kategorie, zu der das Produkt gehört
     */
    public CategoryEnum getCategory() { return this.category; }

    /**
     * Setzt die Kategorie des Produkts, um dessen Art zu spezifizieren.
     * @param category die neue Kategorie des Produkts
     */
    public void setCategory(CategoryEnum category) { this.category = category; }

    /**
     * Gibt das Modell des Produkts zurück.
     * @return das Modell des Produkts, welches die Produktspezifikation genauer beschreibt
     */
    public String getModel() { return model; }

    /**
     * Setzt ein neues Modell für das Produkt.
     * @param model das neue Modell des Produkts
     */
    public void setModel(String model) { this.model = model; }

    /**
     * Gibt den Hersteller des Produkts zurück.
     * @return der Hersteller, also die Marke des Produkts
     */
    public String getManufacturer() { return manufacturer; }

    /**
     * Setzt einen neuen Hersteller für das Produkt.
     * @param manufacturer der neue Hersteller des Produkts
     */
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    /**
     * Gibt den Zustand des Produkts zurück, z.B. neu oder gebraucht.
     * @return der Zustand des Produkts
     */
    public ConditionEnum getCondition() { return condition; }

    /**
     * Setzt den Zustand des Produkts, um dessen Qualität oder Nutzungshistorie zu spezifizieren.
     * @param condition der neue Zustand des Produkts
     */
    public void setCondition(ConditionEnum condition) { this.condition = condition; }

    public List<ProductImage> getImages() {
        return images;
    }

    public void setImages(List<ProductImage> images) {
        this.images = images;
    }

    public Set<Request> getRequests() {
        return requests;
    }

    public void setRequests(Set<Request> requests) {
        this.requests = requests;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public void addImage(ProductImage image) {
//        images.add(image);
//        image.setProduct(this);
//    }
//
//    public void removeImage(ProductImage image) {
//        images.remove(image);
//        image.setProduct(null);
//    }

//    public List<ProductImage> getImages() {
//        return images;
//    }
//
//    public void setImages(List<ProductImage> images) {
//        this.images = images;
//    }

    /**
     * Gibt den Benutzer zurück, der das Produkt besitzt.
     * @return der Benutzer, der das Produkt besitzt
     */
    public User getOwner() { return owner; }

    /**
     * Setzt den Benutzer, der das Produkt besitzt.
     * @param owner der Benutzer, der das Produkt besitzt
     */
    public void setOwner(User owner) { this.owner = owner; }

    /**
     * Gibt eine String-Repräsentation des Produkts zurück, die alle relevanten Informationen in einem lesbaren Format auflistet.
     * @return eine lesbare String-Darstellung der Produktinformationen
     */
    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + this.productName + '\'' +
                ", category=" + category +
                ", model='" + model + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", condition=" + condition +
                '}';
    }
}
