package pl.movieapp.movieapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.movieapp.movieapp.classes.MovieCategory;

import java.util.Optional;

public interface MovieCategoryRepository extends JpaRepository<MovieCategory, Long> {
    Optional<MovieCategory> findByName(String name);
}
