package pl.movieapp.movieapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.movieapp.movieapp.classes.Movie;
import pl.movieapp.movieapp.classes.MovieRatings;
import pl.movieapp.movieapp.repositories.MovieRatingsRepository;
import pl.movieapp.movieapp.repositories.MovieRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ratings")
public class MovieRatingsController {
    @Autowired
    private MovieRatingsRepository movieRatingsRepository;

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping("/{movieId}")
    public Movie addRatingToMovie(@PathVariable Long movieId, @RequestParam int rating) {
        //Checking database for a movie with the id given in the PathVariable
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Movie not found with id: " + movieId));

        //Creating an object
        MovieRatings movieRatings = new MovieRatings();

        //Setting data for the object
        movieRatings.setMovie(movie);
        movieRatings.setRating(rating);

        //Saving the rating for the given movie
        movieRatingsRepository.save(movieRatings);

        //Average calculating mechanism
        List<MovieRatings> ratings = movieRatingsRepository.findByMovieId(movieId);
        double meanRating = ratings.stream()
                .mapToDouble(MovieRatings::getRating)
                .average()
                .orElse(0.0);

        //Setting mean rating
        movie.setMeanRating(meanRating);

        //Updating the movie mean rating
        return movieRepository.save(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRating(@PathVariable Long id) {
        Optional<MovieRatings> optionalMovieRatings = movieRatingsRepository.findById(id);
        if (optionalMovieRatings.isPresent()) {
            movieRatingsRepository.deleteById(id);
            return ResponseEntity.ok("Rating deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Rating not found with id: " + id);
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex) {
        return "An error occurred: " + ex.getMessage();
    }
}
