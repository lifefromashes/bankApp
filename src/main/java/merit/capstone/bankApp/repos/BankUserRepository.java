package merit.capstone.bankApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import merit.capstone.bankApp.models.BankUser;


public interface BankUserRepository extends JpaRepository <BankUser, Long>{
	
	BankUser findByUsername(String username);
	
	BankUser findById(long id);

}
