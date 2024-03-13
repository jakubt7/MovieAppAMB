DROP DATABASE movie_db;

CREATE DATABASE IF NOT EXISTS movie_db;

USE movie_db;

CREATE TABLE IF NOT EXISTS movies
(
    id              INT AUTO_INCREMENT PRIMARY KEY,
    title           VARCHAR(255) NOT NULL,
    production_year INT          NOT NULL,
    category        VARCHAR(255),
    description     TEXT,
    mean_rating      DOUBLE,
    awards          VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS movie_ratings
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    movie_id INT NOT NULL,
    rating   INT NOT NULL
);

ALTER TABLE `movie_ratings` ADD CONSTRAINT `ratings_fk0` FOREIGN KEY (`movie_id`) REFERENCES `movies`(`id`) ON DELETE CASCADE ON UPDATE CASCADE;


INSERT INTO movies (title, production_year, category, description, mean_rating, awards)
VALUES
    ('The Shawshank Redemption', 1994, 'Drama', 'Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency.', NULL, 'Academy Award for Best Actor, Academy Award for Best Picture'),
    ('The Dark Knight', 2008, 'Action', 'When the menace known as The Joker emerges from his mysterious past, he wreaks havoc and chaos on the people of Gotham.', NULL, 'Academy Award for Best Supporting Actor, Academy Award for Best Sound Editing'),
    ('Pulp Fiction', 1994, 'Crime', 'The lives of two mob hitmen, a boxer, a gangster and his wife, and a pair of diner bandits intertwine in four tales of violence and redemption.', NULL, 'Academy Award for Best Original Screenplay, Academy Award for Best Supporting Actor');

INSERT INTO movie_ratings (movie_id, rating)
VALUES
    (1, 4),
    (2, 5),
    (3, 3);




