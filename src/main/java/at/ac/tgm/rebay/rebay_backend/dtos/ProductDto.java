package at.ac.tgm.rebay.rebay_backend.dtos;

import at.ac.tgm.rebay.rebay_backend.models.CategoryEnum;
import at.ac.tgm.rebay.rebay_backend.models.ConditionEnum;

import java.util.List;

public class ProductDto {

    private String productName;

    private String model;

    private String manufacturer;

    private int stock;

    private String description;

    private CategoryEnum category;

    private ConditionEnum condition;

    private List<String> images;

    // Getter und Setter

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public ConditionEnum getCondition() {
        return condition;
    }

    public void setCondition(ConditionEnum condition) {
        this.condition = condition;
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

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
