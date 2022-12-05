package auth.repository;

import auth.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByUserName(String userName);

    void deleteByUserID(String userId);
}
