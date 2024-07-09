package com.example.demo.weather;

import com.example.demo.weather.client.WeatherClient;
import com.example.demo.weather.client.dto.WeatherResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("weather")
public class WeatherController {
    private final WeatherClient weatherClient;

    public WeatherController(final WeatherClient weatherClient) {
        this.weatherClient = weatherClient;
    }

    @GetMapping("/forecast")
    public WeatherResponse getWeather(@RequestParam final Double latitude, @RequestParam final Double longitude) {
        return weatherClient.getWeather(latitude, longitude);
    }
}
