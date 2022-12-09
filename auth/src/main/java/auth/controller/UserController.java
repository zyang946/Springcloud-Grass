package auth.controller;

import com.springboot.cloud.util.Response;

import auth.dto.BasicAuthDto;
import auth.dto.MenusDto;
import auth.dto.PageDto;
import auth.dto.PermissionDto;
import auth.dto.UserWithSeniorDto;
import auth.entity.RoleToMenu;
import auth.entity.User;
import auth.exception.UserOperationException;
import auth.service.TokenService;
import auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public ResponseEntity<Response> getToken(@RequestBody BasicAuthDto dao, @RequestHeader HttpHeaders headers) {
        logger.info("Login request of username: {}", dao.getId());
        try {
            Response<?> res = tokenService.getToken(dao, headers);
            return ResponseEntity.ok(res);
        } catch (UserOperationException e) {
            logger.error("[getToken][tokenService.getToken error][UserOperationException, message: {}]",
                    e.getMessage());
            return ResponseEntity.ok(new Response<>(0, "get token error", null));
        }
    }

    @PostMapping("/getUserInfo")
    public ResponseEntity<Response> getUserInfoById(@RequestParam("id") String id, @RequestHeader HttpHeaders headers) {
        logger.info("[Get user info by studentId][studentId: {}]", id);
        try {
            HashMap<String, Object> map = new HashMap<>();
            User user = userService.getUserInfoById(id, headers);
            map.put("user", user);
            Set<String> menus = new HashSet<>();
            for (String role : user.getRoles()) {
                RoleToMenu roleToMenu = userService.getRoleToMenuByRole(role);
                menus.addAll(roleToMenu.getMenus().stream().map(
                        MenusDto::getMenuKey
                ).collect(Collectors.toList()));
            }
            PermissionDto permission = new PermissionDto(new ArrayList<>(menus), new ArrayList<>());
            map.put("permission", permission);
            return ResponseEntity.ok(new Response<>(1, "success", map));
        } catch (UserOperationException e) {
            logger.error("[Get user info by studentId][Error: {}]", e.getMessage());
            return ResponseEntity.ok(new Response<>(0, "get user info error", null));
        }
    }

    @PostMapping("/getUsers")
    public ResponseEntity<Response> getAllUser(@RequestBody PageDto pageDto, @RequestHeader HttpHeaders headers) {
        logger.info("[getAllUser][Get all users]");
        HashMap<String, Object> res = new HashMap<>();
        long total = userService.getUserCount();
        res.put("total", total);
        List<User> list = userService.getAllUsers(pageDto, headers);
        List<UserWithSeniorDto> userList = new ArrayList<>();
        for (User user : list) {
            UserWithSeniorDto userWithSeniorDto = UserWithSeniorDto.builder()
                    .userId(user.getUserId())
                    .id(user.getStudentId())
                    .name(user.getUsername())
                    .department(user.getDepartment())
                    .phone(user.getPhone())
                    .to_id(user.getSeniorId())
                    .to_name(userService.getUserInfoById(user.getSeniorId(), headers).getUsername())
                    .roles(new ArrayList<>(user.getRoles())).build();
            userList.add(userWithSeniorDto);
        }
        res.put("userList", userList);
        return ResponseEntity.ok().body(new Response<>(1, "success", res));
    }

    @PostMapping("/deleteUser")
    public ResponseEntity<Response> deleteUserById(@RequestParam("userId") Integer userId,
                                                   @RequestHeader HttpHeaders headers) {
        logger.info("[deleteUserById][Delete user][userId: {}]", userId);
        return ResponseEntity.ok(userService.deleteByUserId(userId, headers));
    }

    @PostMapping("/updateUser")
    public ResponseEntity<Response> updateUser(@RequestBody UserWithSeniorDto userWithSeniorDto,
                                               @RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok(userService.updateUser(userWithSeniorDto, headers));
    }
}
