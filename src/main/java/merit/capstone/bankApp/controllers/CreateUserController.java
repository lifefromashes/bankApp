package merit.capstone.bankApp.controllers;



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

import merit.capstone.bankApp.exceptions.NotFoundException;
import merit.capstone.bankApp.exceptions.UsernameAlreadyExistsException;
import merit.capstone.bankApp.models.BankUser;
import merit.capstone.bankApp.repos.BankUserRepository;

@RestController
@CrossOrigin
public class CreateUserController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass() );
	
	@Autowired private BankUserRepository bankUserRepository;
	
	
	
	
	
	@PostMapping("Admin/NewUser")
	@ResponseStatus(HttpStatus.CREATED)
	public BankUser createCustomer(@Valid @RequestBody BankUser user) throws UsernameAlreadyExistsException {
		
		if( bankUserRepository.findByUsername(user.getUsername()) != null ) { 
			throw new UsernameAlreadyExistsException();
		}
		
		bankUserRepository.save(user);
		
		return user;
	}
	
	
	@GetMapping(value = "/Admin/Users")
	public Iterable<BankUser> getAccountHolders(){
		log.info("account holders queried");
		return bankUserRepository.findAll();
	}
	
	@GetMapping(value = "Admin/Users/{id}")
	public BankUser getAccountHolderByID(@Valid @PathVariable (name = "id") long id) throws NotFoundException {
		BankUser user = bankUserRepository.findById(id);
		ControllerUtil.enforceFound(user);
		return user;
	}
	
	
	
	
}
