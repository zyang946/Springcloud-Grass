package auth.init;

import auth.constant.AuthConstant;
import auth.constant.InfoConstant;
import auth.entity.User;
import auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;

@Component
public class initUser implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User whetherExistAdmin = userRepository.findByStudentId("root").orElse(new User());
        if (whetherExistAdmin.getUsername() == null) {
            User admin = User.builder()
                    .userId(1)
                    .userName("root")
                    .password(passwordEncoder.encode(InfoConstant.DEFAULT_PASSWORD))
                    .roles(new HashSet<>(Collections.singletonList(AuthConstant.ROLE_ADMIN)))
                    .build();
            userRepository.save(admin);
        }
    }
}
