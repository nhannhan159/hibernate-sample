package com.nhannhan159.weather.data.config;

import com.nhannhan159.weather.data.api.model.City;
import com.nhannhan159.weather.data.api.model.CityWeather;
import com.nhannhan159.weather.data.service.QueueListener;
import com.nhannhan159.weather.data.service.QueuePolledConsumer;
import com.nhannhan159.weather.data.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.function.context.PollableBean;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

/**
 * @author tien.tan
 */
@RequiredArgsConstructor
@Configuration
@EnableBinding({ QueuePolledConsumer.class })
public class QueueBindingConfig {
    private final TaskService taskService;

    @PollableBean
    public Supplier<Flux<City>> bulkCitiesOut() {
        return taskService::getBulkCitiesMessage;
    }

    @PollableBean
    public Supplier<Flux<CityWeather>> bulkWeathersOut() {
        return taskService::getBulkWeathersMessage;
    }
}
