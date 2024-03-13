package pl.movieapp.movieapp.Class;

import lombok.*;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import java.util.List;

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

    private int productionYear;

    private String category;

    private String description;

    private Double mean_rating;

    private String awards;


}
