package com.nhannhan159.sample.infrastructure.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author tien.tan
 */
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(ExecutorConfigProperties.class)
public class ExecutorConfig {

    private final ExecutorConfigProperties properties;

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(
            properties.getCorePoolSize(),
            properties.getMaximumPoolSize(),
            properties.getKeepAliveTime(),
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(properties.getMaxQueueSize()),
            new ThreadFactoryBuilder().setNameFormat("logistics-pm-thread-%d").build());
    }

    @Bean
    public ExecutorService executorService() {
        return TtlExecutors.getTtlExecutorService(threadPoolExecutor());
    }

}
