package merit.capstone.bankApp.security;
/*
 * model class to represent auth HTTP requests as they come in so that our security classes can interact with them
 */
public class AuthenticationRequest {
	
	private String username;
	private String password;
	
	public AuthenticationRequest (String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public AuthenticationRequest() {}
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
