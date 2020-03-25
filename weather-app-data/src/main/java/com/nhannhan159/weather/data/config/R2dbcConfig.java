package com.nhannhan159.weather.data.config;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * @author tien.tan
 */
@Configuration
@EnableR2dbcRepositories("com.nhannhan159.weather.data.repository.reactive")
@EnableConfigurationProperties(R2dbcExtProperties.class)
public class R2dbcConfig extends AbstractR2dbcConfiguration {

    @Autowired
    private R2dbcExtProperties properties;

    @Bean
    @Primary
    public R2dbcExtProperties r2dbcProperties() {
        return new R2dbcExtProperties();
    }

    @NotNull
    @Override
    public ConnectionFactory connectionFactory() {
        return MySqlConnectionFactory.from(MySqlConnectionConfiguration.builder()
            .host(properties.getHost())
            .port(properties.getPort())
            .username(properties.getUsername())
            .password(properties.getPassword())
            .database(properties.getDatabase())
            .build());
    }
}
