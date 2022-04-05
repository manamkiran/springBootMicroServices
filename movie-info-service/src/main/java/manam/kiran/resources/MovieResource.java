package manam.kiran.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import manam.kiran.models.Movie;

@RestController
@RequestMapping("movies")
public class MovieResource {
	
	@Value("${api.key}")
	private String apiKey;
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable("movieId")String movieId) {
		Movie movie = restTemplate.getForObject("https://api.themoviedb.org/3/movie/"+ movieId+"?api_key="+apiKey, Movie.class);
		return movie;
		
	}
}
