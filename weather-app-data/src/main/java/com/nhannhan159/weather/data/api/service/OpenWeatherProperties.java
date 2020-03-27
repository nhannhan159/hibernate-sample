package com.nhannhan159.weather.data.api.service;

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
}
