package com.nhannhan159.weather.data.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.boot.autoconfigure.r2dbc.R2dbcProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tien.tan
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ConfigurationProperties(prefix = "spring.r2dbc")
public class R2dbcExtProperties extends R2dbcProperties {
    private String host = "localhost";
    private int port = 3306;
    private String database;
}
