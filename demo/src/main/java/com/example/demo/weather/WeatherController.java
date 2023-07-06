package com.example.demo.weather;

import com.example.demo.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private WeatherRepository weatherRepository;
    @GetMapping("/{zipCode}")
    public Weather getWeatherByZipCode(@PathVariable String zipCode) {
        // Retrieve weather data from the database using the zip code
        return weatherRepository.findByZipCode(zipCode)
                .orElseThrow(() -> new ResourceNotFoundException("Weather data not found for zip code: " + zipCode));
    }

    @PostMapping
    public void updateWeatherData() {
        // Trigger the weather data update manually
        // This endpoint can be invoked by a scheduler or an external trigger
        List<String> zipCodes = Arrays.asList("12345", "67890"); // Example zip codes to update
        for (String zipCode : zipCodes) {
            weatherService.fetchAndSaveWeatherData(zipCode);
        }
    }
}

