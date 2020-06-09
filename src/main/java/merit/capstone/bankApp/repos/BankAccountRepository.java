package merit.capstone.bankApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import merit.capstone.bankApp.models.BankAccount;



public interface BankAccountRepository extends JpaRepository <BankAccount, Long>{
	
	BankAccount findByUsername(String username);

}
