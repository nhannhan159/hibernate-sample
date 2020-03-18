package com.nhannhan159.openweather.api;

import com.nhannhan159.openweather.model.CityWeather;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

/**
 * @author tien.tan
 */
@RequiredArgsConstructor
@Component
@EnableConfigurationProperties(OpenWeatherProperties.class)
public class OpenWeatherApiService {
    private final OpenWeatherProperties properties;
    private WebClient client;

    @PostConstruct
    public void init() {
        this.client = WebClient.create(this.properties.getUrl());
    }

    public Mono<CityWeather> fetchCityWeather(String cityName) {
        return this.client.get()
            .uri(uriBuilder -> uriBuilder
                .queryParam("appid", this.properties.getAppId())
                .queryParam("q", cityName)
                .build())
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(CityWeather.class);
    }
}