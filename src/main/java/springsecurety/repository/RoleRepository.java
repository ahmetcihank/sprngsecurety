package springsecurety.repository;

/**
 * Created by SOFTWARE02 on 19.08.2018.
 */
import springsecurety.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository("roleRepository")
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
}
