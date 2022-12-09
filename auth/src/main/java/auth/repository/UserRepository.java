package auth.repository;

import auth.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String>,
        JpaSpecificationExecutor<User> {
    Optional<User> findByStudentId(String studentId);
    Optional<User> findByUserId(int userId);
    void deleteByUserId(int userId);
}
