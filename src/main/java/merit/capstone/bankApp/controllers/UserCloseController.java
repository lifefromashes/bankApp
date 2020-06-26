package merit.capstone.bankApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import merit.capstone.bankApp.exceptions.CannotCloseAccountException;
import merit.capstone.bankApp.exceptions.ExceedsAvailableBalanceException;
import merit.capstone.bankApp.exceptions.NegativeAmountException;
import merit.capstone.bankApp.exceptions.NotFoundException;
import merit.capstone.bankApp.exceptions.TransactionNotAllowedException;
import merit.capstone.bankApp.models.BankAccount;
import merit.capstone.bankApp.models.BankUser;
import merit.capstone.bankApp.models.Transaction;
import merit.capstone.bankApp.repos.BankAccountRepository;
import merit.capstone.bankApp.repos.BankUserRepository;
import merit.capstone.bankApp.repos.CDOfferingRepository;
import merit.capstone.bankApp.repos.TransactionRepository;
import merit.capstone.bankApp.security.JwtUtil;
import merit.capstone.bankApp.security.MyUserDetailsService;

@RestController
public class UserCloseController {

    @Autowired private BankUserRepository bankUserRepository;
	@Autowired private BankAccountRepository bankAccountRepository;
    @Autowired private CDOfferingRepository cdOfferingRepository;
    @Autowired private JwtUtil jwtUtil;
	@Autowired private MyUserDetailsService userDetailsService;
    @Autowired private TransactionRepository transactionRepository;

    private BankUser findUser(String auth) throws NotFoundException {
		String jwt = auth.substring(7);
		String username = jwtUtil.extractUsername(jwt);
		BankUser user = bankUserRepository.findByUsername(username);
		ControllerUtil.enforceFound(user);
		return user;
    }
    
    @CrossOrigin
	@PutMapping(value = "/User/Close/{id}")
	public BankAccount closeAccoun(@RequestHeader("Authorization") String auth, @PathVariable (name="id") long id) throws TransactionNotAllowedException,NotFoundException, CannotCloseAccountException, ExceedsAvailableBalanceException, NegativeAmountException {
		
		BankUser user = findUser(auth);
		ControllerUtil.enforceFound(user);
		
		BankAccount a = bankAccountRepository.findById(id);
		ControllerUtil.enforceFound(a);
		
		// prevent anyone without the correct token from accessing other's accounts by editing the origin url
		if(a.getUserId() != user.getId()) { throw new NotFoundException(); }
		
		Transaction t = a.closeAccount(user); 
		
		bankAccountRepository.save(a);
		if(t != null) { transactionRepository.save(t); }
		return a;
	}

}