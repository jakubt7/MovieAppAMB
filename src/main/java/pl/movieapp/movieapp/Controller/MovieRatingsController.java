package pl.movieapp.movieapp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.movieapp.movieapp.Class.Movie;
import pl.movieapp.movieapp.Class.MovieRatings;
import pl.movieapp.movieapp.Repository.MovieRatingsRepository;
import pl.movieapp.movieapp.Repository.MovieRepository;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/ratings")
public class MovieRatingsController {
    @Autowired
    private MovieRatingsRepository movieRatingsRepository;

    @Autowired
    private MovieRepository movieRepository;

    @PostMapping("/{movieId}")
    public Movie addRatingToMovie(@PathVariable Long movieId, @RequestParam int rating) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found with id: " + movieId));

        MovieRatings movieRatings = new MovieRatings();
        movieRatings.setMovie(movie);
        movieRatings.setRating(rating);

        movieRatingsRepository.save(movieRatings);

        List<MovieRatings> ratings = movieRatingsRepository.findByMovieId(movieId);
        double meanRating = ratings.stream()
                .mapToDouble(MovieRatings::getRating)
                .average()
                .orElse(0.0);

        movie.setMean_rating(meanRating);

        return movieRepository.save(movie);
    }
}