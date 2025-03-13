package at.ac.tgm.rebay.rebay_backend.controllers;

import at.ac.tgm.rebay.rebay_backend.dtos.UserDto;
import at.ac.tgm.rebay.rebay_backend.services.AuthenticationService;
import at.ac.tgm.rebay.rebay_backend.dtos.LoginResponseDto;
import at.ac.tgm.rebay.rebay_backend.dtos.LoginUserDto;
import at.ac.tgm.rebay.rebay_backend.models.User;
import at.ac.tgm.rebay.rebay_backend.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/account")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody UserDto userDto) {

        User registeredUser = authenticationService.signup(userDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> authenticate(@RequestBody LoginUserDto loginUserDto) {

        LoginResponseDto loginResponseDto = new LoginResponseDto();
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String jwtToken = jwtService.generateToken(authenticatedUser);

        loginResponseDto.setUser(authenticatedUser);
        loginResponseDto.setToken(jwtToken);
        loginResponseDto.setExpiresIn(jwtService.getJwtExpirationInMs());

        return ResponseEntity.ok(loginResponseDto);
    }
}
