package at.ac.tgm.rebay.rebay_backend.services;

import at.ac.tgm.rebay.rebay_backend.dtos.ProductDto;
import at.ac.tgm.rebay.rebay_backend.dtos.ProductResponseDto;
import at.ac.tgm.rebay.rebay_backend.models.Product;
import at.ac.tgm.rebay.rebay_backend.models.ProductImage;
import at.ac.tgm.rebay.rebay_backend.models.Request;
import at.ac.tgm.rebay.rebay_backend.models.User;
import at.ac.tgm.rebay.rebay_backend.repositories.ProductImageRepository;
import at.ac.tgm.rebay.rebay_backend.repositories.ProductRepository;
import at.ac.tgm.rebay.rebay_backend.repositories.RequestRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.*;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    //------------------------------------------------------------------------------------------------------------------

    private Product saveProductImages(ProductDto createProductDto, Product savedProduct) throws IOException {
        for (int i = 0; i < createProductDto.getImages().size(); i++) {
            String base64Image = createProductDto.getImages().get(i);
            String imageUrl = saveBase64Image(base64Image); // Methode zum Speichern des Bildes

            ProductImage productImage = new ProductImage();
            productImage.setImage(imageUrl);
            productImage.setIsMainImage(i == 0);
            productImage.setProduct(savedProduct);
            productImageRepository.save(productImage);
            savedProduct.getImages().add(productImage);
        }
        return this.productRepository.save(savedProduct);
    }

    private void setProductAttributes(ProductDto createProductDto, Product product) {
        product.setProductName(createProductDto.getProductName());
        product.setModel(createProductDto.getModel());
        product.setManufacturer(createProductDto.getManufacturer());
        product.setStock(createProductDto.getStock());
        product.setDescription(createProductDto.getDescription());
        product.setCategory(createProductDto.getCategory());
        product.setCondition(createProductDto.getCondition());
    }

    private String saveBase64Image(String base64Image) throws IOException {
        // Base64-Daten decodieren und als Datei speichern
        String[] parts = base64Image.split(",");
        String imageDataBytes = parts[1];
        byte[] imageBytes = Base64.getDecoder().decode(imageDataBytes);

        String dateiName = UUID.randomUUID().toString() + ".jpg"; // Annahme: JPG-Format
        Path dateiPfad = Paths.get("uploads/" + dateiName);
        Files.write(dateiPfad, imageBytes);

        return dateiPfad.toString();
    }

    private String encodeImageToBase64(String imagePath) throws IOException {
        Path path = Paths.get(imagePath);
        byte[] imageBytes = Files.readAllBytes(path);
        return Base64.getEncoder().encodeToString(imageBytes);
    }

    //------------------------------------------------------------------------------------------------------------------

    public Product createProduct(ProductDto createProductDto, User owner) throws IOException{

        Product product = new Product();
        setProductAttributes(createProductDto, product);
        product.setOwner(owner);
        product.setImages(new ArrayList<>());

        Product savedProduct = this.productRepository.save(product);

        return saveProductImages(createProductDto, savedProduct);
    }

    public Product updateProduct(int id, ProductDto updateProductDto, User owner) throws IOException{

        Product product = this.productRepository.findById(id).orElseThrow();

        if (product.getOwner().equals(owner) && product.getOwner().getId() == owner.getId()) {
            setProductAttributes(updateProductDto, product);

            // Alte Bilder löschen
            productImageRepository.deleteByProductId(id);
            // Neue Bilder hinzufügen
            product.setImages(new ArrayList<>());
            return saveProductImages(updateProductDto, product);
        } else {
            throw new AccessDeniedException("User is not the owner of the product");
        }
    }

    public Product deleteProduct(int id, User owner) {

        Product product = this.productRepository.findById(id).orElseThrow();

        if (product.getOwner().equals(owner) && product.getOwner().getId() == owner.getId()) {
            this.productRepository.delete(product);

            return product;
        } else {
            throw new AccessDeniedException("User is not the owner of the product");
        }
    }

    public List<ProductResponseDto> getAllOwnedProducts(User owner) throws IOException {
        List<Product> products = this.productRepository.findByOwner(owner).orElseThrow();

        return getProductDtos(products);
    }

    public ProductResponseDto getSingleProduct(int id) throws IOException {
        Product product = this.productRepository.findById(id).orElseThrow();

        List<String> base64Images = new ArrayList<>();

        for(ProductImage image : product.getImages()) {
            String base64Image = encodeImageToBase64(image.getImage());
            base64Images.add(base64Image);
        }

        return new ProductResponseDto(product.getId(), product.getProductName(), product.getModel(), product.getManufacturer(), product.getStock(),
                product.getDescription(), product.getCategory(), product.getCondition(), base64Images);
    }

    public List<ProductResponseDto> getAllProducts() throws IOException{

        List<Product> products = new ArrayList<>();

        this.productRepository.findAll().forEach(products::add);

        return getProductDtos(products);
    }

    private List<ProductResponseDto> getProductDtos(List<Product> products) throws IOException {
        List<ProductResponseDto> productDtos = new ArrayList<>();

        for (Product product : products) {
            List<String> base64Images = new ArrayList<>();

            for (ProductImage image : product.getImages()) {
                String base64Image = encodeImageToBase64(image.getImage());
                base64Images.add(base64Image);
            }

            productDtos.add(new ProductResponseDto(product.getId(),product.getProductName(), product.getModel(), product.getManufacturer(), product.getStock(),
                    product.getDescription(), product.getCategory(), product.getCondition(), base64Images));
        }

        return productDtos;
    }
}
