package pl.movieapp.movieapp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.movieapp.movieapp.classes.MovieCategory;
import pl.movieapp.movieapp.repositories.MovieCategoryRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categories")
public class MovieCategoryController {
    @Autowired
    private MovieCategoryRepository movieCategoryRepository;

    @GetMapping("")
    public List<MovieCategory> getAllCategories() {
        return movieCategoryRepository.findAll();
    }

    @PostMapping("")
    public ResponseEntity<?> addCategory(@RequestBody MovieCategory category) {

        //Checking if a category already exists in the database
        Optional<MovieCategory> existingCategory = movieCategoryRepository.findByName(category.getName());
        if (existingCategory.isPresent()) {
        //If it already exists we return a notification
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Category with name '" + category.getName() + "' already exists");
        } else {
        //If not we insert a new category into the database
            return ResponseEntity.ok(movieCategoryRepository.save(category));
        }
    }

    @PutMapping("/{id}")
    public MovieCategory updateCategory(@PathVariable Long id, @RequestBody MovieCategory categoryDetails) {
        //Checking if a category exists in the database
        MovieCategory category = movieCategoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found with id: " + id));

        //Updating the name field
        category.setName(categoryDetails.getName());

        //Saving the category
        return movieCategoryRepository.save(category);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        // Check if the category exists in the database
        if (movieCategoryRepository.existsById(id)) {
            // If the category exists, delete it
            movieCategoryRepository.deleteById(id);
            return ResponseEntity.ok("Category deleted successfully");
        } else {
            // If the category does not exist, return a not found response
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found with id: " + id);
        }
    }


    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleAllExceptions(Exception ex) {
        return "An error occurred: " + ex.getMessage();
    }
}
