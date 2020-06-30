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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


import merit.capstone.bankApp.exceptions.ExceedsCombinedBalanceLimitException;
import merit.capstone.bankApp.exceptions.MaxAccountsReachedException;
import merit.capstone.bankApp.exceptions.NotFoundException;
import merit.capstone.bankApp.models.BankAccount;
import merit.capstone.bankApp.models.CheckingAccount;
import merit.capstone.bankApp.models.DBACheckingAccount;
import merit.capstone.bankApp.models.RegularIRA;
import merit.capstone.bankApp.models.RolloverIRA;
import merit.capstone.bankApp.models.RothIRA;
import merit.capstone.bankApp.models.SavingsAccount;
import merit.capstone.bankApp.models.Transaction;
import merit.capstone.bankApp.models.BankUser;
import merit.capstone.bankApp.models.CDAccount;
import merit.capstone.bankApp.models.CDOffering;
import merit.capstone.bankApp.repos.BankAccountRepository;
import merit.capstone.bankApp.repos.BankUserRepository;
import merit.capstone.bankApp.repos.CDOfferingRepository;
import merit.capstone.bankApp.repos.TransactionRepository;

/**
 * 
 * This controller is handles APIs for basic bank worker functions like creating new accounts
 * or viewing information by the user's ID
 * 
 * It is critical that these functions are accessible only to employees and not account holders
 * 
 *
 */

@CrossOrigin
@RestController
public class AdminController {
	private Logger log = LoggerFactory.getLogger(this.getClass() );
	
	@Autowired private BankUserRepository bankUserRepository;
	@Autowired private BankAccountRepository bankAccountRepository;
	@Autowired private CDOfferingRepository cdOfferingRepository;
	@Autowired private TransactionRepository transactionRepository;
	
	
	
	@PostMapping("Admin/CDOfferings")
	@ResponseStatus(HttpStatus.CREATED)
	public CDOffering createCDOffering(@RequestBody CDOffering a) {
		cdOfferingRepository.save(a);
		log.info("Created new CD Offering: " + a.getInterestRate() + " for " + a.getTerm());
		return a;
	}
	
	@GetMapping("CDOfferings")
	public List<CDOffering> getCDOfferings() throws NotFoundException {
		return cdOfferingRepository.findAll();
	}
	
	@GetMapping("CDOffering/{id}")
	public CDOffering getCDOffering(@PathVariable(name = "id") long id) throws NotFoundException {
		return cdOfferingRepository.findById(id);
	}
		
	
	
	
	
	@PostMapping("Admin/{id}/CDAccount/{cdo}")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addCDAccount(@PathVariable(name = "id") long id, @PathVariable(name = "cdo") long cdo, @RequestBody @Valid CDAccount a)
				throws NotFoundException, ExceedsCombinedBalanceLimitException, MaxAccountsReachedException {
		return createAccount(id, a, cdo);
	}
	@PostMapping("Admin/{id}/CheckingAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addCheckingAccount(@PathVariable(name = "id") long id, @RequestBody @Valid CheckingAccount a)
				throws NotFoundException, ExceedsCombinedBalanceLimitException, MaxAccountsReachedException {
		return createAccount(id, a);
	}
	@PostMapping("Admin/{id}/DBACheckingAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addDBACheckingAccount(@PathVariable(name = "id") long id, @RequestBody @Valid DBACheckingAccount a)
				throws NotFoundException, ExceedsCombinedBalanceLimitException, MaxAccountsReachedException {
		return createAccount(id, a);
	}
	@PostMapping("Admin/{id}/RegularIRA")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addRegularIRA(@PathVariable(name = "id") long id, @RequestBody @Valid RegularIRA a)
				throws NotFoundException, ExceedsCombinedBalanceLimitException, MaxAccountsReachedException {
		return createAccount(id, a);
	}
	@PostMapping("Admin/{id}/RolloverIRA")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addRolloverIRA(@PathVariable(name = "id") long id, @RequestBody @Valid RolloverIRA a)
				throws NotFoundException, ExceedsCombinedBalanceLimitException, MaxAccountsReachedException {
		return createAccount(id, a);
	}
	@PostMapping("Admin/{id}/RothIRA")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addRothIRA(@PathVariable(name = "id") long id, @RequestBody @Valid RothIRA a)
				throws NotFoundException, ExceedsCombinedBalanceLimitException, MaxAccountsReachedException {
		return createAccount(id, a);
	}
	@PostMapping("Admin/{id}/SavingsAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addCheckingAccount(@PathVariable(name = "id") long id, @RequestBody @Valid SavingsAccount a)
				throws NotFoundException, ExceedsCombinedBalanceLimitException, MaxAccountsReachedException {
		return createAccount(id, a);
	}
	

	
	@GetMapping("Admin/{id}/{t}")
	public List<BankAccount> getAnyBankAccounts(@PathVariable(name = "id") long id, @PathVariable(name = "t") String t) 
			throws NotFoundException, IllegalArgumentException {
		
		switch(t) {
			case "CDAccount": return getBankAccounts(id, new CDAccount());
			case "CheckingAccount": return getBankAccounts(id, new CheckingAccount());
			case "DBACheckingAccount": return getBankAccounts(id, new DBACheckingAccount());
			case "RegularIRA": return getBankAccounts(id, new RegularIRA());
			case "RolloverIRA": return getBankAccounts(id, new RolloverIRA());
			case "RothIRA": return getBankAccounts(id, new RothIRA());
			case "SavingsAccount": return getBankAccounts(id, new SavingsAccount());
			default: throw new IllegalArgumentException();
		}
		
	}
	
	
	
	
	
	
	

	
	private BankAccount createAccount(long id, BankAccount a) throws NotFoundException, MaxAccountsReachedException {
		BankUser user = bankUserRepository.findById(id);
		ControllerUtil.enforceFound(user);
		a.setUserId(id);
		user.addBankAccount(a);
		bankAccountRepository.save(a);
		
		log.info("Created new " + a.getAccountName() + " for user # " + user.getId());
		
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
	
	private BankAccount createAccount(long id, BankAccount a, long cdo) throws NotFoundException, MaxAccountsReachedException {
		CDOffering c = cdOfferingRepository.findById(cdo);
		ControllerUtil.enforceFound(c);
		
		
		BankUser user = bankUserRepository.findById(id);
		ControllerUtil.enforceFound(user);
		a.setUserId(id);
		
		a.setTerm(c.getTerm());
		a.setInterestRate(c.getInterestRate());
		
		user.addBankAccount(a);
		bankAccountRepository.save(a);
		
		Transaction t = new Transaction();
		t.setSourceAccount(a.getAccountNumber());
		t.setTargetAccount(a.getAccountNumber());
		t.setTransactionMemo("Account Created");
		t.setBalanceAfterTransaction(a.getBalance());
		a.addTransaction(t);

		transactionRepository.save(t);
		bankAccountRepository.save(a);
		
		return a;
	}
	
	private List<BankAccount> getBankAccounts(long id, BankAccount t) throws NotFoundException { 
		
		BankUser user = bankUserRepository.findById(id);
		ControllerUtil.enforceFound(user);
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
}
