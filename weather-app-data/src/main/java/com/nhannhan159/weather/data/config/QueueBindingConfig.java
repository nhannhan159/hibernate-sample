package com.nhannhan159.weather.data.config;

import com.nhannhan159.weather.data.api.model.City;
import com.nhannhan159.weather.data.api.model.CityWeather;
import com.nhannhan159.weather.data.service.QueueListener;
import com.nhannhan159.weather.data.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author tien.tan
 */
@RequiredArgsConstructor
@Configuration
public class QueueBindingConfig {
    private final TaskService taskService;
    private final QueueListener queueListener;

    @PollableBean
    public Supplier<Flux<City>> bulkCitiesOut() {
        return taskService::getBulkCitiesMessage;
    }

    @PollableBean
    public Supplier<Flux<CityWeather>> bulkWeathersOut() {
        return taskService::getBulkWeathersMessage;
    }

    @Bean
    public Consumer<Flux<City>> bulkCitiesIn() {
        return queueListener::consumeBulkCites;
    }

    @Bean
    public Consumer<Flux<CityWeather>> bulkWeathersIn() {
        return queueListener::consumeBulkWeathers;
    }
}
