package manam.kiran.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	@Value("${my.greeting}")
	private String greeting;
	
	// use default value if value doesn't exist
	@Value("${my.greeting.doesnt.exist: default value}")
	private String optonalGreeting;
	
	@Value("${app.description}")
	private String appDescription;

	@GetMapping("/greeting")
	public String getGreeting() {
		return greeting + " Welcome to my app" + appDescription;
	}
	
}
