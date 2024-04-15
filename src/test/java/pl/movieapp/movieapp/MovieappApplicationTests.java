package pl.movieapp.movieapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.movieapp.movieapp.classes.MovieCategory;
import pl.movieapp.movieapp.repositories.MovieCategoryRepository;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class MovieappApplicationTests {

	@MockBean
	private MovieCategoryRepository movieCategoryRepository;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetAllCategories() throws Exception {
		//Creating mock data
		MovieCategory category1 = new MovieCategory(1L, "Action");
		MovieCategory category2 = new MovieCategory(2L, "Comedy");

		when(movieCategoryRepository.findAll()).thenReturn(Arrays.asList(category1, category2));

		//Performing a get request and checking for the expected outcome
		mockMvc.perform(MockMvcRequestBuilders.get("/categories"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").isArray())
				.andExpect(jsonPath("$").isNotEmpty())
				.andExpect(jsonPath("$[0].name").value("Action"))
				.andExpect(jsonPath("$[1].name").value("Comedy"));
	}
}
