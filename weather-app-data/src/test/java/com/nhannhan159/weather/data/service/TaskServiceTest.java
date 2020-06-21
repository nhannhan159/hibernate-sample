package com.nhannhan159.weather.data.service;

import com.nhannhan159.weather.openweather.client.OpenWeatherApiClient;
import com.nhannhan159.weather.openweather.client.OpenWeatherBulkApiClient;
import com.nhannhan159.weather.openweather.model.City;
import com.nhannhan159.weather.openweather.model.Coord;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * @author tien.tan
 */
@Slf4j
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskServiceTest {

    @Autowired
    private OpenWeatherBulkApiClient openWeatherApiClient;

    @Test
    public void api_getBulkCities_success() {
        var city1 = new City().setId(833L).setName("Ḩeşār-e Sefīd").setCountry("IR").setCoord(new Coord(47.159401, 34.330502));
        var city2 = new City().setId(2960L).setName("‘Ayn Ḩalāqīm").setCountry("SY").setCoord(new Coord(36.321911, 34.940079));
        var source = this.openWeatherApiClient.fetchBulkCities();
        StepVerifier.create(source)
            .expectNext(city1)
            .expectNext(city2)
            .expectNextCount(209577)
            .expectComplete()
            .verify();
    }

    @Test
    public void api_getBulkCityWeather_success() {
        var source = this.openWeatherApiClient.fetchBulkWeathers();
        StepVerifier.create(source)
            .expectNextCount(22635)
            .expectComplete()
            .verify();
    }

    public <T> Flux<T> appendBoomError(Flux<T> source) {
        return source.concatWith(Mono.error(new IllegalArgumentException("boom")));
    }
}
