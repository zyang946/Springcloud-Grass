package auth.controller;

import com.springboot.cloud.util.Response;

import auth.dto.AuthDto;
import auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    private static final Logger logger = LoggerFactory.getLogger(AuthController.class);

    public HttpEntity<Response> createDefaultUser(@RequestBody AuthDto authDto) {
        logger.info("[createDefaultUser][Create default auth user with authDto][AuthDto: {}]", authDto.toString());
        userService.createDefaultAuthUser(authDto);
        Response response = new Response(200, "Success", authDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
