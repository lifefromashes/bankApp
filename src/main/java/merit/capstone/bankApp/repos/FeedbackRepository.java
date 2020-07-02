package merit.capstone.bankApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import merit.capstone.bankApp.models.Feedback;
/*
 * Repository interface to be used by spring as it autowires access to the database
 */
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

	
}
