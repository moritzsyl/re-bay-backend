package at.ac.tgm.rebay.rebay_backend.controllers;

import at.ac.tgm.rebay.rebay_backend.dtos.LoginResponseDto;
import at.ac.tgm.rebay.rebay_backend.dtos.LoginUserDto;
import at.ac.tgm.rebay.rebay_backend.models.Product;
import at.ac.tgm.rebay.rebay_backend.models.User;
import at.ac.tgm.rebay.rebay_backend.services.AuthenticationService;
import at.ac.tgm.rebay.rebay_backend.services.JwtService;
import at.ac.tgm.rebay.rebay_backend.services.ProductService;
import at.ac.tgm.rebay.rebay_backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.util.List;

@RequestMapping("/admin")
@RestController
public class AdminController {

    private final JwtService jwtService;
    private final ProductService productService;
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public AdminController(JwtService jwtService, ProductService productService, UserService userService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.productService = productService;
        this.userService = userService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) throws AccessDeniedException {

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        User authenticatedUser = authenticationService.authenticateAdmin(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        loginResponseDto.setToken(jwtToken);
        loginResponseDto.setExpiresIn(jwtService.getJwtExpirationInMs());

        return ResponseEntity.ok(loginResponseDto);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @RequestMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts() {

        List<Product> products = productService.getAllProducts();

        return ResponseEntity.ok(products);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public ResponseEntity<List<User>> allUsers() {
        List <User> users = userService.allUsers();

        return ResponseEntity.ok(users);
    }
}
