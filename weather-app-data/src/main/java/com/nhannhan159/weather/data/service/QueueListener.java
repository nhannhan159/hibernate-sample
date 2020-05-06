package com.nhannhan159.weather.data.service;

import com.nhannhan159.weather.data.api.model.City;
import com.nhannhan159.weather.data.api.model.CityWeather;
import com.nhannhan159.weather.data.repository.jpa.CityWeatherRepository;
import com.nhannhan159.weather.data.repository.reactive.ReactiveCityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author tien.tan
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class QueueListener {
    private final QueuePolledConsumer consumer;
    private final ReactiveCityRepository cityRepository;
    private final CityWeatherRepository cityWeatherRepository;

    @Scheduled(fixedDelay = 5000)
    public void consumeBulkCites() {
        boolean result;
        do {
            result = this.consumer.bulkCitiesIn().poll(msg -> {
                var payload = (City) msg.getPayload();
                log.info("PollableConsumer.receive payload: {}", payload);
            }, new ParameterizedTypeReference<City>() {});
        } while (result);
    }

    @Scheduled(fixedDelay = 5000)
    public void consumeBulkWeathers() {
        boolean result;
        do {
            result = this.consumer.bulkCitiesIn().poll(msg -> {
                var payload = (CityWeather) msg.getPayload();
                log.info("PollableConsumer.receive payload: {}", payload);
            }, new ParameterizedTypeReference<CityWeather>() {});
        } while (result);
    }
}
