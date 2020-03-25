package com.nhannhan159.weather.web.config;

import com.alibaba.rsocket.invocation.RSocketRemoteServiceBuilder;
import com.alibaba.rsocket.upstream.UpstreamManager;
import com.nhannhan159.weather.common.facade.WeatherFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tien.tan
 */
@Configuration
public class ServiceConsumeConfig {

    @Bean
    public WeatherFacade weatherFacade(UpstreamManager upstreamManager) {
        return RSocketRemoteServiceBuilder
            .client(WeatherFacade.class)
            .upstreamManager(upstreamManager)
            .build();
    }
}
