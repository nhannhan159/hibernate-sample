package com.nhannhan159.weather.data.config;

import com.alibaba.rsocket.upstream.UpstreamManager;
import com.alibaba.spring.boot.rsocket.RSocketBrokerHealthIndicator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author tien.tan
 */
@Configuration
public class RSocketConfig {

    @Bean
    @Primary
    public RSocketBrokerHealthIndicator primaryRsocketBrokerHealth(UpstreamManager upstreamManager,
                                                                   @Value("${rsocket.brokers}") String brokers) {
        return new RSocketBrokerHealthIndicator(upstreamManager, brokers);
    }
}
