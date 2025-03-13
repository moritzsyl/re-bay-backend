package at.ac.tgm.rebay.rebay_backend.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "product_images")
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false , name = "image_id")
    private int id;

    @Column(nullable = false , name = "image_data")
    private String image;

    @Column(nullable = false , name = "is_main_image")
    private boolean isMainImage;

    @ManyToOne
    @JoinColumn(nullable = false, name = "product_id")
    @JsonBackReference
    private Product product;

    // Getter und Setter


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isMainImage() {
        return isMainImage;
    }

    public void setIsMainImage(boolean mainImage) {
        isMainImage = mainImage;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

