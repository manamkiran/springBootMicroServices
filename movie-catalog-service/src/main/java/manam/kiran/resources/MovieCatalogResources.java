package manam.kiran.resources;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import manam.kiran.models.CatalogItem;
import manam.kiran.models.Movie;
import manam.kiran.models.Rating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {
	
	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

		List<Rating> ratings = Arrays.asList(new Rating("RRR", 4), new Rating("Radhe Shyam", 2));

		return ratings.stream().map(rating -> {

			Movie movie= restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);
			return new CatalogItem(rating.getMovieId(), rating.getMovieId()+" Description", rating.getRating());
		}).collect(Collectors.toList());

	}
}
