package manam.kiran.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import manam.kiran.models.CatalogItem;
import manam.kiran.models.Movie;
import manam.kiran.models.Rating;
import manam.kiran.services.MovieCatalogService;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {

	@Autowired
	private MovieCatalogService movieCatalogService;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {
		List<Rating> ratings = movieCatalogService.getUserRating(userId);

		return ratings.stream().map(rating -> {
			return movieCatalogService.getCatalogItem(rating);
		}).collect(Collectors.toList());

	}

	
	/*
	 * public List<CatalogItem> getFallBackCatalog(@PathVariable("userId") String
	 * userId) {
	 * 
	 * return Arrays.asList(new CatalogItem("No Movie", "", 0)); }
	 */

	@RequestMapping("/web/{userId}")
	public List<CatalogItem> getCatalogViaWebClient(@PathVariable("userId") String userId) {

		List<Rating> ratings = Arrays.asList(new Rating("100", 4), new Rating("143", 2));

		return ratings.stream().map(rating -> {

			Movie movie = webClientBuilder.build().get().uri("http://localhost:8082/movies/" + rating.getMovieId())
					.retrieve().bodyToMono(Movie.class).block();
			return new CatalogItem(rating.getMovieId(), rating.getMovieId() + " Description", rating.getRating());
		}).collect(Collectors.toList());

	}
}
