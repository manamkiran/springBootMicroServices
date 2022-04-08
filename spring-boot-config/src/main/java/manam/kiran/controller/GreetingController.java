package manam.kiran.controller;

import java.util.List;

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
	
	@Value("static message")
	private String staticMessage;
	
	@Value("${app.description}")
	private String appDescription;
	
	@Value("${my.list.values}")
	private List<String> listValues;

	@GetMapping("/greeting")
	public String getGreeting() {
		return greeting + " Welcome to my app" + appDescription + "<br>" + listValues + "  with other messages <br>" + staticMessage+optonalGreeting;
	}
	
}
