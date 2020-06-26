package merit.capstone.bankApp.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import merit.capstone.bankApp.exceptions.ExceedsCombinedBalanceLimitException;
import merit.capstone.bankApp.exceptions.MaxAccountsReachedException;
import merit.capstone.bankApp.exceptions.NotFoundException;
import merit.capstone.bankApp.models.BankAccount;
import merit.capstone.bankApp.models.BankUser;
import merit.capstone.bankApp.models.CDAccount;
import merit.capstone.bankApp.models.CheckingAccount;
import merit.capstone.bankApp.models.DBACheckingAccount;
import merit.capstone.bankApp.models.RegularIRA;
import merit.capstone.bankApp.models.RolloverIRA;
import merit.capstone.bankApp.models.RothIRA;
import merit.capstone.bankApp.models.SavingsAccount;
import merit.capstone.bankApp.models.Transaction;
import merit.capstone.bankApp.repos.BankAccountRepository;
import merit.capstone.bankApp.repos.BankUserRepository;
import merit.capstone.bankApp.repos.TransactionRepository;
import merit.capstone.bankApp.security.JwtUtil;
import merit.capstone.bankApp.security.MyUserDetailsService;

@RestController
@CrossOrigin
public class UserController {
	
private Logger log = LoggerFactory.getLogger(this.getClass() );
	
	@Autowired private BankUserRepository bankUserRepository;
	@Autowired private TransactionRepository transactionRepository;
	@Autowired private BankAccountRepository bankAccountRepository;
	@Autowired private JwtUtil jwtUtil;
	@Autowired private MyUserDetailsService userDetailsService;

	private MyUserDetailsService myUserDetailsService;
	
	
	@CrossOrigin
	@GetMapping(value = "/User")
	public BankUser getMyAccount(@RequestHeader("Authorization") String auth) throws NotFoundException {
		BankUser user = findUser(auth);
		return user;
	}
	
	
	
	

	@CrossOrigin
	@GetMapping("Me/BankAccount")
	public List<BankAccount> getCheckingAccounts(@RequestHeader("Authorization") String auth) 
				throws NotFoundException {
		
		BankUser user = findUser(auth);
		List<BankAccount> a = bankAccountRepository.findByUserId(user.getId());
		
		for(BankAccount b : a) {
			if(!b.isActive()) {
				a.remove(b);
			}
		}
		
		return a;
	}
	
	
	@GetMapping("Me/{t}")
	public List<BankAccount> getAnyBankAccounts(@RequestHeader("Authorization") String auth, @PathVariable(name = "t") String t) 
			throws NotFoundException, IllegalArgumentException {
		
		BankUser user = findUser(auth);
		
		switch(t) {
			case "CDAccount": return getBankAccounts(user, new CDAccount());
			case "CheckingAccount": return getBankAccounts(user, new CheckingAccount());
			case "DBACheckingAccount": return getBankAccounts(user, new DBACheckingAccount());
			case "RegularIRA": return getBankAccounts(user, new RegularIRA());
			case "RolloverIRA": return getBankAccounts(user, new RolloverIRA());
			case "RothIRA": return getBankAccounts(user, new RothIRA());
			case "SavingsAccount": return getBankAccounts(user, new SavingsAccount());
			default: throw new IllegalArgumentException();
		}
	}
	
	/*
	 * accepts a temp child BankAccount object and returns a list of the accounts matching that type
	 */
	public List<BankAccount> getBankAccounts(BankUser user, BankAccount t) throws NotFoundException { 
		List<BankAccount> a = bankAccountRepository.findByUserId(user.getId());
		List<BankAccount> matching = new ArrayList<>();
		for(BankAccount b : a) {
			if(b.getClass() == t.getClass() && b.isActive()) {
				matching.add(b);
			}
		}
		ControllerUtil.enforceFound(matching);
		return matching;
	}

	
	
	
	
	
	
