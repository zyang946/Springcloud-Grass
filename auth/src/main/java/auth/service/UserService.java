package auth.service;


import com.springboot.cloud.util.Response;

import auth.dto.AuthDto;
import auth.dto.PageDto;
import auth.dto.RoleDto;
import auth.dto.UserWithSeniorDto;
import auth.entity.RoleToMenu;
import auth.entity.User;
import org.springframework.http.HttpHeaders;

import java.util.List;

public interface UserService {
    User saveUser(User user);

    User getUserInfoById(String studentId, HttpHeaders headers);

    List<User> getAllUsers(PageDto pageDto, HttpHeaders headers);

    long getUserCount();

    User createDefaultAuthUser(AuthDto dto);

    Response deleteByUserId(int userId, HttpHeaders headers);

    Response updateUser(UserWithSeniorDto userWithSeniorDto, HttpHeaders headers);

    RoleToMenu getRoleToMenuByRole(String role);

    List<RoleToMenu> getAllRoles();

    Response createRole(RoleDto roleDto);

    Response updateRole(RoleDto roleDto);

    Response deleteRole(Integer id);
}
