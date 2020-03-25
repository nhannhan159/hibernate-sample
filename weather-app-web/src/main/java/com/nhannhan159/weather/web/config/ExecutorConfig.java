package com.nhannhan159.weather.web.config;

import lombok.RequiredArgsConstructor;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.aop.interceptor.SimpleAsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.sleuth.instrument.async.LazyTraceExecutor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author tien.tan
 */
@RequiredArgsConstructor
@Configuration
@EnableAsync
@EnableScheduling
@EnableConfigurationProperties(ExecutorConfigProperties.class)
public class ExecutorConfig extends AsyncConfigurerSupport implements SchedulingConfigurer {
    private final ExecutorConfigProperties properties;
    private final BeanFactory beanFactory;

    @Override
    public Executor getAsyncExecutor() {
        return simpleThreadPoolTaskExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new SimpleAsyncUncaughtExceptionHandler();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.setScheduler(schedulingExecutor());
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
        return new LazyTraceExecutor(beanFactory, taskExecutor);
    }

    @Bean(destroyMethod = "shutdown")
    Executor schedulingExecutor() {
        return Executors.newScheduledThreadPool(properties.getCorePoolSize());
    }
}
