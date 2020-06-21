package com.nhannhan159.weather.data.service;

import com.nhannhan159.weather.data.stream.OpenWeatherStream;
import com.nhannhan159.weather.openweather.client.OpenWeatherBulkApiClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * @author tien.tan
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class TaskService {
    private final OpenWeatherBulkApiClient openWeatherApiClient;

    @Autowired
    @Qualifier(OpenWeatherStream.BULK_CITIES_OUT)
    private MessageChannel bulkCitiesOutChannel;

    @Autowired
    @Qualifier(OpenWeatherStream.BULK_CITIES_OUT)
    private MessageChannel bulkWeathersOutChannel;

    //@Scheduled(cron = "0 0 0 1 1/1 *")
    @Scheduled(fixedRate = 50000)
    public void scheduleFetchBulkCities() {
        this.openWeatherApiClient.fetchBulkCities()
            .subscribe(payload -> {
                var message = MessageBuilder.withPayload(payload).build();
                this.bulkCitiesOutChannel.send(message);
            });
    }

    //@Scheduled(cron = "0 0 0 1 1/1 *")
    @Scheduled(fixedRate = 50000)
    public void scheduleFetchBulkWeathers() {
        this.openWeatherApiClient.fetchBulkWeathers()
            .subscribe(payload -> {
                var message = MessageBuilder.withPayload(payload).build();
                this.bulkCitiesOutChannel.send(message);
            });
    }
}
