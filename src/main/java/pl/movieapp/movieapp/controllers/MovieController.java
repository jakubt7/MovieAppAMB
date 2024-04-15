package pl.movieapp.movieapp.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pl.movieapp.movieapp.classes.Movie;
import pl.movieapp.movieapp.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
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
    public ResponseEntity<?> addMovie(@RequestBody Movie movie) {
        //Checking if a movie with the title exists in the database
        Optional<Movie> existingMovie = movieRepository.findByTitle(movie.getTitle());
        if (existingMovie.isPresent()) {
        //Movie is present in the database notification
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Movie with title '" + movie.getTitle() + "' already exists");
        } else {
        //Movie is not present which results in inserting it to the database
            return ResponseEntity.ok(movieRepository.save(movie));
        }
    }

    //EDITING A MOVIE
    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable Long id, @RequestBody Movie movieDetails) {
        //Searching for the movie with the id provided by the PathVariable if not -> notification
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + id));

        //Setting fields to the new information provided in the RequestBody
        movie.setTitle(movieDetails.getTitle());
        movie.setProductionYear(movieDetails.getProductionYear());
        movie.setCategory(movieDetails.getCategory());
        movie.setDescription(movieDetails.getDescription());
        movie.setMeanRating(movieDetails.getMeanRating());
        movie.setAwards(movieDetails.getAwards());

        //Updating the movie in the database
        return movieRepository.save(movie);
    }

    //DELETING A MOVIE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMovie(@PathVariable Long id) {
        //Searching if the movie exists in the database
        Optional<Movie> optionalMovie = movieRepository.findById(id);

        if(optionalMovie.isPresent()) {
        //If the movie exists it gets deleted
            movieRepository.deleteById(id);
            return ResponseEntity.ok("Movie deleted successfully");
        } else {
        //User is notified if the movie does not exist or has already been deleted
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Movie not found with id: " + id);
        }
    }

    //SEARCHING
    @GetMapping("/search")
    public List<Movie> searchMovies(@RequestParam String query) {
        //Custom method filtering movies by the title/description
        return movieRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(query, query);
    }

    //FILTERING BY CATEGORY
    @GetMapping("/category/{categoryName}")
    public List<Movie> filterMoviesByCategory(@PathVariable String categoryName) {
        //Custom method filtering movies
        return movieRepository.findByCategoryNameContainingIgnoreCase(categoryName);
    }

    //ERROR HANDLING
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex) {
        return "An error occurred: " + ex.getMessage();
    }
}


