package com.nhannhan159.weather.data.api.service;

import com.google.gson.Gson;
import com.nhannhan159.weather.data.api.model.City;
import com.nhannhan159.weather.data.api.model.CityWeather;
import com.nhannhan159.weather.data.util.ResourceUtils;
import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.MediaType;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import javax.annotation.PostConstruct;

/**
 * @author tien.tan
 */
@Slf4j
@RequiredArgsConstructor
@Component
@EnableConfigurationProperties(OpenWeatherProperties.class)
public class OpenWeatherApiService {
    private static final String BULK_CITIES = "city.list.json.gz";
    private static final String BULK_WEATHERS = "weather_14.json.gz";
    private static final String APP_ID = "appid";
    private final OpenWeatherProperties properties;
    private WebClient client;
    private WebClient bulkClient;

    @PostConstruct
    public void init() {
        this.client = this.webClient(this.properties.getUrl());
        this.bulkClient = this.webClient(this.properties.getBulkUrl());
    }

    public Mono<CityWeather> fetchCityWeather(String cityName) {
        return this.client.get()
            .uri(uriBuilder -> uriBuilder
                .queryParam(APP_ID, this.properties.getAppId())
                .queryParam("q", cityName)
                .build())
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(CityWeather.class);
    }

    public Flux<City> fetchBulkCities() {
        return this.fetchResource(BULK_CITIES)
            .publishOn(Schedulers.boundedElastic())
            .flatMapMany(resource -> ResourceUtils.readLinesFromByteArrayResource(resource, true))
            .map(line -> new Gson().fromJson(line, City.class));
    }

    public Flux<CityWeather> fetchBulkWeathers() {
        return this.fetchResource(BULK_WEATHERS)
            .publishOn(Schedulers.boundedElastic())
            .flatMapMany(resource -> ResourceUtils.readAllLinesFromByteArrayResource(resource, true))
            .map(line -> new Gson().fromJson(line, CityWeather.class));
    }

    private Mono<ByteArrayResource> fetchResource(String resourceName) {
        return this.bulkClient.get()
            .uri(uriBuilder -> uriBuilder
                .queryParam(APP_ID, this.properties.getAppId())
                .build(resourceName))
            .accept(MediaType.TEXT_PLAIN)
            .retrieve()
            .bodyToMono(ByteArrayResource.class);
    }

    public void fetchResourceTest(String resourceName) {
        this.bulkClient.get()
            .uri(uriBuilder -> uriBuilder
                .queryParam(APP_ID, this.properties.getAppId())
                .build(resourceName))
            .accept(MediaType.TEXT_PLAIN)
            .retrieve()
            .bodyToMono(String.class)
            .subscribe(log::info);
    }

    private WebClient webClient(String baseUrl) {
        var tcpClient = TcpClient.create()
            .wiretap(true)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 100000)
            .doOnConnected(connection ->
                connection.addHandlerLast(new LoggingHandler(LogLevel.TRACE))
                    .addHandlerLast(new ReadTimeoutHandler(100))
                    .addHandlerLast(new WriteTimeoutHandler(100)));
        var httpConnector = new ReactorClientHttpConnector(HttpClient.from(tcpClient));
        return WebClient.builder()
            .clientConnector(httpConnector)
            .baseUrl(baseUrl)
            .build();
    }
}