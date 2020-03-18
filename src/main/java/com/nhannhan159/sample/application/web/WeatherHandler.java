package com.nhannhan159.sample.application.web;

import com.nhannhan159.sample.application.dto.CityDTO;
import com.nhannhan159.sample.application.dto.CityWeatherDTO;
import com.nhannhan159.sample.domain.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * @author tien.tan
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class WeatherHandler {
    private final WeatherService weatherService;

    @NonNull
    public Mono<ServerResponse> getCities(ServerRequest request) {
        return ServerResponse.ok().body(weatherService.getCities(), CityDTO.class);
    }

    @NonNull
    public Mono<ServerResponse> addCity(ServerRequest request) {
        var cityName = request.pathVariable("name");
        return ServerResponse.ok().body(this.weatherService.addCity(cityName), CityDTO.class);
    }

    @NonNull
    public Mono<ServerResponse> deleteCity(ServerRequest request) {
        var cityName = request.pathVariable("name");
        return ServerResponse.ok().build(this.weatherService.deleteCity(cityName));
    }

    @NonNull
    public Mono<ServerResponse> getCityWeathers(ServerRequest request) {
        var cityName = request.queryParam("cityName").orElse(null);
        return ServerResponse.ok().body(this.weatherService.getCityWeathers(cityName), CityWeatherDTO.class);
    }
}
