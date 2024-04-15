package pl.movieapp.movieapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.movieapp.movieapp.classes.Movie;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String query, String query1);
    Optional<Movie> findByTitle(String title);
    List<Movie> findByCategoryNameContainingIgnoreCase(String categoryName);
}
