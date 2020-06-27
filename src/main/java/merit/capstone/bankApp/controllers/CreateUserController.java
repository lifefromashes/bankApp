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

import merit.capstone.bankApp.exceptions.MaxAccountsReachedException;
import merit.capstone.bankApp.exceptions.NotFoundException;
import merit.capstone.bankApp.exceptions.UsernameAlreadyExistsException;
import merit.capstone.bankApp.models.BankUser;
import merit.capstone.bankApp.models.SavingsAccount;
import merit.capstone.bankApp.models.Transaction;
import merit.capstone.bankApp.repos.BankAccountRepository;
import merit.capstone.bankApp.repos.BankUserRepository;
import merit.capstone.bankApp.repos.CDOfferingRepository;
import merit.capstone.bankApp.repos.TransactionRepository;
import merit.capstone.bankApp.security.JwtUtil;

@RestController
@CrossOrigin
public class CreateUserController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass() );
	
	@Autowired private BankUserRepository bankUserRepository;
	@Autowired private BankAccountRepository bankAccountRepository;
	@Autowired private CDOfferingRepository cdOfferingRepository;
	@Autowired private TransactionRepository transactionRepository;
	@Autowired private JwtUtil jwtUtil;
	
	
	
	
	@PostMapping("NewUser")
	@ResponseStatus(HttpStatus.CREATED)
	public BankUser createCustomer(@Valid @RequestBody BankUser user) throws UsernameAlreadyExistsException, MaxAccountsReachedException {
		
		if( bankUserRepository.findByUsername(user.getUsername()) != null ) { 
			throw new UsernameAlreadyExistsException();
		}
		
		if(user.getAuthority() == null) { user.setAuthority("USER"); } // maybe remove if front handles
		
		bankUserRepository.save(user);
		
		SavingsAccount a = new SavingsAccount();
		a.setUserId(user.getId());
		user.addBankAccount(a);
		bankAccountRepository.save(a);
		
		Transaction t = new Transaction();
		t.setSourceAccount(a.getAccountNumber());
		t.setTargetAccount(a.getAccountNumber());
		t.setTransactionMemo("Account Created");
		t.setTransactionSuccess(true);
		a.addTransaction(t);

		transactionRepository.save(t);
		bankAccountRepository.save(a);
		
		
		
		
		return user;
	}
	
	
	@GetMapping(value = "/Admin/AllUsers")
	public Iterable<BankUser> getAllBankUsers(){
		log.info("account holders queried");
		return bankUserRepository.findAll();
	}
	
	@GetMapping(value = "/Admin/Users")
	public Iterable<BankUser> getAccountHolders(){
		List<BankUser> bu = bankUserRepository.findAll();
		List<BankUser> ah = new ArrayList<>();
		for(BankUser b : bu) {
			if(!b.getAuthority().equals("ADMIN")) {
				b.setTotalValue(b.getCombinedBalance());
				ah.add(b);
			}
		}
		return ah;
	}
	
	@GetMapping(value = "Admin/Users/{id}")
	public BankUser getAccountHolderByID(@Valid @PathVariable (name = "id") long id) throws NotFoundException {
		BankUser user = bankUserRepository.findById(id);
		ControllerUtil.enforceFound(user);
		return user;
	}
	
	//front should pre-load the text fields with existing info by calling GET Admin/Users/ID 
	@PutMapping(value = "/Admin/UserInfo/{id}")
	public BankUser changeAccountHolderInfoByID(@PathVariable (name = "id") long id, @RequestBody BankUser newInfo) throws NotFoundException {
		BankUser user = bankUserRepository.findById(id);
		user.updateContactInfo(newInfo);
		return user;
	}
	
	
	@PostMapping("Contact")
	@ResponseStatus(HttpStatus.CREATED)
	public BankUser updateUserInfo(@RequestHeader("Authorization") String auth, @Valid @RequestBody BankUser u) throws NotFoundException {
		
		String jwt = auth.substring(7);
		String username = jwtUtil.extractUsername(jwt);
		BankUser user = bankUserRepository.findByUsername(username);
		ControllerUtil.enforceFound(user);
		
		user.setEmail(u.getEmail());
		user.setPhone(u.getPhone());
		user.setAddress(u.getAddress());
		user.setCity(u.getCity());
		user.setState(u.getState());
		user.setZip(u.getZip());
		
		bankUserRepository.save(user);
		
		return user;
	}
	
}
