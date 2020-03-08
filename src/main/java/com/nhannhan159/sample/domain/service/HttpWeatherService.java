package com.nhannhan159.sample.domain.service;

import com.nhannhan159.openweather.api.OpenWeatherApiService;
import com.nhannhan159.sample.application.dto.CityDTO;
import com.nhannhan159.sample.application.dto.CityWeatherDTO;
import com.nhannhan159.sample.domain.converter.CityDtoConverter;
import com.nhannhan159.sample.domain.converter.CityWeatherApiConverter;
import com.nhannhan159.sample.domain.converter.CityWeatherDtoConverter;
import com.nhannhan159.sample.infrastructure.entity.CityDO;
import com.nhannhan159.sample.infrastructure.entity.CityWeatherDO;
import com.nhannhan159.sample.infrastructure.repository.CityRepository;
import com.nhannhan159.sample.infrastructure.repository.CityWeatherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author tien.tan
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class HttpWeatherService implements WeatherService {
    private final OpenWeatherApiService openWeatherApiService;
    private final CityDtoConverter cityDtoConverter;
    private final CityWeatherDtoConverter cityWeatherDtoConverter;
    private final CityWeatherApiConverter cityWeatherApiConverter;
    private final CityRepository cityRepository;
    private final CityWeatherRepository cityWeatherRepository;

    @Override
    public Mono<CityDTO> addCity(String cityName) {
        return Mono
            .just(this.cityRepository.save(new CityDO().setName(cityName)))
            .map(this.cityDtoConverter::convertBack);
    }

    @Override
    public Mono<Void> deleteCity(String cityName) {
        return Mono.fromRunnable(() -> this.cityRepository.delete(new CityDO().setName(cityName)));
    }

    @Override
    public Flux<CityDTO> getCities() {
        return Flux
            .fromIterable(this.cityRepository.findAll())
            .map(this.cityDtoConverter::convertBack);
    }

    @Override
    public Mono<CityWeatherDTO> getCityWeather(String cityName) {
        var ds = new SimpleDateFormat("yyyyMMDD").format(new Date());
        return this.getCityWeatherFromDb(cityName, ds)
            .switchIfEmpty(this.getCityWeatherFromApi(cityName));
    }

    @Override
    public Flux<CityWeatherDTO> getCityWeathers(String cityName) {
        var ds = new SimpleDateFormat("yyyyMMDD").format(new Date());
        var weatherFlux = this.getCityWeatherFromDb(cityName);
        var weatherTodayFlux = weatherFlux
            .take(1)
            .filter(p -> ds.equals(p.getDs()))
            .defaultIfEmpty(this.getCityWeatherFromApi(cityName).block());
        return Flux.merge(weatherTodayFlux, weatherFlux);
    }

    private Mono<CityWeatherDTO> getCityWeatherFromApi(String cityName) {
        return Mono
            .just(this.openWeatherApiService.fetchCityWeather(cityName))
            .map(this.cityWeatherApiConverter::convert)
            .doOnSuccess(this::saveCityWeatherToDb)
            .map(this.cityWeatherDtoConverter::convertBack);
    }

    private Mono<CityWeatherDTO> getCityWeatherFromDb(String cityName, String ds) {
        return Mono
            .just(this.cityWeatherRepository.findByCityNameIsLikeAndDs(cityName, ds))
            .map(this.cityWeatherDtoConverter::convertBack);
    }

    private Flux<CityWeatherDTO> getCityWeatherFromDb(String cityName) {
        return Flux
            .fromIterable(this.cityWeatherRepository.findByCityNameIsLikeOrderByDsDesc(cityName))
            .map(this.cityWeatherDtoConverter::convertBack);
    }

    private void saveCityWeatherToDb(CityWeatherDO cityWeatherDO) {
        this.cityWeatherRepository.save(cityWeatherDO);
    }

}
