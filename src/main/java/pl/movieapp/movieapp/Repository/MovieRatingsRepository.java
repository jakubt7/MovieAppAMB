package pl.movieapp.movieapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.movieapp.movieapp.Class.MovieRatings;

import java.util.List;

@Repository
public interface MovieRatingsRepository extends JpaRepository<MovieRatings, Long> {
    List<MovieRatings> findByMovieId(Long movieId);
}