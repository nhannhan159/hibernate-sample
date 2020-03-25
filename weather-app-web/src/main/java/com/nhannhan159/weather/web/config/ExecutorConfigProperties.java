package com.nhannhan159.weather.web.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * check this document: https://www.jianshu.com/p/44e6d04c7910
 *     private static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() *2;
 *     private static final int MAX_POOL_SIZE = CORE_POOL_SIZE *4 <256 ? 256 : CORE_POOL_SIZE * 4;
 *     private static final int KEEP_ALIVE_TIME = 10; //允许线程空闲时间（单位为秒）
 *     private static final int QUEUE_CAPACITY = 200; // 缓冲队列数
 *     private static final int AWAIT_TERMINATION = 60;//线程池中任务的等待时间，如果超过这个时候还没有销毁就强制销毁
 *     private static final Boolean WAIT_FOR_TASKS_TO_COMPLETE_ON_SHUTDOWN = true;//用来设置线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean
 *     private static final String THREAD_NAME_PREFIX = "PiceaAsync-Service-"; // 线程池名前缀
 *
 * @author tien.tan
 */
@Data
@ConfigurationProperties("com.nhannhan159.config.executor")
public class ExecutorConfigProperties {
    private int corePoolSize = 2;
    private int maximumPoolSize = 4;
    private int keepAliveTime = 60;
    private int maxQueueSize = 40;
    private int awaitTermination = 60;
    private boolean waitForTasksToCompleteOnShutdown = true;
    private String threadNamePrefix = "com-nhannhan159-thread-";
}
