package merit.capstone.bankApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import merit.capstone.bankApp.models.CDOffering;



public interface CDOfferingRepository extends JpaRepository <CDOffering, Long> {
	
	CDOffering findById(long id);
}
