package com.nhannhan159.openweather.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tien.tan
 */
@Data
@ConfigurationProperties("com.nhannhan159.retrofit")
public class RetrofitProperties {
    private long connectTimeout;
}
