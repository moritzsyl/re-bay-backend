package at.ac.tgm.rebay.rebay_backend.controllers;

import at.ac.tgm.rebay.rebay_backend.dtos.UserDto;
import at.ac.tgm.rebay.rebay_backend.models.User;
import at.ac.tgm.rebay.rebay_backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET})
    @PreAuthorize("hasRole('ROLE_ANBIETER') or hasRole('ROLE_ABNEHMER') or hasRole('ROLE_ADMIN')")
    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }

    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.PATCH})
    @PreAuthorize("hasRole('ROLE_ANBIETER') or hasRole('ROLE_ABNEHMER') or hasRole('ROLE_ADMIN')")
    @PatchMapping("/update")
    public ResponseEntity<User> updatedUser(@RequestBody UserDto userDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        User updatedUser = userService.updateUser(currentUser, userDto);

        return ResponseEntity.ok(updatedUser);
    }
}