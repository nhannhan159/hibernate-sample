package com.nhannhan159.weather.data.config;

import com.nhannhan159.weather.data.stream.OpenWeatherStream;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Configuration;

/**
 * @author tien.tan
 */
@Configuration
@EnableBinding(OpenWeatherStream.class)
public class StreamConfig {
}
