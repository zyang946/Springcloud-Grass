package auth.init;

import auth.constant.AuthConstant;
import auth.constant.InfoConstant;
import auth.dto.MenusDto;
import auth.entity.RoleToMenu;
import auth.entity.User;
import auth.repository.RoleRepository;
import auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Component
public class initUser implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User whetherExistAdmin = userRepository.findByStudentId("root").orElse(new User());
        if (whetherExistAdmin.getUsername() == null) {
            User admin = User.builder()
                    .userId(1)
                    .studentId("root")
                    .password(passwordEncoder.encode(InfoConstant.DEFAULT_PASSWORD))
                    .roles(new HashSet<>(Collections.singletonList(AuthConstant.ROLE_ADMIN)))
                    .build();
            userRepository.save(admin);
        }
        RoleToMenu role = roleRepository.findByRole(AuthConstant.ROLE_ADMIN);
        if (role == null) {
            Set<MenusDto> set = new HashSet<>();
            MenusDto menusDto;
            menusDto = MenusDto.builder()
                    .menuKey(InfoConstant.MANAGE_KEY)
                    .name(InfoConstant.MANAGE_NAME)
                    .build();
            set.add(menusDto);
            menusDto = MenusDto.builder()
                    .menuKey(InfoConstant.REVIEW_KEY)
                    .name(InfoConstant.REVIEW_NAME)
                    .build();
            set.add(menusDto);
            menusDto = MenusDto.builder()
                    .menuKey(InfoConstant.APPLY_KEY)
                    .name(InfoConstant.APPLY_NAME)
                    .build();
            set.add(menusDto);
            role = RoleToMenu.builder()
                    .role(AuthConstant.ROLE_ADMIN)
                    .description("我是无敌的管理员")
                    .menus(set)
                    .build();
            roleRepository.save(role);
        }
    }
}
