package com.outbrain.summerschool.domain.weather.client.dto;

import java.util.List;

public record DailyWeather(
        List<String> sunrise,
        List<String> sunset
) { }
