package com.nhannhan159.weather.data.service;

import org.springframework.cloud.stream.annotation.Input;
import org.springframework.cloud.stream.binder.PollableMessageSource;

/**
 * @author tien.tan
 */
public interface QueuePolledConsumer {
    String BULK_CITIES_IN = "bulkCitiesIn";
    String BULK_WEATHERS_IN = "bulkWeathersIn";

    @Input(BULK_CITIES_IN)
    PollableMessageSource bulkCitiesIn();

    @Input(BULK_WEATHERS_IN)
    PollableMessageSource bulkWeathersIn();
}
