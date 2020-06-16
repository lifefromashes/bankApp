package merit.capstone.bankApp.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import merit.capstone.bankApp.exceptions.NotFoundException;
import merit.capstone.bankApp.models.BankAccount;
import merit.capstone.bankApp.models.BankUser;
import merit.capstone.bankApp.models.Transaction;
import merit.capstone.bankApp.repos.BankAccountRepository;
import merit.capstone.bankApp.repos.BankUserRepository;
import merit.capstone.bankApp.repos.TransactionRepository;

@CrossOrigin
@RestController
public class AdminTransactionController {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired private BankUserRepository bankUserRepository;
    @Autowired private BankAccountRepository bankAccountRepository;
    @Autowired private TransactionRepository transactionRepository;


    @PostMapping(value = "Admin/Transaction/{id}")
	public Transaction getAccountHolderByID(@Valid @PathVariable (name = "id") long id, @RequestBody @Valid Transaction transaction) throws NotFoundException {
        BankAccount ba = bankAccountRepository.findByUserId(id);
		BankUser user = bankUserRepository.findById(ba.getAccountNumber());
        ControllerUtil.enforceFound(user);
        ba.processTransaction(transaction);
        bankAccountRepository.save(ba);
        transactionRepository.save(transaction);
		return transaction;
    }
    

}