package merit.capstone.bankApp.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import merit.capstone.bankApp.models.Feedback;

public interface FeedbackRepository extends JpaRepository<Feedback, Long> {

	
}
