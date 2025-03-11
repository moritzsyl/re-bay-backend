package at.ac.tgm.rebay.rebay_backend.repositories;

import at.ac.tgm.rebay.rebay_backend.models.Product;
import at.ac.tgm.rebay.rebay_backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends CrudRepository<Product, Integer> {
    Optional<List<Product>> findByOwner(User owner); // Produkt über den Besitzer finden, Methodenname ist wichtig!
}

