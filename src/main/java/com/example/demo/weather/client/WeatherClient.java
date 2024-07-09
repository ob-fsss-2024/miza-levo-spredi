package com.outbrain.summerschool.domain.weather.client;

import com.outbrain.summerschool.domain.weather.client.dto.WeatherResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

public interface WeatherClient {
    @GetExchange("/v1/forecast?latitude={latitude}&longitude={longitude}&daily=sunrise,sunset&timezone=auto")
    WeatherResponse getWeather(@PathVariable Double latitude, @PathVariable Double longitude);
}
