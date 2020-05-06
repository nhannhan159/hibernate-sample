package com.nhannhan159.weather.data.api.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.nhannhan159.weather.common.util.ReactiveWrapper;
import com.nhannhan159.weather.data.api.model.City;
import com.nhannhan159.weather.data.api.model.CityWeather;
import com.nhannhan159.weather.data.util.ResourceUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseExtractor;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * @author tien.tan
 */
@Slf4j
@RequiredArgsConstructor
@Component
@EnableConfigurationProperties(OpenWeatherProperties.class)
public class OpenWeatherApiService {
    private static final String BULK_CITIES = "city.list.json.gz";
    private static final String BULK_WEATHERS = "weather_14.json.gz";
    private static final String APP_ID = "appid";
    private final OpenWeatherProperties properties;
    private final WebClient.Builder webClientBuilder;
    private final RestTemplateBuilder restTemplateBuilder;
    private RestTemplate restTemplate;
    private WebClient client;

    @PostConstruct
    public void init() {
        this.restTemplate = this.restTemplateBuilder.build();
        this.client = this.webClientBuilder.baseUrl(this.properties.getUrl()).build();
    }

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

    public Flux<City> fetchBulkCities() {
        var cityListType = new TypeToken<ArrayList<City>>(){}.getType();
        var url = UriComponentsBuilder.fromHttpUrl(this.properties.getBulkUrl()).build(BULK_CITIES).toString();
        return ReactiveWrapper.toFlux(() -> this.retrieveResource(url, clientHttpResponse -> {
            var rawStr = ResourceUtils.readAllLinesFromGzipStream(clientHttpResponse.getBody());
            List<City> response = null;
            try {
                response = new Gson().fromJson(rawStr, cityListType);
            } catch (Exception e) {
                log.error("error while parse json", e);
            }
            return response;
        })).doOnSubscribe(i -> log.info("subscribed bulk cities list"));
    }

    /**
     * TODO: something still blocking
     */
    public Flux<CityWeather> fetchBulkWeathers() {
        var url = UriComponentsBuilder.fromHttpUrl(this.properties.getBulkUrl()).build(BULK_WEATHERS).toString();
        return ReactiveWrapper.toFlux(this.retrieveResource(url, clientHttpResponse ->
            ResourceUtils.readLinesFromGzipStream(clientHttpResponse.getBody()))
                .map(line -> {
                    CityWeather response = null;
                    try {
                        response = new Gson().fromJson(line, CityWeather.class);
                    } catch (Exception e) {
                        log.error("error while parse json", e);
                    }
                    return response;
                })).doOnSubscribe(i -> log.info("subscribed bulk weather list"));
    }

    private <T> T retrieveResource(String url, ResponseExtractor<T> extractor) {
        return this.restTemplate.execute(url, HttpMethod.GET, null, extractor);
    }
}