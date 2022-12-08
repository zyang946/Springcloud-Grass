package auth.service;


import com.springboot.cloud.util.Response;

import auth.dto.AuthDto;
import auth.entity.RoleToMenu;
import auth.entity.User;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User getUserInfoById(String studentId, HttpHeaders headers);

    List<User> getAllUsers(HttpHeaders headers);

    User createDefaultAuthUser(AuthDto dto);

    Response deleteById(String studentId, HttpHeaders headers);

    RoleToMenu getRoleToMenuByRole(String role);
}
