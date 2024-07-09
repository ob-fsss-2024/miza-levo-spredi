package com.example.demo.weather.client.dto;

import java.util.List;

public record DailyWeather(
        List<String> sunrise,
        List<String> sunset
) { }
