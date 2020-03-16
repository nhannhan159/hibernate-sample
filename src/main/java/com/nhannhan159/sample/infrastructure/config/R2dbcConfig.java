package com.nhannhan159.sample.infrastructure.config;

import dev.miku.r2dbc.mysql.MySqlConnectionConfiguration;
import dev.miku.r2dbc.mysql.MySqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * @author tien.tan
 */
@Configuration
@EnableR2dbcRepositories("com.nhannhan159.sample.infrastructure.repository.reactive")
@EnableConfigurationProperties(R2dbcExtProperties.class)
public class R2dbcConfig extends AbstractR2dbcConfiguration {

    @Autowired
    private R2dbcProperties properties;

    @Autowired
    private R2dbcExtProperties extProperties;

    @NotNull
    @Override
    public ConnectionFactory connectionFactory() {
        return MySqlConnectionFactory.from(MySqlConnectionConfiguration.builder()
            .host(extProperties.getHost())
            .port(extProperties.getPort())
            .username(properties.getUsername())
            .password(properties.getPassword())
            .database(extProperties.getDatabase())
            .build());
    }
}
