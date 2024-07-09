package com.example.demo.weather;

import com.outbrain.summerschool.domain.weather.client.WeatherClient;
import com.outbrain.summerschool.domain.weather.client.dto.WeatherResponse;
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
