package merit.capstone.bankApp;

//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import merit.capstone.bankApp.models.BankAccount;

//import org.junit.Test;


import merit.capstone.bankApp.models.BankUser;
import merit.capstone.bankApp.models.CheckingAccount;
import merit.capstone.bankApp.models.RegularIRA;
import merit.capstone.bankApp.models.Transaction;

public class BankAppApplicationTest {

	
	
	@SuppressWarnings("deprecation")
	@Test
	public void testProcessTransaction() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");
		
		CheckingAccount a = new CheckingAccount();
		a.setBalance(0);
		
		Transaction t = new Transaction();
		t.setSourceAccount(a.getAccountNumber());
		t.setTargeAccount(a.getAccountNumber());
		t.setAmount(100);
		
		a.processTransaction(t);
		
		assertEquals(100, a.getBalance(), 0);
	}

	@Test
	public void testCloseAccount() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		RegularIRA ra = new RegularIRA();
		ra.setBalance(500);

		assertEquals(500, ra.getBalance(), 0);

		try{
			ra.closeAccount(user);

		} catch (Exception e) {
			fail();
		}
		
		assertEquals(false, ra.isActive());
	}

}
