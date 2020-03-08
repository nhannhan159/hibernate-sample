package com.nhannhan159.sample.application.controller;

import com.nhannhan159.sample.application.dto.CityDTO;
import com.nhannhan159.sample.application.dto.CityWeatherDTO;
import com.nhannhan159.sample.domain.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author tien.tan
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping(value = "/cities")
    public Flux<CityDTO> getCities() {
        return this.weatherService.getCities();
    }

    @GetMapping(value = "/cities/{name}")
    public Mono<CityDTO> addCity(@PathVariable String name) {
        return this.weatherService.addCity(name);
    }

    @GetMapping(value = "/cities/{name}")
    public Mono<Void> deleteCity(@PathVariable String name) {
        return this.weatherService.deleteCity(name);
    }

    @GetMapping(value = "/cityWeathers")
    public Flux<CityWeatherDTO> getCityWeathers(@RequestParam String cityName) {
        return this.weatherService.getCityWeathers(cityName);
    }
}
