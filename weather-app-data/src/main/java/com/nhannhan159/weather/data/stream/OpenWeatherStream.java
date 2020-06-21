package com.nhannhan159.weather.data.stream;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;

/**
 * @author tien.tan
 */
public interface OpenWeatherStream {
    String BULK_CITIES_IN = "bulkCitiesIn";
    String BULK_CITIES_OUT = "bulkCitiesOut";
    String BULK_WEATHERS_IN = "bulkWeathersIn";
    String BULK_WEATHERS_OUT = "bulkWeathersOut";

    @Input(BULK_CITIES_IN)
    SubscribableChannel bulkCitiesIn();

    @Input(BULK_WEATHERS_IN)
    SubscribableChannel bulkWeathersIn();

    @Output(BULK_CITIES_OUT)
    MessageChannel bulkCitiesOut();

    @Output(BULK_WEATHERS_OUT)
    MessageChannel bulkWeathersOut();
}
