package com.nhannhan159.weather.openweather.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tien.tan
 */
@Data
@ConfigurationProperties("com.nhannhan159.api.open-weather")
public class OpenWeatherProperties {
    private String url;
    private String bulkUrl;
    private String appId;
    private int connectTimeout = 100;
    private int readTimeout = 100;
    private int writeTimeout = 100;
    private int retryAttempt = 3;
}
