package merit.capstone.bankApp.controllers;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import merit.capstone.bankApp.exceptions.NotFoundException;
import merit.capstone.bankApp.models.BankAccount;
import merit.capstone.bankApp.models.BankUser;
import merit.capstone.bankApp.models.Transaction;
import merit.capstone.bankApp.repos.BankAccountRepository;
import merit.capstone.bankApp.repos.BankUserRepository;
import merit.capstone.bankApp.repos.TransactionRepository;
/*
 * Controllers to allow for admin accounts to adjust the funds in other accounts,
 * as well as view account history.
 * 
 * Gated behind an admin level JWT
 */
@CrossOrigin
@RestController
public class AdminTransactionController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired private BankUserRepository bankUserRepository;
    @Autowired private BankAccountRepository bankAccountRepository;
    @Autowired private TransactionRepository transactionRepository;


    @PostMapping(value = "Admin/Transaction")
	public Transaction inputTransaction(@RequestBody @Valid Transaction transaction) throws NotFoundException {
    	
        
    	BankAccount ba = bankAccountRepository.findById( transaction.getSourceAccount() );
    	ControllerUtil.enforceFound(ba);
    	
    	BankAccount bat = bankAccountRepository.findById(transaction.getTargetAccount());
    	ControllerUtil.enforceFound(bat);
    	
    	//BankUser user = bankUserRepository.findById(ba.getUserId());
    	//ControllerUtil.enforceFound(user);
    	      
        ba.processTransaction(transaction, ba, bat);
        
        bankAccountRepository.save(ba);
        transactionRepository.save(transaction);
		return transaction;
    }
    
    
    
    
    @CrossOrigin
	@GetMapping(value = "/Admin/Transaction/{id}/{activeNum}") // id is the BankAccount id
	public List<Transaction> accountHistory(@RequestHeader("Authorization") String auth, @PathVariable(name = "id") long id, @PathVariable(name = "activeNum") int activeNum) throws NotFoundException {
		boolean active = activeNum == 0 ? true : false;
    	
		BankAccount a = bankAccountRepository.findById(id);
		ControllerUtil.enforceFound(a, active);
		
		return a.getTransactions();
	}
    

}