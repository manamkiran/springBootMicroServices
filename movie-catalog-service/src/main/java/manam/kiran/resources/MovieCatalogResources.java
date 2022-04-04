package manam.kiran.resources;

import java.util.Collections;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import manam.kiran.models.CatalogItem;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResources {

	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId") String userId) {

//		List<CatalogItem> items;

		return Collections.singletonList(new CatalogItem("RRR", "Telugu Movie", 4));

	}
}
