package at.ac.tgm.rebay.rebay_backend.repositories;

import at.ac.tgm.rebay.rebay_backend.models.ProductImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductImageRepository extends CrudRepository<ProductImage, Integer> {
    void deleteByProductId(int productId);
    List<ProductImage> findByProductId(int productId);
}
