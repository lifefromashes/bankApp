package merit.capstone.bankApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import merit.capstone.bankApp.models.CDOffering;


/*
 * Repository interface to be used by spring as it autowires access to the database
 */
public interface CDOfferingRepository extends JpaRepository <CDOffering, Long> {
	
	CDOffering findById(long id);
}
