package com.nhannhan159.weather.data.service;

import com.google.gson.Gson;
import com.nhannhan159.weather.data.api.model.City;
import com.nhannhan159.weather.data.api.model.CityWeather;
import com.nhannhan159.weather.data.api.service.OpenWeatherApiService;
import com.nhannhan159.weather.data.repository.jpa.CityWeatherRepository;
import com.nhannhan159.weather.data.repository.reactive.ReactiveCityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * @author tien.tan
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class QueueListener {
    private final OpenWeatherApiService openWeatherApiService;
    private final ReactiveCityRepository cityRepository;
    private final CityWeatherRepository cityWeatherRepository;

    public void consumeBulkCites(Flux<City> flux) {
        var a = 1;
    }

    public void consumeBulkWeathers(Flux<CityWeather> flux) {
        var a = 1;
    }
}
