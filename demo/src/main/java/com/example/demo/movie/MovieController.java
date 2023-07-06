package com.example.demo.movie;

import com.example.demo.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieRepository movieRepository;

    @GetMapping
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @PostMapping
    public Movie createMovie(@RequestBody Movie movie) {
        return movieRepository.save(movie);
    }

    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable String id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));
    }

    @PutMapping("/{id}")
    public Movie updateMovie(@PathVariable String id, @RequestBody Movie movieDetails) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        movie.setTitle(movieDetails.getTitle());
        movie.setReleaseDate(movieDetails.getReleaseDate());
        movie.setRuntime(movieDetails.getRuntime());
        movie.setActors(movieDetails.getActors());
        movie.setDirectors(movieDetails.getDirectors());

        return movieRepository.save(movie);
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable String id) {
        Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movie not found with id: " + id));

        movieRepository.delete(movie);
    }

    @GetMapping("/search")
    public List<Movie> searchMovies(@RequestParam(required = false) String title, @RequestParam(required = false) String releaseDate) {
        if (title != null && releaseDate != null) {
            return movieRepository.findByTitleAndReleaseDate(title, releaseDate);
        } else if (title != null) {
            return movieRepository.findByTitle(title);
        } else if (releaseDate != null) {
            return movieRepository.findByReleaseDate(releaseDate);
        } else {
            return movieRepository.findAll();
        }
    }
}
