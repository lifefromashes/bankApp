package merit.capstone.bankApp.controllers;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;



import merit.capstone.bankApp.models.User;

@RestController
public class AdminController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass() );
	
	@Autowired private User userRepository;
	
	
	
	
	@CrossOrigin
	@PostMapping("Admin/NewCustomer")
	@ResponseStatus(HttpStatus.CREATED)
	public User createCustomer( @RequestBody @Valid User user) {
		
		
		
		
		
		
		userRepository.save(user);
		
		
		return user;
	}
	
	
	
	
}
