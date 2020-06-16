package merit.capstone.bankApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import merit.capstone.bankApp.models.CheckingAccount;
import merit.capstone.bankApp.models.Transaction;

@SpringBootApplication
public class BankAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankAppApplication.class, args);

		CheckingAccount c = new CheckingAccount();
		c.setBalance(100);
		Transaction t = new Transaction();
		t.setTargeAccount(c);
		t.setAmount(100);
		c.processTransaction(t);
		
	}

}
