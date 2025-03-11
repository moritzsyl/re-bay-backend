package at.ac.tgm.rebay.rebay_backend.services;

import at.ac.tgm.rebay.rebay_backend.dtos.UserDto;
import at.ac.tgm.rebay.rebay_backend.models.User;
import at.ac.tgm.rebay.rebay_backend.repositories.UserRepository;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();

        this.userRepository.findAll().forEach(users::add);

        return users;
    }

    public User updateUser(User user, UserDto input) {
        if (user.getName().equals(input.getName())) {
            user.setName(input.getName());
        }
        if(user.getAddress().equals(input.getAddress())) {
            user.setAddress(input.getAddress());
        }
        if(user.getPostalCode().equals(input.getPostalCode())) {
            user.setPostalCode(input.getPostalCode());
        }
        if(user.getCity().equals(input.getCity())) {
            user.setCity(input.getCity());
        }
        if(user.getContactPhonenumber().equals(input.getContactPhonenumber())) {
            user.setContactPhonenumber(input.getContactPhonenumber());
        }
        user.setLoginContactEmail(input.getLoginContactEmail());

        return this.userRepository.save(user);
    }
}
