package auth.service.impl;

import com.springboot.cloud.util.Response;
import com.springboot.cloud.util.StringUtils;

import auth.constant.InfoConstant;
import auth.dto.AuthDto;
import auth.dto.PageDto;
import auth.dto.UserWithSeniorDto;
import auth.entity.RoleToMenu;
import auth.entity.User;
import auth.exception.UserOperationException;
import auth.repository.RoleRepository;
import auth.repository.UserRepository;
import auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.Predicate;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    protected PasswordEncoder passwordEncoder;

    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public User getUserInfoById(String studentId, HttpHeaders headers) {
        return userRepository.findByStudentId(studentId).orElseThrow(
                () -> new UserOperationException(MessageFormat.format(InfoConstant.ID_NOT_FOUND, studentId))
        );
    }

    @Override
    public List<User> getAllUsers(PageDto pageDto, HttpHeaders headers) {
        String sort = pageDto.getSort();
        // 按照id递增
        Sort mySort = null;
        if (sort.equals("+id")) {
            mySort = Sort.by(Sort.Direction.ASC, "studentId");
        } else {
            mySort = Sort.by(Sort.Direction.DESC, "studentId");
        }
        Pageable pageable = PageRequest.of(pageDto.getPage(), pageDto.getLimit(), mySort);
        Specification<User> specification = (root, criteriaQuery, criteriaBuilder) -> {
            Predicate predicate = criteriaBuilder.conjunction();
            String studentId = pageDto.getId();
            String userName = pageDto.getName();
            String department = pageDto.getDepartment();
            if (StringUtils.isNotBlank(studentId)) {
                predicate.getExpressions().add(
                        criteriaBuilder.equal(root.get("studentId"), studentId)
                );
            }
            if (StringUtils.isNotBlank(userName)) {
                predicate.getExpressions().add(
                        criteriaBuilder.equal(root.get("userName"), userName)
                );
            }
            if (StringUtils.isNotBlank(department)) {
                predicate.getExpressions().add(
                        criteriaBuilder.equal(root.get("department"), department)
                );
            }
            return predicate;
        };
        Page<User> page = userRepository.findAll(specification, pageable);
        return page.getContent();
    }

    @Override
    public long getUserCount() {
        return userRepository.count();
    }


    @Override
    public User createDefaultAuthUser(AuthDto dto) {
        LOGGER.info("[createDefaultAuthUser][Register User Info][AuthDto id: {}]", dto.getStudentId());
        User user = User.builder()
                .studentId(dto.getStudentId())
                .userName(dto.getUserName())
                .department(dto.getDepartment())
                .phone(dto.getPhone())
                .seniorId(dto.getSeniorId())
                .password(InfoConstant.DEFAULT_PASSWORD)
                .roles(dto.getRoles())
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
    @Transactional
    public Response deleteByUserId(int userId, HttpHeaders headers) {
        LOGGER.info("[deleteByUserId][DELETE USER][user id: {}]", userId);
        userRepository.deleteByUserId(userId);
        return new Response(1, "Success", null);
    }

    @Override
    public Response updateUser(UserWithSeniorDto userWithSeniorDto, HttpHeaders headers) {
        // create a new User
        if (userWithSeniorDto.getUserId() == null) {
            AuthDto authDto = AuthDto.builder()
                    .studentId(userWithSeniorDto.getId())
                    .userName(userWithSeniorDto.getName())
                    .department(userWithSeniorDto.getDepartment())
                    .phone(userWithSeniorDto.getPhone())
                    .seniorId(userWithSeniorDto.getTo_id())
                    .roles(new HashSet<>(userWithSeniorDto.getRoles()))
                    .build();
            createDefaultAuthUser(authDto);
        } else {
            // update a user
            User user = userRepository.findByUserId(userWithSeniorDto.getUserId()).orElseThrow(
                    () -> new UserOperationException(
                            MessageFormat.format(InfoConstant.ID_NOT_FOUND,
                                    userWithSeniorDto.getUserId())
                    )
            );
            String studentId = userWithSeniorDto.getId();
            String userName = userWithSeniorDto.getName();
            String department = userWithSeniorDto.getDepartment();
            String phone = userWithSeniorDto.getPhone();
            String seniorId = userWithSeniorDto.getTo_id();
            Set<String> roles = new HashSet<>(userWithSeniorDto.getRoles());
            if (StringUtils.isNotBlank(studentId)) {
                user.setStudentId(studentId);
            }
            if (StringUtils.isNotBlank(userName)) {
                user.setUserName(userName);
            }
            if (StringUtils.isNotBlank(department)) {
                user.setDepartment(department);
            }
            if (StringUtils.isNotBlank(phone)) {
                user.setPhone(phone);
            }
            if (StringUtils.isNotBlank(seniorId)) {
                user.setSeniorId(seniorId);
            }
            if (!roles.isEmpty()) {
                user.setRoles(roles);
            }
            userRepository.save(user);
        }
        return new Response(1, "Success", null);
    }

    @Override
    public RoleToMenu getRoleToMenuByRole(String role) {
        return roleRepository.findByRole(role);
    }

    @Override
    public List<RoleToMenu> getAllRoles() {
        return (List<RoleToMenu>) roleRepository.findAll();
    }

    private void checkUserCreateInfo(User user) {
        LOGGER.info("[checkUserCreateInfo][Check user create info][userId: {}, id: {}]", user.getUserId(),
                user.getUsername());
        List<String> infos = new ArrayList<>();

        if (null == user.getUsername() || "".equals(user.getUsername())) {
            infos.add(MessageFormat.format(InfoConstant.PROPERTIES_CANNOT_BE_EMPTY, InfoConstant.USERNAME));
        }

        /* unused
        int passwordMaxLength = 6;
        if (null == user.getPassword()) {
            infos.add(MessageFormat.format(InfoConstant.PROPERTIES_CANNOT_BE_EMPTY, InfoConstant.PASSWORD));
        } else if (user.getPassword().length() < passwordMaxLength) {
            infos.add(MessageFormat.format(InfoConstant.PASSWORD_LEAST_CHAR, 6));
        }
        */

        if (null == user.getRoles() || user.getRoles().isEmpty()) {
            infos.add(MessageFormat.format(InfoConstant.PROPERTIES_CANNOT_BE_EMPTY, InfoConstant.ROLES));
        }

        if (!infos.isEmpty()) {
            LOGGER.warn(infos.toString());
            throw new UserOperationException(infos.toString());
        }
    }
}
