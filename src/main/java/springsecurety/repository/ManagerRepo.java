package springsecurety.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springsecurety.model.Managers;

/**
 * Created by SOFTWARE02 on 01.09.2018.
 */
public interface ManagerRepo  extends JpaRepository<Managers,Integer>{
}
