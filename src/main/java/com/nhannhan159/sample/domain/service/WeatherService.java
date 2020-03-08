package com.nhannhan159.sample.domain.service;

import com.nhannhan159.sample.application.dto.CityDTO;
import com.nhannhan159.sample.application.dto.CityWeatherDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author tien.tan
 */
public interface WeatherService {

    Mono<CityDTO> addCity(String cityName);

    Flux<CityDTO> getCities();

    Mono<Void> deleteCity(String cityName);

    Mono<CityWeatherDTO> getCityWeather(String cityName);

    Flux<CityWeatherDTO> getCityWeathers(String cityName);
}
