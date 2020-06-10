package merit.capstone.bankApp.models;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

@Entity
public class CDOffering {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Min(1)
	private int term;
	
	@DecimalMin("0.0")
	@DecimalMax("0.99999")
	private double interestRate;
	
	
	public CDOffering(int term, double interestRate){
		this.term = term;
		this.interestRate = interestRate;
	}
	
	public CDOffering() { }
	
	public long getId() { return this.id; }
	public void setId(long id) { this.id = id; }
	
	public int getTerm() { return this.term; }
	public void setTerm(int n) { this.term = n; }
	
	public double getInterestRate() { return this.interestRate; }
	public void setInterestRate(double n) { this.interestRate = n; }

}

