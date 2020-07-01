package merit.capstone.bankApp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import merit.capstone.bankApp.models.BankUser;
import merit.capstone.bankApp.repos.BankUserRepository;
import merit.capstone.bankApp.security.AuthenticationRequest;
import merit.capstone.bankApp.security.AuthenticationResponse;
import merit.capstone.bankApp.security.JwtUtil;
import merit.capstone.bankApp.security.MyUserDetailsService;

/*
 * API in-points for logging in, and redirecting the user to either the admin page or user page, depending
 * on their token's level
 */
@CrossOrigin
@RestController
public class LoginController {
	
private Logger log = LoggerFactory.getLogger(this.getClass() );
	
	@Autowired private AuthenticationManager authenticationManager;
	@Autowired private MyUserDetailsService userDetailsService;
	@Autowired private JwtUtil jwtUtil;
	@Autowired private BankUserRepository bankUserRepository;
	
	@CrossOrigin
	@GetMapping(value = "/test")
	public String testController() {
		return "Hello";
	}
	
	@CrossOrigin
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		
		
		try {
			authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());
		
		final String jwt = jwtUtil.generateToken(userDetails);
		
		log.info( authenticationRequest.getUsername() + " logged in with token " + jwt );
		
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@CrossOrigin
	@RequestMapping(value = "/direct", method = RequestMethod.GET)
	public String directByRole(@RequestHeader("Authorization") String auth) {
		
		log.info("~~~~~~~");
		
		
		String jwt = auth.substring(7);
		String username = jwtUtil.extractUsername(jwt);
		BankUser ua = bankUserRepository.findByUsername(username);
		if(!ua.isActive()) { return "/error"; }
		if(ua.getAuthority().equals("ADMIN")) { 
			log.info("Directing " + jwtUtil.extractUsername(jwt) + " to admin.html");
			return "/admin"; 
		}
		log.info("Directing " + jwtUtil.extractUsername(jwt) + " to user.html");
		return "/user";
	}
	
	
}
