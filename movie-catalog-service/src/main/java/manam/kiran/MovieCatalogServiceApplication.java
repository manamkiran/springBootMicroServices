package manam.kiran;

import java.time.Duration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig.SlidingWindowType;
import io.github.resilience4j.common.circuitbreaker.configuration.CircuitBreakerConfigCustomizer;

@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogServiceApplication {

	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		/*HttpComponentsClientHttpRequestFactory client = new HttpComponentsClientHttpRequestFactory();
		client.setConnectTimeout(3000);
		return new RestTemplate(client);*/
		return new RestTemplate();
	}
	
	/*@Bean
    public CircuitBreakerConfigCustomizer externalServiceFooCircuitBreakerConfig() {
        return CircuitBreakerConfigCustomizer
                .of("catalogService",
                        builder -> builder.slidingWindowSize(10)
                                .slidingWindowType(SlidingWindowType.COUNT_BASED)
                                .waitDurationInOpenState(Duration.ofSeconds(5))
                                .minimumNumberOfCalls(5)
                                .recordExceptions(IllegalStateException.class)
                                .failureRateThreshold(50.0f));
    }*/
	
	@Bean
	public WebClient.Builder getWebClient() {
		return WebClient.builder();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MovieCatalogServiceApplication.class, args);
	}

}
