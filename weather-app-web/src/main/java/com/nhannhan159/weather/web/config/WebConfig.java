package com.nhannhan159.weather.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author tien.tan
 */
@Configuration
public class WebConfig implements WebFluxConfigurer {

    /**
     * overriding SecurityWebFilterChain bean in ReactiveOAuth2ClientConfigurations,
     * dont use servlet filter
     */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.authorizeExchange()
            .pathMatchers("/static/**").permitAll()
            .anyExchange().authenticated();
        http.oauth2Login();
        http.oauth2Client();
        return http.build();
    }
}
