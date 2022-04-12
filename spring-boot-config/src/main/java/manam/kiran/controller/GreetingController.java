package manam.kiran.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import manam.kiran.setting.DBSettings;

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

	@Value("#{${dbValues}}")
	private Map<String, String> dbValues;

	@Autowired
	private DBSettings dbSettings;
	
	@Autowired
	private Environment env;

	@GetMapping("/greeting")
	public String getGreeting() {
		return greeting + " Welcome to my app" + appDescription + "<br>" + listValues + "  with other messages <br>"
				+ staticMessage + optonalGreeting + "<br>" + dbValues + "<br> Printing DB Settings <br>"
				+ dbSettings.getConnection() + "<br>" + dbSettings.getHost() + "<br>" + dbSettings.getPort() + "<br>";
	}
	
	@GetMapping("/envDetails")
	public String getEnvironmentDetails() {
		
		return env.toString();
		//return env.getProperty("app.description");
	}

}
