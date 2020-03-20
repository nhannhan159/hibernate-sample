package com.nhannhan159.sample.application.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

/**
 * @author tien.tan
 */
@Configuration
public class RouterConfig {

    @Bean
    RouterFunction<ServerResponse> indexRoutes(@Value("classpath:/static/index.html") final Resource indexHtml) {
        return route(GET("/weather"), request -> ok().contentType(MediaType.TEXT_HTML).bodyValue(indexHtml));
    }

    @Bean
    RouterFunction<ServerResponse> apiRoutes(WeatherHandler weatherHandler) {
        return nest(GET("/api").and(accept(APPLICATION_JSON)),
            route(GET("/cities"), weatherHandler::getCities)
            .andRoute(GET("/cities/{name}"), weatherHandler::addCity)
            .andRoute(DELETE("/cities/{name}"), weatherHandler::deleteCity)
            .andRoute(GET("/cityWeathers"), weatherHandler::getCityWeathers));
    }
}
