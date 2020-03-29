package com.nhannhan159.weather.data.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.client.RestTemplateCustomizer;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

/**
 * @author tien.tan
 */
@Configuration
public class WebClientConfig {

    @Bean
    WebClientCustomizer webClientCustomizer() {
        return webClientBuilder -> {
            var tcpClient = TcpClient.create()
                .wiretap(true)
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 100000)
                .doOnConnected(connection ->
                    connection.addHandlerLast(new LoggingHandler(LogLevel.TRACE))
                        .addHandlerLast(new ReadTimeoutHandler(100))
                        .addHandlerLast(new WriteTimeoutHandler(100)));
            var httpConnector = new ReactorClientHttpConnector(HttpClient.from(tcpClient));
            webClientBuilder.clientConnector(httpConnector);
        };
    }

    @Bean
    RestTemplateBuilder restTemplateBuilder() {
        return new RestTemplateBuilder(customRestTemplateCustomizer());
    }

    @Bean
    RestTemplateCustomizer customRestTemplateCustomizer() {
        return restTemplate -> {
            var requestFactory = restTemplate.getRequestFactory();
            if (requestFactory instanceof SimpleClientHttpRequestFactory) {
                var simpleRequestFactory = (SimpleClientHttpRequestFactory) requestFactory;
                simpleRequestFactory.setConnectTimeout(100000);
                simpleRequestFactory.setReadTimeout(100000);
            }
            restTemplate.setRequestFactory(new BufferingClientHttpRequestFactory(requestFactory));
        };
    }
}
