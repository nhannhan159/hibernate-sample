package com.nhannhan159.weather.data.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Primary;

/**
 * @author tien.tan
 */
@Data
@Primary
@ConfigurationProperties(prefix = "spring.r2dbc.ext")
public class R2dbcExtProperties {
    private String host = "localhost";
    private int port = 3306;
    private String database;
}
