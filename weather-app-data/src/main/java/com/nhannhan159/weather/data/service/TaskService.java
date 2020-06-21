package com.nhannhan159.weather.data.service;

import com.nhannhan159.weather.openweather.client.OpenWeatherBulkApiClient;
import com.nhannhan159.weather.openweather.model.City;
import com.nhannhan159.weather.openweather.model.CityWeather;
import lombok.Getter;
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
public class TaskService {
    private final OpenWeatherBulkApiClient openWeatherApiClient;
    @Getter private Flux<City> bulkCitiesMessage;
    @Getter private Flux<CityWeather> bulkWeathersMessage;

    //@Scheduled(cron = "0 0 0 1 1/1 *")
    @Scheduled(fixedRate = 50000)
    public void scheduleFetchBulkCities() {
        this.bulkCitiesMessage = this.openWeatherApiClient.fetchBulkCities();
    }

    //@Scheduled(cron = "0 0 0 1 1/1 *")
    //@Scheduled(fixedRate = 50000)
    //public void scheduleFetchBulkWeathers() {
    //    this.bulkWeathersMessage = this.openWeatherApiService.fetchBulkWeathers();
    //}
}
