package at.ac.tgm.rebay.rebay_backend.services;

import at.ac.tgm.rebay.rebay_backend.dtos.LoginUserDto;
import at.ac.tgm.rebay.rebay_backend.dtos.UserDto;
import at.ac.tgm.rebay.rebay_backend.models.Role;
import at.ac.tgm.rebay.rebay_backend.models.RoleEnum;
import at.ac.tgm.rebay.rebay_backend.models.User;
import at.ac.tgm.rebay.rebay_backend.repositories.RoleRepository;
import at.ac.tgm.rebay.rebay_backend.repositories.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            RoleRepository roleRepository,
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.roleRepository = roleRepository;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    public User signup(UserDto input) {

        Role anbieter = roleRepository.findByRoleName(RoleEnum.ANBIETER).orElseThrow();
        Role abnehmer = roleRepository.findByRoleName(RoleEnum.ABNEHMER).orElseThrow();

        User user = new User();

        user.setName(input.getName());
        user.setAddress(input.getAddress());
        user.setPostalCode(input.getPostalCode());
        user.setCity(input.getCity());
        user.setContactPhonenumber(input.getContactPhonenumber());
        user.setLoginContactEmail(input.getLoginContactEmail());


        if (input.getRole()) { //true = anbieter, false = abnehmer

            user.setRole(anbieter);
        } else {

            user.setRole(abnehmer);
        }

        user.setPassword(passwordEncoder.encode(input.getPassword()));

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto input) {

        auth(input);

        return userRepository.findByLoginContactEmail(input.getLoginContactEmail()).orElseThrow();
    }

    public User authenticateAdmin(LoginUserDto input) {

        auth(input);

        User user = userRepository.findByLoginContactEmail(input.getLoginContactEmail()).orElseThrow();

        if (user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {

            return user;
        } else {

            throw new AccessDeniedException("User is not an admin");
        }
    }


    private void auth(LoginUserDto input) {

        try {

            authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(

                            input.getLoginContactEmail().toLowerCase(),
                            input.getPassword()
                    )
            );
            System.out.println("Authentication successful for: " + input.getLoginContactEmail());
        } catch (DisabledException exc) {
            System.err.println("Authentication failed, disabled: " + exc.getMessage());
            throw exc;
        } catch (LockedException exc){
            System.err.println("Authentication failed, locked: " + exc.getMessage());
            throw exc;
        } catch (BadCredentialsException exc) {
            System.err.println("Authentication failed, badcredentials: " + exc.getMessage());
            throw exc;
        }
    }
}
