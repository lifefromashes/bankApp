package merit.capstone.bankApp.security;

/*
 * model class to represent auth HTTP responces before we send them bacl so that our security classes can interact with them
 */
public class AuthenticationResponse {
	
	private final String jwt;

	public AuthenticationResponse (String jwt) {
		this.jwt = jwt;
	}
	
	public String getJwt() {
		return jwt;
	}
	
	

}
