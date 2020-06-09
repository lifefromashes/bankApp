package merit.capstone.bankApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import merit.capstone.bankApp.models.User;


public interface UserRepository extends JpaRepository <User, Long>{
	
	User findByUsername(String username);

}
