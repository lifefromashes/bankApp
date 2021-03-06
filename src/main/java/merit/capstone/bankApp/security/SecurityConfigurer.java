package merit.capstone.bankApp.security;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.session.SessionManagementFilter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


/*
 * All the rules for what APIs require what token are here-
 * feedback, register, and pre-flight requests are auto approved
 * anything in the admin path requires an admin token.
 * everything else requires a regular token
 */
@SuppressWarnings("deprecation")
@EnableWebSecurity
@CrossOrigin
public class SecurityConfigurer extends WebSecurityConfigurerAdapter {
	
	final Logger log = LoggerFactory.getLogger(this.getClass() );
	
	
	@Autowired private UserDetailsService myUserDetailsService;
	@Autowired JwtRequestFilter jwtRequestFilter;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myUserDetailsService);
	}
	
	
	@Override
	@CrossOrigin
	protected void configure(HttpSecurity http) throws Exception {
		
		
		http
		.csrf().disable().authorizeRequests()
				//preflight CORS requests are of type OPTIONS, as long as NO APIs exist of this type it's safe to approve
				// them all to resolve this issue
				.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
				.antMatchers("/Admin/**").hasAuthority("ADMIN")
				.antMatchers("/authenticate").permitAll()
				.antMatchers("/NewUser").permitAll()
				.antMatchers("/Feedback").permitAll()
				.antMatchers("/FutureValue").permitAll()
				
				
				.anyRequest().authenticated()
				.and().sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
		//return new BCryptPasswordEncoder();
		//the BCryptPasswordEncoder automatically generates and uses a random salt to calculate the hash,

	}
	

}
