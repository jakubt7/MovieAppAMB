package pl.movieapp.movieapp.classes;

import lombok.*;

import jakarta.persistence.*;
import jakarta.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer productionYear;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private MovieCategory category;

    private String description;

    private Double meanRating;

    private String awards;
}
