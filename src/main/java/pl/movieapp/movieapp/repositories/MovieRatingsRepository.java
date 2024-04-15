package pl.movieapp.movieapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.movieapp.movieapp.classes.MovieRatings;

import java.util.List;

@Repository
public interface MovieRatingsRepository extends JpaRepository<MovieRatings, Long> {
    List<MovieRatings> findByMovieId(Long movieId);
}