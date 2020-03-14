package com.nhannhan159.sample.infrastructure.config;

import com.alibaba.ttl.threadpool.TtlExecutors;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author tien.tan
 */
@RequiredArgsConstructor
@Configuration
@EnableConfigurationProperties(ExecutorConfigProperties.class)
public class ExecutorConfig implements AsyncConfigurer {
    private final ExecutorConfigProperties properties;

    @Override
    public Executor getAsyncExecutor() {
        return simpleThreadPoolTaskExecutor();
    }

    @Bean
    @Primary
    Executor simpleThreadPoolTaskExecutor() {
        var taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(properties.getCorePoolSize());
        taskExecutor.setMaxPoolSize(properties.getMaximumPoolSize());
        taskExecutor.setKeepAliveSeconds(properties.getKeepAliveTime());
        taskExecutor.setQueueCapacity(properties.getMaxQueueSize());
        taskExecutor.setThreadNamePrefix(properties.getThreadNamePrefix());
        taskExecutor.setWaitForTasksToCompleteOnShutdown(properties.isWaitForTasksToCompleteOnShutdown());
        taskExecutor.setAwaitTerminationSeconds(properties.getAwaitTermination());
        // 线程池对拒绝任务的处理策略
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        // 初始化
        taskExecutor.initialize();
        return TtlExecutors.getTtlExecutor(taskExecutor);
    }
}
