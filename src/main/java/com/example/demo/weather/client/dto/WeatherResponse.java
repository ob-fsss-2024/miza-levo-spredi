package com.outbrain.summerschool.domain.weather.client.dto;

public record WeatherResponse(
        Double latitude,
        Double longitude,
        String timezone,
        Integer elevation,
        DailyWeather daily
) {
}
