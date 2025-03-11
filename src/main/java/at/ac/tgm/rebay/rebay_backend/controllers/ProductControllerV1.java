package at.ac.tgm.rebay.rebay_backend.controllers;

import at.ac.tgm.rebay.rebay_backend.dtos.ProductDto;
import at.ac.tgm.rebay.rebay_backend.models.Product;
import at.ac.tgm.rebay.rebay_backend.models.User;
import at.ac.tgm.rebay.rebay_backend.services.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;
import java.util.List;

@RequestMapping("/products")
@RestController
public class ProductControllerV1 {

    private final ProductService productService;

    public ProductControllerV1 (ProductService productService) {
        this.productService = productService;
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET})
    @GetMapping("/catalog")
    public ResponseEntity<List<Product>> getAllCatalogProducts() {

        List<Product> products = productService.getAllProducts();

        return ResponseEntity.ok(products);
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ANBIETER')")
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllUserOwnedProducts() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        List<Product> ownedProducts = productService.getAllOwnedProducts(currentUser);

        return ResponseEntity.ok(ownedProducts);
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ANBIETER') or hasRole('ROLE_ABNEHMER')")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductDetails(@PathVariable int id) {
        Product product = productService.getProduct(id);

        return ResponseEntity.ok(product);
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ANBIETER')")
    @PostMapping("/create")
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto createProductDto) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        Product savedProduct = productService.createProduct(createProductDto, currentUser);

        return ResponseEntity.ok(savedProduct);
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.PATCH})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ANBIETER')")
    @PatchMapping("/update/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody ProductDto updateProductDto) throws IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        Product updatedProduct = productService.updateProduct(id, updateProductDto, currentUser);

        return ResponseEntity.ok(updatedProduct);
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.DELETE})
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_ANBIETER')")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        Product product = productService.deleteProduct(id, currentUser);

        return ResponseEntity.ok(product);
    }
}
