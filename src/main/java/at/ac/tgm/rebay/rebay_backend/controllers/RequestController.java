package at.ac.tgm.rebay.rebay_backend.controllers;

import at.ac.tgm.rebay.rebay_backend.models.Request;
import at.ac.tgm.rebay.rebay_backend.models.User;
import at.ac.tgm.rebay.rebay_backend.services.RequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RequestMapping("/requests")
@RestController
public class RequestController {

    private final RequestService requestService;

    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET})
    @GetMapping("/all")
    public ResponseEntity<Set<Request>> getAllUserRequests() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        Set<Request> requests = requestService.getAllRequestsForUser(currentUser);

        return ResponseEntity.ok(requests);
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.POST})
    @PostMapping("/create/{id}")
    public ResponseEntity<Request> createRequest(@PathVariable int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        Request request = requestService.createRequest(id, currentUser);

        return ResponseEntity.ok(request);
    }
}
