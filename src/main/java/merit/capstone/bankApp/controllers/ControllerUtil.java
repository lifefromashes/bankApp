package merit.capstone.bankApp.controllers;

import java.util.List;

import merit.capstone.bankApp.exceptions.NotFoundException;
import merit.capstone.bankApp.models.BankAccount;
import merit.capstone.bankApp.models.BankUser;
import merit.capstone.bankApp.models.CDOffering;

/*
 * Simple utility class for the controllers:
 * 
 * Many of the APIs throw exceptions if the object they're looking for isn't found,
 * that code is abstracted here in a single methods that's overloaded for each standard
 * configuration
 * 
 * Methods to find the best interest rates available for CD account
 */
public class ControllerUtil {

	
	
	public static void enforceFound(BankAccount a) throws NotFoundException {
		enforceFound(a, true);
	}
	
	public static void enforceFound(BankAccount a, boolean active) throws NotFoundException {
		if(a == null) { throw new NotFoundException(); }
		if(!a.isActive() == active) { throw new NotFoundException(); }
	}
	
	public static void enforceFound(CDOffering a) throws NotFoundException {
		if(a == null) { throw new NotFoundException(); }
	}
	
	public static void enforceFound(BankUser a) throws NotFoundException {
		enforceFound(a, true);
	}
	public static void enforceFound(BankUser a, boolean active) throws NotFoundException {
		if(a == null) { throw new NotFoundException(); }
		if(!a.isActive() == active) { throw new NotFoundException(); }
	}
	
	public static void enforceFound(List<?> a) throws NotFoundException {
		if(a == null) { throw new NotFoundException(); }
	}

	
	
	//public static void enforceFound(CDOffering a) throws NotFoundException {
	//	if(a == null) { throw new NotFoundException(); }
	//}

	public CDOffering getBestCDOffering(List<CDOffering> cdOfferings){
		double bestRate = 0;
		CDOffering best = null;

		if(cdOfferings.size() == 0) return null;

		for(CDOffering c : cdOfferings){
			if (c.getInterestRate() > bestRate) {
				best = c;
				bestRate = c.getInterestRate();
			}
		}
		return best;
	}

	public CDOffering getSecondBestCDOffering(List<CDOffering> cdOfferings) {
		double bestRate = 0;
		CDOffering best = null;
		CDOffering secondBest = null;

		if(cdOfferings.size() <= 1) return null;

		best = getBestCDOffering(cdOfferings);

		for(CDOffering c : cdOfferings){
			if(c == best) continue;
			if (c.getInterestRate() > bestRate) {
				secondBest = c;
				bestRate = c.getInterestRate();
			}
		}
		return secondBest;

	}

}
