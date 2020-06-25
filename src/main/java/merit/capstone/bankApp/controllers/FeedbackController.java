package merit.capstone.bankApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import merit.capstone.bankApp.models.Feedback;
import merit.capstone.bankApp.repos.FeedbackRepository;

@CrossOrigin
@RestController
public class FeedbackController {
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@PostMapping("Feedback")
	public Feedback sendFeedback(@RequestBody Feedback fb) {
		
		feedbackRepository.save(fb);
		return fb;
		
	}

}
