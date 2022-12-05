package auth.service.impl;

import com.springboot.cloud.util.Response;

import auth.constant.AuthConstant;
import auth.constant.InfoConstant;
import auth.dto.AuthDto;
import auth.entity.User;
import auth.exception.UserOperationException;
import auth.repository.UserRepository;
import auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public List<User> getAllUsers(HttpHeaders headers) {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public User createDefaultAuthUser(AuthDto dto) {
        //TODO: register to create uid
        LOGGER.info("[createDefaultAuthUser][Register User Info][AuthDto id: {}]", dto.getId());
        User user = User.builder()
                .userID(dto.getUserId())
                .userName(dto.getId())
                .password(dto.getPassword())
                .roles(new HashSet<>(Arrays.asList(AuthConstant.ROLE_STUDENT)))
                .build();

        try {
            checkUserCreateInfo(user);
        } catch (UserOperationException e) {
            LOGGER.error("[createDefaultAuthUser][Create default auth user][UserOperationException][message: {}]",
                    e.getMessage());
        }
        return userRepository.save(user);
    }

    @Override
    public Response deleteByUserId(String userId, HttpHeaders headers) {
        LOGGER.info("[deleteByUserId][DELETE USER][user id: {}]", userId);
        userRepository.deleteByUserID(userId);
        return new Response(1, "Success", null);
    }

    private void checkUserCreateInfo(User user) {
        LOGGER.info("[checkUserCreateInfo][Check user create info][userId: {}, id: {}]", user.getUserId(),
                user.getUsername());
        List<String> infos = new ArrayList<>();

        if (null == user.getUsername() || "".equals(user.getUsername())) {
            infos.add(MessageFormat.format(InfoConstant.PROPERTIES_CANNOT_BE_EMPTY, InfoConstant.USERNAME));
        }

        int passwordMaxLength = 6;
        if (null == user.getPassword()) {
            infos.add(MessageFormat.format(InfoConstant.PROPERTIES_CANNOT_BE_EMPTY, InfoConstant.PASSWORD));
        } else if (user.getPassword().length() < passwordMaxLength) {
            infos.add(MessageFormat.format(InfoConstant.PASSWORD_LEAST_CHAR, 6));
        }

        if (null == user.getRoles() || user.getRoles().isEmpty()) {
            infos.add(MessageFormat.format(InfoConstant.PROPERTIES_CANNOT_BE_EMPTY, InfoConstant.ROLES));
        }

        if (!infos.isEmpty()) {
            LOGGER.warn(infos.toString());
            throw new UserOperationException(infos.toString());
        }
    }
}
