package manam.kiran.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.reactive.function.client.WebClientAutoConfiguration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import manam.kiran.models.CatalogItem;
import manam.kiran.models.Movie;
import manam.kiran.models.Rating;
import manam.kiran.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		List<Rating> ratings = restTemplate
				.getForObject("http://localhost:8083/ratingdata/users/" + userId, UserRating.class).getRatings();

		return ratings.stream().map(rating -> {

			Movie movie = restTemplate.getForObject("http://localhost:8082/movies/" + rating.getMovieId(), Movie.class);
			return new CatalogItem(movie.getName(), movie.getName() + " Description", rating.getRating());
		}).collect(Collectors.toList());

	}

	@RequestMapping("/web/{userId}")
	public List<CatalogItem> getCatalogViaWebClient(@PathVariable("userId") String userId) {

		List<Rating> ratings = Arrays.asList(new Rating("RRR", 4), new Rating("Radhe Shyam", 2));

		return ratings.stream().map(rating -> {

			Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/" + rating.getMovieId())
					.retrieve().bodyToMono(Movie.class).block();
			return new CatalogItem(rating.getMovieId(), rating.getMovieId() + " Description", rating.getRating());
		}).collect(Collectors.toList());

	}
}
