package com.nhannhan159.weather.data.service;

import com.google.gson.Gson;
import com.nhannhan159.weather.data.api.service.OpenWeatherApiService;
import com.nhannhan159.weather.data.repository.jpa.CityWeatherRepository;
import com.nhannhan159.weather.data.repository.reactive.ReactiveCityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author tien.tan
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class TaskService {
    private final OpenWeatherApiService openWeatherApiService;
    private final ReactiveCityRepository cityRepository;
    private final CityWeatherRepository cityWeatherRepository;

    @Scheduled(cron = "0 0 0 1 1/1 *")
    public void getBulkCities() {
        this.openWeatherApiService.fetchBulkCities()
            .subscribe(i -> log.info("line: " + new Gson().toJson(i)));
    }
}
