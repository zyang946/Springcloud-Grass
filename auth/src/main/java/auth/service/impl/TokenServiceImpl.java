package auth.service.impl;

import com.springboot.cloud.util.Response;

import auth.constant.InfoConstant;
import auth.dto.BasicAuthDto;
import auth.dto.TokenDto;
import auth.entity.User;
import auth.exception.UserOperationException;
import auth.repository.UserRepository;
import auth.security.jwt.JWTProvider;
import auth.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;

@Service
public class TokenServiceImpl implements TokenService {
    private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public Response getToken(BasicAuthDto dto, HttpHeaders headers) {
        String id = dto.getId();
        String password = dto.getPassword();
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(id,
                passwordEncoder.encode(password));
        try {
            authenticationManager.authenticate(token);
        } catch (AuthenticationException e) {
            LOGGER.warn("[getToken][Incorrect username or password][username: {}, password: {}]", id, password);
            return new Response<>(0, "Incorrect username or password.", null);
        }

        User user = userRepository.findByStudentId(id)
                .orElseThrow(() -> new UserOperationException(MessageFormat.format(
                        InfoConstant.ID_NOT_FOUND, id
                )));

        String providedToken = jwtProvider.createToken(user);
        LOGGER.info("[getToken][success][USER TOKEN: {} USER ID: {}]", token, user.getUserId());
        return new Response<>(1, "login success", new TokenDto(user.getUserId(), id, providedToken));
    }
}
