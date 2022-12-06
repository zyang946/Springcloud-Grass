package auth.init;

import auth.constant.AuthConstant;
import auth.entity.User;
import auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.UUID;

@Component
public class initUser implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User whetherExistAdmin = userRepository.findByUserName("18373526").orElse(new User());
        if (whetherExistAdmin.getUsername() == null) {
            User admin = User.builder()
                    .userID(UUID.randomUUID().toString())
                    .userName("18373526")
                    .password(passwordEncoder.encode("123456"))
                    .roles(new HashSet<>(Arrays.asList(AuthConstant.ROLE_ADMIN)))
                    .build();
            userRepository.save(admin);
        }
    }
}
