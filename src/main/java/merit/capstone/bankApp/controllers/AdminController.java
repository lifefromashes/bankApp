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

import merit.capstone.bankApp.models.BankAccount;
import merit.capstone.bankApp.models.CheckingAccount;
import merit.capstone.bankApp.models.DBACheckingAccount;
import merit.capstone.bankApp.models.RegularIRA;
import merit.capstone.bankApp.models.RolloverIRA;
import merit.capstone.bankApp.models.RothIRA;
import merit.capstone.bankApp.models.SavingsAccount;
import merit.capstone.bankApp.models.BankUser;
import merit.capstone.bankApp.models.CDAccount;
import merit.capstone.bankApp.repos.BankAccountRepository;
import merit.capstone.bankApp.repos.BankUserRepository;

/**
 * 
 * This controller is handles APIs for basic bank worker functions like creating new accounts
 * or viewing information by the user's ID
 * 
 *
 */

@CrossOrigin
@RestController
public class AdminController {
	private Logger log = LoggerFactory.getLogger(this.getClass() );
	
	@Autowired private BankUserRepository bankUserRepository;
	@Autowired private BankAccountRepository bankAccountRepository;
	
	
	
	
	
	
	
	
	@PostMapping("Admin/{id}/CDAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addCDAccount(@PathVariable(name = "id") long id, @RequestBody @Valid CDAccount a) {
				//throws NotFoundException, ExceedsCombinedBalanceLimitException {
		return createAccount(id, a);
	}
	@PostMapping("Admin/{id}/CheckingAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addCheckingAccount(@PathVariable(name = "id") long id, @RequestBody @Valid CheckingAccount a) {
				//throws NotFoundException, ExceedsCombinedBalanceLimitException {
		return createAccount(id, a);
	}
	@PostMapping("Admin/{id}/DBACheckingAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addDBACheckingAccount(@PathVariable(name = "id") long id, @RequestBody @Valid DBACheckingAccount a) {
				//throws NotFoundException, ExceedsCombinedBalanceLimitException {
		return createAccount(id, a);
	}
	@PostMapping("Admin/{id}/RegularIRA")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addRegularIRA(@PathVariable(name = "id") long id, @RequestBody @Valid RegularIRA a) {
				//throws NotFoundException, ExceedsCombinedBalanceLimitException {
		return createAccount(id, a);
	}
	@PostMapping("Admin/{id}/RolloverIRA")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addRolloverIRA(@PathVariable(name = "id") long id, @RequestBody @Valid RolloverIRA a) {
				//throws NotFoundException, ExceedsCombinedBalanceLimitException {
		return createAccount(id, a);
	}
	@PostMapping("Admin/{id}/RothIRA")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addRothIRA(@PathVariable(name = "id") long id, @RequestBody @Valid RothIRA a) {
				//throws NotFoundException, ExceedsCombinedBalanceLimitException {
		return createAccount(id, a);
	}
	@PostMapping("Admin/{id}/SavingsAccount")
	@ResponseStatus(HttpStatus.CREATED)
	public BankAccount addCheckingAccount(@PathVariable(name = "id") long id, @RequestBody @Valid SavingsAccount a) {
				//throws NotFoundException, ExceedsCombinedBalanceLimitException {
		return createAccount(id, a);
	}
	
	/*
	@GetMapping("Admin/{id}/CheckingAccounts")
	public List<BankAccount> getCheckingAccounts(@PathVariable(name = "id") long id) { 
				// throws NotFoundException {
		return getBankAccounts(id, new CheckingAccount());
	}
	*/

	
	@GetMapping("Admin/{id}/{t}")
	public List<BankAccount> getSavingsAccounts(@PathVariable(name = "id") long id, @PathVariable(name = "t") String t) { 
				// throws NotFoundException {
		
		switch(t) {
			case "CDAccount": return getBankAccounts(id, new CDAccount());
			case "CheckingAccount": return getBankAccounts(id, new CheckingAccount());
			case "DBACheckingAccount": return getBankAccounts(id, new DBACheckingAccount());
			case "RegularIRA": return getBankAccounts(id, new RegularIRA());
			case "RolloverIRA": return getBankAccounts(id, new RolloverIRA());
			case "RothIRA": return getBankAccounts(id, new RothIRA());
			case "SavingsAccount": return getBankAccounts(id, new SavingsAccount());
			default: // throw something
		}
		
		return getBankAccounts(id, new SavingsAccount()); // TODO: change this line to a throw
	}
	
	
	
	
	
	
	

	
	private BankAccount createAccount(long id, BankAccount a) { //throws
		BankUser user = bankUserRepository.findById(id);
		ControllerUtil.enforceFound(user);
		a.setUserId(id);
		user.addBankAccount(a);
		bankAccountRepository.save(a);
		
		return a;
	}
	
	private List<BankAccount> getBankAccounts(long id, BankAccount t){ //throws
		
		BankUser user = bankUserRepository.findById(id);
		ControllerUtil.enforceFound(user);
		List<BankAccount> a = bankAccountRepository.findByUserId(user.getId());
		List<BankAccount> matching = new ArrayList<>();
		for(BankAccount b : a) {
			if(b.getClass() == t.getClass()) {
				matching.add(b);
			}
		}
		ControllerUtil.enforceFound(matching);
		return matching;
	}
}
