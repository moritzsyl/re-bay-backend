package at.ac.tgm.rebay.rebay_backend.controllers;

import at.ac.tgm.rebay.rebay_backend.config.LoginResponse;
import at.ac.tgm.rebay.rebay_backend.dtos.LoginUserDto;
import at.ac.tgm.rebay.rebay_backend.models.User;
import at.ac.tgm.rebay.rebay_backend.services.AuthenticationService;
import at.ac.tgm.rebay.rebay_backend.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.AccessDeniedException;

@RequestMapping("/admin")
@RestController
public class AdminAuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AdminAuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) throws AccessDeniedException {

        LoginResponse loginResponse = new LoginResponse();
        User authenticatedUser = authenticationService.authenticateAdmin(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getJwtExpirationInMs());

        return ResponseEntity.ok(loginResponse);
    }
}
