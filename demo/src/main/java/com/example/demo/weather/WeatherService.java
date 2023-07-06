package com.example.demo.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class WeatherService {
    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private RestTemplate restTemplate; // Assuming you're using RestTemplate for making HTTP requests

    public void fetchAndSaveWeatherData(String zipCode) {
        // Make an API request to the third-party weather API using the zip code
        String apiUrl = "https://api.example.com/weather/{zipCode}";
        WeatherApiResponse response = restTemplate.getForObject(apiUrl, WeatherApiResponse.class, zipCode);

        // Parse the response and extract the relevant weather information
        Weather weather = new Weather();
        weather.setZipCode(zipCode);
        weather.setCity(response.getCity());
        weather.setTemperature(response.getTemperature());
        weather.setHumidity(response.getHumidity());

        // Save the Weather object in the database using the WeatherRepository
        weatherRepository.save(weather);
    }
    @Scheduled(cron = "0 0 * * * *") // Run every hour
    public void scheduledWeatherDataUpdate() {
        List<String> zipCodes = weatherRepository.findAllZipCodes();

        // Fetch and save weather data for each zip code
        for (String zipCode : zipCodes) {
            fetchAndSaveWeatherData(zipCode);
        }
    }
}
