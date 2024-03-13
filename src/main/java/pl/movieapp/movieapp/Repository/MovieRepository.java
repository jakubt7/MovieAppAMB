package pl.movieapp.movieapp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.movieapp.movieapp.Class.Movie;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String query, String query1);
    List<Movie> findByCategoryIgnoreCase(String category);
}
