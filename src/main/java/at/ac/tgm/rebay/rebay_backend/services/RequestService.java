package at.ac.tgm.rebay.rebay_backend.services;

import at.ac.tgm.rebay.rebay_backend.models.Product;
import at.ac.tgm.rebay.rebay_backend.models.Request;
import at.ac.tgm.rebay.rebay_backend.models.User;
import at.ac.tgm.rebay.rebay_backend.repositories.ProductRepository;
import at.ac.tgm.rebay.rebay_backend.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class RequestService {

    private final RequestRepository requestRepository;

    private final ProductRepository productRepository;

    public RequestService(RequestRepository requestRepository, ProductRepository productRepository) {
        this.requestRepository = requestRepository;
        this.productRepository = productRepository;
    }

    public Set<Request> getAllRequestsForUser(User user) {
        return requestRepository.findAllByUser(user).orElseThrow();
    }

    public Request createRequest(int id, User user) {
        Product product = productRepository.findById(id).orElseThrow();
        if (requestRepository.findByUserAndProduct(user, product).orElse(null) == null) {
            Request request = new Request();
            request.setUser(user);
            request.setProduct(product);
            requestRepository.save(request);
            return request;
        } else {
            throw new RuntimeException("Request already exists");
        }
    }
}
