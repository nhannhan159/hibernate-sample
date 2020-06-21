package com.nhannhan159.weather.openweather.client;

import com.nhannhan159.weather.openweather.config.OpenWeatherProperties;
import com.nhannhan159.weather.openweather.model.CityWeather;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;

/**
 * @author tien.tan
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class OpenWeatherApiClient {
    private static final String APP_ID = "appid";
    private final OpenWeatherProperties properties;
    private final WebClient.Builder webClientBuilder;
    private WebClient client;

    @PostConstruct
    public void init() {
        this.client = this.webClientBuilder.baseUrl(this.properties.getUrl()).build();
    }

    @Retry(name = "open-weather")
    public Mono<CityWeather> fetchCityWeather(String cityName) {
        return this.client.get()
            .uri(uriBuilder -> uriBuilder
                .queryParam(APP_ID, this.properties.getAppId())
                .queryParam("q", cityName)
                .build())
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(CityWeather.class);
    }
}