package pl.movieapp.movieapp.Controller;

import pl.movieapp.movieapp.Class.Movie;
import pl.movieapp.movieapp.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    //ALL MOVIES
    @GetMapping("")
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    //ADDING A MOVIE
    @PostMapping("")
    public Movie addMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    //EDITING A MOVIE
    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));

        movie.setTitle(movieDetails.getTitle());
        movie.setProductionYear(movieDetails.getProductionYear());
        movie.setCategory(movieDetails.getCategory());
        movie.setDescription(movieDetails.getDescription());
        movie.setMean_rating(movieDetails.getMean_rating());
        movie.setAwards(movieDetails.getAwards());

        return movieRepository.save(movie);
    }

    //DELETING A MOVIE
    @DeleteMapping("/{id}")
    public String deleteMovie(@PathVariable Long id) {
        movieRepository.deleteById(id);
        return "Movie deleted successfully";
    }

    //SEARCHING
    @GetMapping("/search")
    public List<Movie> searchMovies(@RequestParam String query) {
        return movieRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    }

    //FILTERING BY CATEGORY
    @GetMapping("/category/{category}")
    public List<Movie> filterMoviesByCategory(@PathVariable String category) {
        return movieRepository.findByCategoryIgnoreCase(category);
    }
}