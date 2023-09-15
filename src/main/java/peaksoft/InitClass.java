package peaksoft;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import peaksoft.enums.Role;
import peaksoft.models.User;
import peaksoft.repository.UserRepository;

import java.time.LocalDate;

@Component
public class InitClass {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @PostConstruct
    public void init() {
        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("admin");
        user.setEmail("abmin@gmail.com");
        user.setPassword(passwordEncoder.encode("admin"));
        user.setRole(Role.ADMIN);
        user.setCreatedDate(LocalDate.now());
        if (userRepository.existsByEmail("admin@gmail.com")) {
            userRepository.save(user);
        }
    }
}
