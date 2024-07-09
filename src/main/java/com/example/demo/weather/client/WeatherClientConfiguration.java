package com.example.demo.weather.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@Configuration
public class WeatherClientConfiguration {
    @Bean
    public WeatherClient weatherClient(@Value("${weather.api.url}") final String weatherApiUrl) {
        final RestClient restClient = RestClient.builder().baseUrl(weatherApiUrl).build();
        final RestClientAdapter adapter = RestClientAdapter.create(restClient);
        final HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();

        return factory.createClient(WeatherClient.class);
    }
}
