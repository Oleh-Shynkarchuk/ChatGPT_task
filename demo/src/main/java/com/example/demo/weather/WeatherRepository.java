package com.example.demo.weather;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    List<String> findAllZipCodes();

    Optional<Weather> findByZipCode(String zipCode);
}
