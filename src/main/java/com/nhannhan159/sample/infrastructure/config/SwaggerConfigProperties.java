package com.nhannhan159.sample.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 *  TODO: just testing, not finish
 *
 * @author tien.tan
 */
@Data
@ConfigurationProperties("com.nhannhan159.config.swagger")
public class SwaggerConfigProperties {
    private String apiTitle = "Weather sample api";
    private String apiDescription = "sample api";
    private String apiVersion = "1.0.0";
    private int maxQueueSize = 40;
}
