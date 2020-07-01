package merit.capstone.bankApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import merit.capstone.bankApp.models.BankUser;

/*
 * Repository interface to be used by spring as it autowires access to the database
 */
public interface BankUserRepository extends JpaRepository <BankUser, Long>{
	
	BankUser findByUsername(String username);
	
	BankUser findById(long id);

}
