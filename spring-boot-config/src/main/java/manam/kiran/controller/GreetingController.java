package manam.kiran.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

	@Value("${my.greeting}")
	private String greeting;
	
	@Value("${app.description}")
	private String appDescription;

	@GetMapping("/greeting")
	public String getGreeting() {
		return greeting + " Welcome to my app" + appDescription;
	}
	
}
