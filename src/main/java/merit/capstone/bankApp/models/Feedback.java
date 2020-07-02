package merit.capstone.bankApp.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*
 * Simple model class to represent user feedback.
 */
@Entity
public class Feedback {
	
	private String firstname;
	private String lastname;
	private String email;
	private String subject;
	private String message;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	public Feedback() {
		this.firstname = "";
		this.lastname = "";
		this.email = "";
		this.subject = "";
		this.message = "";
	}
	
	

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	
	
	
	
	
	

}
