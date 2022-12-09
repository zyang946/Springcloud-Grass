package auth.repository;

import auth.entity.RoleToMenu;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<RoleToMenu, String> {
    RoleToMenu findByRole(String role);
}
