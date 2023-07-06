package com.example.demo.movie;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MovieRepository extends MongoRepository<Movie, String> {
    List<Movie> findByTitleAndReleaseDate(String title, String releaseDate);

    List<Movie> findByTitle(String title);

    List<Movie> findByReleaseDate(String releaseDate);
}

