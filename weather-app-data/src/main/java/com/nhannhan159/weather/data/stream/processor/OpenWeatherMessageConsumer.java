package com.nhannhan159.weather.data.stream.processor;

import com.nhannhan159.weather.data.converter.CityApiConverter;
import com.nhannhan159.weather.data.converter.CityWeatherApiConverter;
import com.nhannhan159.weather.data.repository.jpa.CityWeatherRepository;
import com.nhannhan159.weather.data.repository.reactive.ReactiveCityRepository;
import com.nhannhan159.weather.data.stream.OpenWeatherStream;
import com.nhannhan159.weather.openweather.model.City;
import com.nhannhan159.weather.openweather.model.CityWeather;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author tien.tan
 */
@Slf4j
@RequiredArgsConstructor
@Service
@Transactional(transactionManager = "transactionManager", rollbackFor = Exception.class)
public class OpenWeatherMessageConsumer {
    private final CityApiConverter cityConverter;
    private final CityWeatherApiConverter cityWeatherConverter;
    private final ReactiveCityRepository cityRepository;
    private final CityWeatherRepository cityWeatherRepository;

    @StreamListener(OpenWeatherStream.BULK_CITIES_IN)
    public void consumeBulkCites(City city) {
        this.cityRepository.save(this.cityConverter.convert(city));
    }

    @StreamListener(OpenWeatherStream.BULK_WEATHERS_IN)
    public void consumeBulkCityWeathers(CityWeather cityWeather) {
        this.cityWeatherRepository.save(this.cityWeatherConverter.convert(cityWeather));
    }
}
