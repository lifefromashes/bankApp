package merit.capstone.bankApp;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import merit.capstone.bankApp.controllers.AdminController;
import merit.capstone.bankApp.exceptions.ExceedsAvailableBalanceException;
import merit.capstone.bankApp.exceptions.MaxAccountsReachedException;
import merit.capstone.bankApp.exceptions.NegativeAmountException;
import merit.capstone.bankApp.exceptions.TransactionNotAllowedException;
import merit.capstone.bankApp.models.BankAccount;

//import org.junit.Test;

import merit.capstone.bankApp.models.BankUser;
import merit.capstone.bankApp.models.CDAccount;
import merit.capstone.bankApp.models.CheckingAccount;
import merit.capstone.bankApp.models.RegularIRA;
import merit.capstone.bankApp.models.Transaction;
import merit.capstone.bankApp.repos.BankAccountRepository;
import merit.capstone.bankApp.repos.BankUserRepository;
import merit.capstone.bankApp.repos.TransactionRepository;

@SpringBootTest
public class BankAppApplicationTest {

	@Autowired
	private AdminController adminController;
	@Autowired
	private BankAccountRepository bankAccountRepository;
	@Autowired
	private BankUserRepository bankUserRepository;
	@Autowired
	private TransactionRepository transactionRepository;

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
		t.setTargetAccount(a.getAccountNumber());
		t.setAmount(100);

		a.processTransaction(t, a, a);

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

		try {
			ra.closeAccount(user);

		} catch (Exception e) {
			fail();
		}

		assertEquals(false, ra.isActive());
	}

	@Test
	public void addBankAccount() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		CheckingAccount ca = new CheckingAccount();
		try {
			user.addBankAccount(ca);
		} catch (MaxAccountsReachedException e) {
			fail();
		}

		assertTrue(user.getBankAccounts().size() >= 2);
	}

	@Test
	public void closeAccountTransfer() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		RegularIRA ra = new RegularIRA();
		ra.setBalance(500);

		assertEquals(500, ra.getBalance(), 0);

		double val = user.getSingleSavingsAccount().getBalance();

		try {
			ra.closeAccount(user);

		} catch (Exception e) {
			fail();
		}

		assertEquals(val + (500 * .8), user.getSingleSavingsAccount().getBalance(), 0);
	}

	@Test
	public void withdraw() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		CheckingAccount a = new CheckingAccount();
		a.setBalance(100);

		Transaction t = new Transaction();
		t.setSourceAccount(a.getAccountNumber());
		t.setTargetAccount(a.getAccountNumber());
		t.setAmount(-100);

		a.processTransaction(t, a, a);

		assertEquals(0, a.getBalance(), 0);
	}

	@Test
	public void withdrawIRA() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		RegularIRA ra = new RegularIRA();
		ra.setBalance(500);

		assertEquals(500, ra.getBalance(), 0);

		try {
			ra.withdraw(100);
		} catch (ExceedsAvailableBalanceException | NegativeAmountException e) {
			fail();
		}

		assertEquals(380, ra.getBalance(), 0);
	}

	@Test
	public void cantDepositCDAccount() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		CDAccount cd = new CDAccount();

		try {
			cd.deposit(200);
			fail();
		} catch (TransactionNotAllowedException e) {

		}

		assertEquals(0, cd.getBalance(), 0);

	}
	
	@Test
	public void checkOverrideIRAMultipleTrans() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");
		bankUserRepository.save(user);
		
		
		RegularIRA ra = new RegularIRA();
		ra.setBalance(500);
		bankAccountRepository.save(ra);
		
		Transaction t = new Transaction();
		t.setSourceAccount(ra.getAccountNumber());
		t.setTargetAccount(user.getSingleSavingsAccount().getAccountNumber());
		t.setAmount(100);
		
		try {
			ra.processTransaction(t, ra, user.getSingleSavingsAccount() );
			
		}catch (Exception e) {
			
		}
		
		assertEquals(380, ra.getBalance(), 0);
		assertEquals(100, user.getSingleSavingsAccount().getBalance());
		
		bankUserRepository.delete(user);
		bankAccountRepository.delete(ra);
	}

	@Test
	public void doesControllerCallStuff() {
		
		assertThat(adminController).isNotNull();
		
	}

}
