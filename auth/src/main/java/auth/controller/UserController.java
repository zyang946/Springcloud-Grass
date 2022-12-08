package auth.controller;

import com.springboot.cloud.util.Response;

import auth.dto.BasicAuthDto;
import auth.dto.Permission;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
                menus.addAll(roleToMenu.getMenus());
            }
            Permission permission = new Permission(new ArrayList<>(menus), new ArrayList<>());
            map.put("permission", permission);
            return ResponseEntity.ok(new Response<>(1, "success", map));
        } catch (UserOperationException e) {
            logger.error("[Get user info by studentId][Error: {}]", e.getMessage());
            return ResponseEntity.ok(new Response<>(0, "get user info error", null));
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser(@RequestHeader HttpHeaders headers) {
        logger.info("[getAllUser][Get all users]");
        return ResponseEntity.ok().body(userService.getAllUsers(headers));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Response> deleteUserById(@PathVariable String userId, @RequestHeader HttpHeaders headers) {
        logger.info("[deleteUserById][Delete user][userId: {}]", userId);
        return ResponseEntity.ok(userService.deleteById(userId, headers));
    }
}
