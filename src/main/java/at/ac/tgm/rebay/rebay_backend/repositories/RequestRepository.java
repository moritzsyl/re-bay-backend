package at.ac.tgm.rebay.rebay_backend.repositories;

import at.ac.tgm.rebay.rebay_backend.models.Product;
import at.ac.tgm.rebay.rebay_backend.models.Request;
import at.ac.tgm.rebay.rebay_backend.models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.Set;

public interface RequestRepository extends CrudRepository<Request, Integer> {
    Optional<Set<Request>> findAllByUser(User user);
    Optional<Request> findByUserAndProduct(User user, Product product);
}
