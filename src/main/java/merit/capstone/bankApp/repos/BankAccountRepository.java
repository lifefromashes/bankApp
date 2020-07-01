package merit.capstone.bankApp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import merit.capstone.bankApp.models.BankAccount;


/*
 * Repository interface to be used by spring as it autowires access to the database
 */
public interface BankAccountRepository extends JpaRepository <BankAccount, Long>{
	
	BankAccount findById(long id);
	
	List<BankAccount> findByUserId(long id);
	

}
