package merit.capstone.bankApp;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.UserRequestPostProcessor;

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
import merit.capstone.bankApp.models.DBACheckingAccount;
import merit.capstone.bankApp.models.Feedback;
import merit.capstone.bankApp.models.RegularIRA;
import merit.capstone.bankApp.models.RolloverIRA;
import merit.capstone.bankApp.models.RothIRA;
import merit.capstone.bankApp.models.SavingsAccount;
import merit.capstone.bankApp.models.Transaction;
import merit.capstone.bankApp.repos.BankAccountRepository;
import merit.capstone.bankApp.repos.BankUserRepository;
import merit.capstone.bankApp.repos.FeedbackRepository;
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
	@Autowired
	private FeedbackRepository feedbackRepository;

	@SuppressWarnings("deprecation")

	@Test
	public void canUpdateContactInfo() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");
		user.setAddress("1234 honey grove");
		bankUserRepository.save(user);
		
		BankUser u = bankUserRepository.findById(user.getId());
		u.setAddress("444 W Holly");
		u.updateContactInfo(u);
		bankUserRepository.save(u);
		
		assertEquals("444 W Holly", bankUserRepository.findById(user.getId()).getAddress());
	}
	
	@Test
	public void getBalanceByTypeChecking() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		CheckingAccount c = new CheckingAccount();
		c.setBalance(500);

		try {
			user.addBankAccount(c);
		} catch (MaxAccountsReachedException e) {
			e.printStackTrace();
		}

		assertEquals(500, user.getBalanceByType(c));

	}
	
	@Test
	public void getBalanceByTypeSavings() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		SavingsAccount s = new SavingsAccount();
		s.setBalance(500);

		try {
			user.addBankAccount(s);
		} catch (MaxAccountsReachedException e) {
			e.printStackTrace();
		}

		assertEquals(500, user.getBalanceByType(s));

	}
	
	@Test
	public void getBalanceByTypeDBAChecking() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		DBACheckingAccount dba = new DBACheckingAccount();
		dba.setBalance(500);

		try {
			user.addBankAccount(dba);
		} catch (MaxAccountsReachedException e) {
			e.printStackTrace();
		}

		assertEquals(500, user.getBalanceByType(dba));

	}
	
	@Test
	public void getBalanceByTypeROTHIra() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		RothIRA r = new RothIRA();
		r.setBalance(500);

		try {
			user.addBankAccount(r);
		} catch (MaxAccountsReachedException e) {
			e.printStackTrace();
		}

		assertEquals(500, user.getBalanceByType(r));

	}
	
	public void getBalanceByTypeRegIRA() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		RegularIRA r = new RegularIRA();
		r.setBalance(500);

		try {
			user.addBankAccount(r);
		} catch (MaxAccountsReachedException e) {
			e.printStackTrace();
		}

		assertEquals(500, user.getBalanceByType(r));

	}
	
	

	@Test
	public void getallAvailableBalance() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		CheckingAccount c = new CheckingAccount();
		c.setBalance(100);

		SavingsAccount s = new SavingsAccount();
		s.setBalance(100);

		DBACheckingAccount dba = new DBACheckingAccount();
		dba.setBalance(100);

		RothIRA roth = new RothIRA();
		roth.setBalance(100);

		RegularIRA reg = new RegularIRA();
		reg.setBalance(100);

		RolloverIRA roll = new RolloverIRA();
		roll.setBalance(100);

		try {
			user.addBankAccount(c);
		} catch (MaxAccountsReachedException e) {
			e.printStackTrace();
		}
		try {
			user.addBankAccount(s);
		} catch (MaxAccountsReachedException e) {
			e.printStackTrace();
		}
		try {
			user.addBankAccount(dba);
		} catch (MaxAccountsReachedException e1) {
			e1.printStackTrace();
		}
		try {
			user.addBankAccount(roth);
		} catch (MaxAccountsReachedException e1) {
			e1.printStackTrace();
		}
		try {
			user.addBankAccount(reg);
		} catch (MaxAccountsReachedException e1) {
			e1.printStackTrace();
		}
		try {
			user.addBankAccount(roll);
		} catch (MaxAccountsReachedException e) {
			e.printStackTrace();
		}

		assertEquals(550, user.getAllAvailableBalance(), 0);

	}

	@Test
	public void futureValue() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		CheckingAccount c = new CheckingAccount();
		c.setBalance(1000);

		assertEquals(1000.5001000100006, c.futureValue(5));
	}

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
	public void getSavingsAccounts() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		SavingsAccount s = new SavingsAccount();

		try {
			user.addBankAccount(s);
		} catch (MaxAccountsReachedException e) {
		}

		assertTrue(user.getNumberOfAccountsByType(s) == 1);
	}

	@Test
	public void getCheckingAccounts() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		CheckingAccount c = new CheckingAccount();

		try {
			user.addBankAccount(c);
		} catch (MaxAccountsReachedException e) {
		}

		assertTrue(user.getNumberOfAccountsByType(c) == 1);

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
			// since we use the catch to stop deposits on CD closings, exceptions here are
			// fine
			// fail();
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

		assertTrue(user.getBankAccounts().size() >= 1);
	}

	@Test
	public void addSavingsAccount() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		SavingsAccount sa = new SavingsAccount();
		try {
			user.addBankAccount(sa);
		} catch (MaxAccountsReachedException e) {
			fail();
		}

		assertTrue(user.getBankAccounts().size() >= 1);
	}

	@Test
	public void addDBAAccount() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		DBACheckingAccount dba = new DBACheckingAccount();
		try {
			user.addBankAccount(dba);
		} catch (MaxAccountsReachedException e) {
			fail();
		}

		assertTrue(user.getBankAccounts().size() >= 1);

	}

	@Test
	public void addCDAccount() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		CDAccount cda = new CDAccount();
		try {
			user.addBankAccount(cda);
		} catch (MaxAccountsReachedException e) {
			fail();
		}

		assertTrue(user.getBankAccounts().size() >= 1);
	}

	@Test
	public void closeAccountTransfer() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		SavingsAccount a = new SavingsAccount();
		a.setUserId(user.getId());
		try {
			user.addBankAccount(a);
		} catch (Exception e) {
			fail();
		}

		RegularIRA ra = new RegularIRA();
		ra.setBalance(500);

		assertEquals(500, ra.getBalance(), 0);

		double val = user.getSingleSavingsAccount().getBalance();

		try {
			ra.closeAccount(user);

		} catch (Exception e) {
			fail();
		}

		assertEquals(val + (500 / 1.2), user.getSingleSavingsAccount().getBalance(), 1);
	}

	@Test
	public void closeCheckingTransferToSavings() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		SavingsAccount sa = new SavingsAccount();
		try {
			user.addBankAccount(sa);
		} catch (Exception e) {
		}

		CheckingAccount c = new CheckingAccount();
		c.setBalance(500);

		assertEquals(500, c.getBalance(), 0);

		try {
			c.closeAccount(user);
		} catch (Exception e) {
		}

		assertEquals(500, user.getSingleSavingsAccount().getBalance(), 0);
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
	public void depositIntoSavings() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		SavingsAccount sa = new SavingsAccount();
		try {
			sa.deposit(500);
		} catch (Exception e) {

		}
		assertEquals(500, sa.getBalance(), 0);
	}

	@Test
	public void depositNegativeIntoSavings() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		SavingsAccount sa = new SavingsAccount();
		try {
			sa.deposit(-500);
			fail();
		} catch (Exception e) {

		}
		assertEquals(0, sa.getBalance(), 0);
	}

	@Test
	public void cantDepositNegativeIntoChecking() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		CheckingAccount ca = new CheckingAccount();
		try {
			ca.deposit(-100);
		} catch (Exception e) {

		}

		assertEquals(0, ca.getBalance(), 0);
	}

	@Test
	public void transferToChecking() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		SavingsAccount sa = new SavingsAccount();
		sa.setBalance(500);

		CheckingAccount ch = new CheckingAccount();
		ch.setBalance(100);

		Transaction t = new Transaction();
		t.setSourceAccount(sa.getAccountNumber());
		t.setTargetAccount(ch.getAccountNumber());
		t.setAmount(100);

		sa.processTransaction(t, sa, ch);

		assertEquals(200, ch.getBalance(), 0);
		assertEquals(400, sa.getBalance(), 0);

	}

	@Test
	public void transferToSavings() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		CheckingAccount c = new CheckingAccount();
		c.setBalance(500);

		SavingsAccount s = new SavingsAccount();
		s.setBalance(100);

		Transaction t = new Transaction();
		t.setSourceAccount(c.getAccountNumber());
		t.setTargetAccount(s.getAccountNumber());
		t.setAmount(200);

		c.processTransaction(t, c, s);

		assertEquals(300, s.getBalance(), 0);
		assertEquals(300, c.getBalance(), 0);
	}

	@Test
	public void cantTransferNegativeAmount() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		CheckingAccount c = new CheckingAccount();
		c.setBalance(200);

		DBACheckingAccount db = new DBACheckingAccount();
		db.setBalance(100);

		Transaction t = new Transaction();
		t.setSourceAccount(c.getAccountNumber());
		t.setTargetAccount(db.getAccountNumber());
		t.setAmount(-100);

		c.processTransaction(t, c, db);

		assertEquals(100, db.getBalance(), 0);
		assertEquals(200, c.getBalance(), 0);
	}

	@Test
	public void transferToDBA() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		CheckingAccount c = new CheckingAccount();
		c.setBalance(400);

		DBACheckingAccount db = new DBACheckingAccount();
		db.setBalance(100);

		Transaction t = new Transaction();
		t.setSourceAccount(c.getAccountNumber());
		t.setTargetAccount(db.getAccountNumber());
		t.setAmount(100);

		c.processTransaction(t, c, db);

		assertEquals(200, db.getBalance(), 0);
		assertEquals(300, c.getBalance(), 0);
	}

	@Test
	public void overdrawChecking() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		CheckingAccount a = new CheckingAccount();
		a.setBalance(100);

		Transaction t = new Transaction();
		t.setSourceAccount(a.getAccountNumber());
		t.setTargetAccount(a.getAccountNumber());
		t.setAmount(-200);

		a.processTransaction(t, a, a);

		assertEquals(0, a.getBalance(), 100);
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
	public void addFeedback() {
		Feedback fb = new Feedback();
		fb.setMessage("hello");

		assertThat(fb).isNotNull();

	}

	@Test
	public void getFeedbackFromDB() {

		Feedback fb = new Feedback();
		fb.setMessage("You're the best");
		fb.setFirstname("Ted");

		feedbackRepository.save(fb);
		long msgId = fb.getId();

		assertEquals("You're the best", feedbackRepository.findById(msgId).getMessage());
	}

	@Test
	public void getFeedBack() {
		Feedback fb = new Feedback();
		fb.setMessage("Hello Friend");

		assertNotNull(fb, "Hello Friend");
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
	public void cantWithdrawCDAccount() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");

		CDAccount cd = new CDAccount();
		cd.setBalance(500);

		try {
			cd.withdraw(200);
		} catch (TransactionNotAllowedException e) {

		}

		assertEquals(500, cd.getBalance(), 0);
	}

	@Test
	public void checkOverrideIRAMultipleTrans() {
		BankUser user = new BankUser();
		user.setFirstName("ted");
		user.setLastName("smith");
		user.setSsn("123123123");
		bankUserRepository.save(user);

		SavingsAccount a = new SavingsAccount();
		a.setUserId(user.getId());
		try {
			user.addBankAccount(a);
		} catch (Exception e) {
			fail();
		}
		// bankAccountRepository.save(a);

		RegularIRA ra = new RegularIRA();
		ra.setBalance(500);
		bankAccountRepository.save(ra);

		Transaction t = new Transaction();
		t.setSourceAccount(ra.getAccountNumber());

		t.setTargetAccount(user.getSingleSavingsAccount().getAccountNumber());
		t.setAmount(100);

		try {
			ra.processTransaction(t, ra, user.getSingleSavingsAccount());

		} catch (Exception e) {

		}

		assertEquals(380, ra.getBalance(), 0);
		assertEquals(100, user.getSingleSavingsAccount().getBalance());

		user.setIsActive(false);
		ra.setIsActive(false);

		bankUserRepository.save(user);
		bankAccountRepository.save(ra);

		// bankUserRepository.delete(user);
		// bankAccountRepository.delete(ra);

	}

	@Test
	public void doesAdminControllerInjectSuccessfully() {
		assertThat(adminController).isNotNull();
	}

	@Test
	public void doesBankAccountRepositoryInjectSuccessfully() {
		assertThat(bankAccountRepository).isNotNull();
	}

	@Test
	public void doesBankUserRepositoryInjectSuccessfully() {
		assertThat(bankUserRepository).isNotNull();
	}

	@Test
	public void doesTransactionRepositoryInjectSuccessfully() {
		assertThat(transactionRepository).isNotNull();
	}

}
