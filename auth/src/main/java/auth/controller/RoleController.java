package auth.controller;

import com.springboot.cloud.util.Response;

import auth.dto.RoleDto;
import auth.entity.RoleToMenu;
import auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private UserService userService;

    @GetMapping("/getRoles")
    public ResponseEntity<Response> getAllRoles(@RequestHeader HttpHeaders headers) {
        List<HashMap<String, Object>> res = new ArrayList<>();
        List<RoleToMenu> roles = userService.getAllRoles();
        for (RoleToMenu role : roles) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", role.getUid());
            map.put("name", role.getRole());
            map.put("description", role.getDescription());
            map.put("permissions", role.getMenus());
            res.add(map);
        }
        return ResponseEntity.ok(new Response<>(1, "success", res));
    }

    @PostMapping("/addRole")
    public ResponseEntity<Response> addRole(@RequestBody RoleDto roleDto, @RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok(userService.createRole(roleDto));
    }

    @DeleteMapping("/deleteRole/{id}")
    public ResponseEntity<Response> deleteRole(@PathVariable("id") Integer id, @RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok(userService.deleteRole(id));
    }

    @PutMapping("/updateRole/{id}")
    public ResponseEntity<Response> updateRole(@RequestBody RoleDto roleDto, @PathVariable("id") Integer id,
                                               @RequestHeader HttpHeaders headers) {
        return ResponseEntity.ok(userService.updateRole(roleDto));
    }
}
