package com.nhannhan159.sample.infrastructure.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author tien.tan
 */
@Data
@ConfigurationProperties("lazada.logistics.pm.executor")
public class ExecutorConfigProperties {
    private int corePoolSize = 2;
    private int maximumPoolSize = 4;
    private long keepAliveTime = 60;
    private int maxQueueSize = 40;
}
