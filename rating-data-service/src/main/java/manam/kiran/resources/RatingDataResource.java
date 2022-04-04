package manam.kiran.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import manam.kiran.models.Rating;

@RestController
@RequestMapping("ratingdata")
public class RatingDataResource {

	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId")String movieId) {
	
		return new Rating(movieId,4);
	}
	
}
