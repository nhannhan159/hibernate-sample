package com.nhannhan159.weather.common.facade;

import com.nhannhan159.weather.common.dto.CityDTO;
import com.nhannhan159.weather.common.dto.CityWeatherDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author tien.tan
 */
public interface WeatherFacade {

    Mono<CityDTO> addCity(String cityName);

    Flux<CityDTO> getCities();

    Mono<Void> deleteCity(String cityName);

    Mono<CityWeatherDTO> getCityWeather(String cityName);

    Flux<CityWeatherDTO> getCityWeathers(String cityName);
}
