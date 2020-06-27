package merit.capstone.bankApp.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import merit.capstone.bankApp.models.BankUser;
import merit.capstone.bankApp.repos.BankUserRepository;



@Service
@CrossOrigin
public class MyUserDetailsService implements UserDetailsService {

	@Autowired BankUserRepository bankUserRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		BankUser u = bankUserRepository.findByUsername(username);
		
		
		if(u != null) {
			if(!u.isActive()) { return null; }
			String userLevel = u.getAuthority();
			Set<SimpleGrantedAuthority> authorities = new HashSet<>();
			authorities.add(new SimpleGrantedAuthority(userLevel));
			return new User(username, u.getPassword(), authorities);
		}
		
		return null;
	}
	
	

}
