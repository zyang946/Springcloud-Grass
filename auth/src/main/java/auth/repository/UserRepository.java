package auth.repository;

import auth.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findByStudentId(String studentId);

    void deleteByStudentId(String studentId);
}
