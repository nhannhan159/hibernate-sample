package com.nhannhan159.weather.openweather.config;

import com.nhannhan159.weather.common.base.AppException;
import com.nhannhan159.weather.openweather.client.OpenWeatherBulkApiClient;
import com.nhannhan159.weather.openweather.exception.OpenWeatherErrorDecoder;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.codec.ErrorDecoder;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author tien.tan
 */
@RequiredArgsConstructor
@Configuration
@EnableFeignClients(
    defaultConfiguration = OpenWeatherConfig.class,
    basePackages = "com.nhannhan159.weather.openweather.client")
@EnableConfigurationProperties(OpenWeatherProperties.class)
public class OpenWeatherConfig {
    private final RetryRegistry retryRegistry;
    private final OpenWeatherProperties properties;

    @PostConstruct
    public void init() {
        this.retryRegistry.retry("open-weather", this.retryConfig());
    }

    @Bean
    ErrorDecoder errorDecoder() {
        return new OpenWeatherErrorDecoder();
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.HEADERS;
    }

    @Bean
    Request.Options requestOptions() {
        return new Request.Options(
            properties.getConnectTimeout(),
            TimeUnit.SECONDS,
            properties.getReadTimeout(),
            TimeUnit.SECONDS,
            false);
    }

    @Bean
    OpenWeatherBulkApiClient openWeatherBulkApiClient() {
        return Feign.builder()
            .options(requestOptions())
            .target(OpenWeatherBulkApiClient.class, properties.getBulkUrl());
    }

    @Bean
    WebClientCustomizer webClientCustomizer() {
        return webClientBuilder -> {
            var tcpClient = TcpClient.create()
                .wiretap(true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, properties.getConnectTimeout() * 1000)
                .doOnConnected(connection ->
                    connection.addHandlerLast(new LoggingHandler(LogLevel.TRACE))
                        .addHandlerLast(new ReadTimeoutHandler(properties.getReadTimeout()))
                        .addHandlerLast(new WriteTimeoutHandler(properties.getWriteTimeout())));
            var httpConnector = new ReactorClientHttpConnector(HttpClient.from(tcpClient));
            webClientBuilder.clientConnector(httpConnector);
        };
    }

    @Bean
    RetryConfig retryConfig() {
        return RetryConfig.custom()
            .retryExceptions(AppException.class, IOException.class)
            .maxAttempts(properties.getRetryAttempt()).build();
    }
}
