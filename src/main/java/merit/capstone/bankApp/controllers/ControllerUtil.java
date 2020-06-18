package merit.capstone.bankApp.controllers;

import java.util.List;

import merit.capstone.bankApp.exceptions.NotFoundException;
import merit.capstone.bankApp.models.BankAccount;
import merit.capstone.bankApp.models.BankUser;
import merit.capstone.bankApp.models.CDOffering;


public class ControllerUtil {

	
	
	public static void enforceFound(BankAccount a) throws NotFoundException {
		if(a == null) { throw new NotFoundException(); }
	}
	
	public static void enforceFound(BankUser a) throws NotFoundException {
		if(a == null) { throw new NotFoundException(); }
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
