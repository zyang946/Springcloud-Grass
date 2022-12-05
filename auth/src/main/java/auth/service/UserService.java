package auth.service;


import com.springboot.cloud.util.Response;

import auth.dto.AuthDto;
import auth.entity.User;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    List<User> getAllUsers(HttpHeaders headers);

    User createDefaultAuthUser(AuthDto dto);

    Response deleteByUserId(String userId, HttpHeaders headers);
}