	@PostMapping("User/CDAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addCDAccount(@RequestHeader("Authorization") String auth, @RequestBody @Valid CDAccount a)
				throws NotFoundException, ExceedsCombinedBalanceLimitException, MaxAccountsReachedException {
		BankUser user = findUser(auth);
		ControllerUtil.enforceFound(user);
		return createAccount(user, a);
	}
	
	@PostMapping("User/CheckingAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addCheckingAccount(@RequestHeader("Authorization") String auth, @RequestBody @Valid CheckingAccount a)
				throws NotFoundException, ExceedsCombinedBalanceLimitException, MaxAccountsReachedException {
		BankUser user = findUser(auth);
		ControllerUtil.enforceFound(user);
		return createAccount(user, a);
	}
	
	@PostMapping("User/DBACheckingAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addDBACheckingAccount(@RequestHeader("Authorization") String auth, @RequestBody @Valid DBACheckingAccount a)
				throws NotFoundException, ExceedsCombinedBalanceLimitException, MaxAccountsReachedException {
		BankUser user = findUser(auth);
		ControllerUtil.enforceFound(user);
		return createAccount(user, a);
	}
	
	@PostMapping("User/RegularIRA")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addRegularIRA(@RequestHeader("Authorization") String auth, @RequestBody @Valid RegularIRA a)
				throws NotFoundException, ExceedsCombinedBalanceLimitException, MaxAccountsReachedException {
		BankUser user = findUser(auth);
		ControllerUtil.enforceFound(user);
		return createAccount(user, a);
	}
	
	@PostMapping("User/RolloverIRA")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addRolloverIRA(@RequestHeader("Authorization") String auth, @RequestBody @Valid RolloverIRA a)
				throws NotFoundException, ExceedsCombinedBalanceLimitException, MaxAccountsReachedException {
		BankUser user = findUser(auth);
		ControllerUtil.enforceFound(user);
		return createAccount(user, a);
	}
	
	@PostMapping("User/RothIRA")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addRothIRA(@RequestHeader("Authorization") String auth, @RequestBody @Valid RothIRA a)
				throws NotFoundException, ExceedsCombinedBalanceLimitException, MaxAccountsReachedException {
		BankUser user = findUser(auth);
		ControllerUtil.enforceFound(user);
		return createAccount(user, a);
	}
	
	@PostMapping("User/SavingsAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addSavingsAccount(@RequestHeader("Authorization") String auth, @RequestBody @Valid SavingsAccount a)
				throws NotFoundException, ExceedsCombinedBalanceLimitException, MaxAccountsReachedException {
		BankUser user = findUser(auth);
		ControllerUtil.enforceFound(user);
		return createAccount(user, a);
	}
	
	
	private BankAccount createAccount(BankUser user, BankAccount a) throws MaxAccountsReachedException {
		a.setUserId(user.getId());
		user.addBankAccount(a);
		bankAccountRepository.save(a);
		
		Transaction t = new Transaction();
		t.setSourceAccount(a.getAccountNumber());
		t.setTargetAccount(a.getAccountNumber());
		t.setTransactionMemo("Account Created");
		t.setBalanceAfterTransaction(a.getBalance());
		t.setTransactionSuccess(true);
		a.addTransaction(t);

		transactionRepository.save(t);
		bankAccountRepository.save(a);
		
		return a;
	}
	
	
	
	private BankUser findUser(String auth) throws NotFoundException {
		String jwt = auth.substring(7);
		String username = jwtUtil.extractUsername(jwt);
		BankUser user = bankUserRepository.findByUsername(username);
		ControllerUtil.enforceFound(user);
		return user;
	}
	
	
	@CrossOrigin
	@PostMapping(value = "/User/Transaction")
	public Transaction inputTransaction(@RequestHeader("Authorization") String auth, @RequestBody @Valid Transaction transaction) throws NotFoundException {
		
		BankUser user = findUser(auth);
		ControllerUtil.enforceFound(user);
		
		BankAccount a = bankAccountRepository.findById( transaction.getSourceAccount() );
		ControllerUtil.enforceFound(a);
		
		BankAccount bat = bankAccountRepository.findById(transaction.getTargetAccount());
		ControllerUtil.enforceFound(bat);
		
		// prevent anyone without the correct token from accessing other's accounts by editing the origin url
		if(a.getUserId() != user.getId()) { throw new NotFoundException(); }
		
		
		a.processTransaction(transaction, a, bat);
		
		bankAccountRepository.save(a);
        transactionRepository.save(transaction);
		return transaction;
	}
	
	@CrossOrigin
	@GetMapping(value = "/User/Transaction/{id}") // id is the BankAccount id
	public BankAccount accountHistory(@RequestHeader("Authorization") String auth, @PathVariable(name = "id") long id) throws NotFoundException {
		
		BankUser user = findUser(auth);
		ControllerUtil.enforceFound(user);
		
		BankAccount a = bankAccountRepository.findById(id);
		ControllerUtil.enforceFound(a);
		
		// prevent anyone without the correct token from accessing other's accounts by editing the origin url
		if(a.getUserId() != user.getId()) { throw new NotFoundException(); }
		
        
		//return a.getTransactions();
		return a;
	}
	
	/*
	 * This method accepts a dummy bank account object and returns a future value
	 * because it doesn't use "real" accounts, no security is appropriate 
	 * 
	 *   using CD account because it doesn't have a default interest rate, but this method handles all types of accounts 
	 */
	@CrossOrigin
	@PostMapping(value = "/FutureValue")
	public double futureValue(@RequestBody CDAccount a) throws NotFoundException {
		
		double v = a.futureValue(a.getTerm());
		v = Math.floor(v / 100);
		v *= 100;
		return a.getBalance() + v;
	}
	
	@CrossOrigin
	@GetMapping(value = "/quickFunds")
	public double quickFunds(@RequestHeader("Authorization") String auth) throws NotFoundException {
		BankUser user = findUser(auth);
		
		
		return user.getAllAvailableBalance();
	}
	
	
	
	
	

}

