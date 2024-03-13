package pl.movieapp.movieapp.Class;
import lombok.*;

import jakarta.persistence.*;
import jakarta.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "movie_ratings")
public class MovieRatings {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Movie movie;

    private double rating;
}
