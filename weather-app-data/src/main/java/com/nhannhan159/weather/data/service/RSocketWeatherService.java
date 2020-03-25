package com.nhannhan159.weather.data.service;

import com.alibaba.rsocket.RSocketService;
import com.nhannhan159.weather.common.dto.CityDTO;
import com.nhannhan159.weather.common.dto.CityWeatherDTO;
import com.nhannhan159.weather.common.facade.WeatherFacade;
import com.nhannhan159.weather.common.util.ReactiveWrapper;
import com.nhannhan159.weather.data.api.service.OpenWeatherApiService;
import com.nhannhan159.weather.data.converter.CityDtoConverter;
import com.nhannhan159.weather.data.converter.CityWeatherApiConverter;
import com.nhannhan159.weather.data.converter.CityWeatherDtoConverter;
import com.nhannhan159.weather.data.entity.CityDO;
import com.nhannhan159.weather.data.entity.CityWeatherDO;
import com.nhannhan159.weather.data.repository.jpa.CityWeatherRepository;
import com.nhannhan159.weather.data.repository.reactive.ReactiveCityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tien.tan
 */
@Slf4j
@RequiredArgsConstructor
@RSocketService(serviceInterface = WeatherFacade.class)
@Service
public class RSocketWeatherService implements WeatherFacade {
    private final OpenWeatherApiService openWeatherApiService;
    private final CityDtoConverter cityDtoConverter;
    private final CityWeatherDtoConverter cityWeatherDtoConverter;
    private final CityWeatherApiConverter cityWeatherApiConverter;
    private final ReactiveCityRepository cityRepository;
    private final CityWeatherRepository cityWeatherRepository;

    @Override
    public Mono<CityDTO> addCity(String cityName) {
        return this.cityRepository.save(new CityDO().setName(cityName))
            .map(this.cityDtoConverter::convertBack);
    }

    @Override
    public Mono<Void> deleteCity(String cityName) {
        return this.cityRepository.delete(new CityDO().setName(cityName));
    }

    @Override
    public Flux<CityDTO> getCities() {
        return this.cityRepository.findAll()
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
            .switchIfEmpty(this.getCityWeatherFromApi(cityName));
        return Flux.merge(weatherTodayFlux, weatherFlux);
    }

    private Mono<CityWeatherDTO> getCityWeatherFromApi(String cityName) {
        return this.openWeatherApiService.fetchCityWeather(cityName)
            .map(this.cityWeatherApiConverter::convert)
            .doOnSuccess(this::saveCityWeatherToDb)
            .map(this.cityWeatherDtoConverter::convertBack);
    }

    private Mono<CityWeatherDTO> getCityWeatherFromDb(String cityName, String ds) {
        return ReactiveWrapper.toMono(() -> this.cityWeatherRepository.findByCityNameIsLikeAndDs(cityName, ds))
            .map(this.cityWeatherDtoConverter::convertBack);
    }

    private Flux<CityWeatherDTO> getCityWeatherFromDb(String cityName) {
        return ReactiveWrapper.toFlux(() -> this.cityWeatherRepository.findByCityNameIsLikeOrderByDsDesc(cityName))
            .map(this.cityWeatherDtoConverter::convertBack);
    }

    private void saveCityWeatherToDb(CityWeatherDO cityWeatherDO) {
        this.cityWeatherRepository.save(cityWeatherDO);
    }

}
