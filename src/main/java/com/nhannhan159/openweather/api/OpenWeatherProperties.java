package com.nhannhan159.openweather.api;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tien.tan
 */
@Data
@ConfigurationProperties("com.nhannhan159.api.open-weather")
public class OpenWeatherProperties {
    private String url;
    private String appId;
}
