package manam.kiran.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import manam.kiran.models.Rating;
import manam.kiran.models.UserRating;

@RestController
@RequestMapping("ratingdata")
public class RatingDataResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId) {

		return new Rating(movieId, 4);
	}

	@RequestMapping("users/{userId}")
	public UserRating getUserRatings(@PathVariable("userId") String userId) {

		UserRating userRating = new UserRating();
		userRating.setRatings(Arrays.asList(new Rating("RRR", 4), new Rating("Adbhutam", 4)));
		return userRating;
	}

}
