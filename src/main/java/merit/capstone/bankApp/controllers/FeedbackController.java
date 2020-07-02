package merit.capstone.bankApp.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import merit.capstone.bankApp.models.Feedback;
import merit.capstone.bankApp.repos.FeedbackRepository;

/*
 * simple controller to save user feedback to the database so that it can be reviewed at a later time
 */
@CrossOrigin
@RestController
public class FeedbackController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private FeedbackRepository feedbackRepository;
	
	@PostMapping("/Feedback")
	public Feedback sendFeedback(@RequestBody Feedback fb) {
		
		feedbackRepository.save(fb);
		log.info("Feedback form accepted.");
		return fb;
		
	}

}
