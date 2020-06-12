package merit.capstone.bankApp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import merit.capstone.bankApp.models.BankAccount;



public interface BankAccountRepository extends JpaRepository <BankAccount, Long>{
	
	List<BankAccount> findByUserId(long id);

}