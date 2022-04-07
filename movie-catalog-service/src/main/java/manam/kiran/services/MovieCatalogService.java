package manam.kiran.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import manam.kiran.models.CatalogItem;
import manam.kiran.models.Movie;
import manam.kiran.models.Rating;
import manam.kiran.models.UserRating;

@Service
public class MovieCatalogService {

	@Autowired
	private RestTemplate restTemplate;

	@CircuitBreaker(name = "catalogService", fallbackMethod = "getFallBackCatalog")
	public CatalogItem getCatalogItem(Rating rating) {
		Movie movie = restTemplate.getForObject("http://movie-info-service/movies/" + rating.getMovieId(), Movie.class);
		return new CatalogItem(movie.getTitle(), movie.getOverview(), rating.getRating());
	}

	@CircuitBreaker(name = "userRatingService", fallbackMethod = "getFallBackUserRatings")
	public List<Rating> getUserRating(String userId) {
		return restTemplate.getForObject("http://rating-data-service/ratingdata/users/" + userId, UserRating.class)
				.getRatings();
	}

	public List<Rating> getFallBackUserRatings(String userId, Throwable t) {
		return Arrays.asList(new Rating("100", 4), new Rating("300", 3));
	}

	public CatalogItem getFallBackCatalog(Rating rating, Throwable t) {
		return new CatalogItem(rating.getMovieId(), "Movie description Not Found", rating.getRating());
	}

}
