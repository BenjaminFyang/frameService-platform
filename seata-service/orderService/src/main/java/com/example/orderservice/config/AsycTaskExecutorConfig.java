package com.example.orderservice.config;

import com.central.common.config.DefaultAsycTaskConfig;
import org.springframework.context.annotation.Configuration;

/**
 * @author fy
 * 线程池配置、启用异步
 */
@Configuration
public class AsycTaskExecutorConfig extends DefaultAsycTaskConfig {

//
//    @Bean(value = "consumerQueueThreadPool")
//    public ExecutorService buildConsumerQueueThreadPool() {
//
//        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().
//                setNameFormat(getThreadNamePrefix())
//                .build();
//
//        CustomThreadPoolTaskExecutor customThreadPoolTaskExecutor = new CustomThreadPoolTaskExecutor();
//
//        ExecutorService executorService = buildExecutor(namedThreadFactory);
//        return TtlExecutors.getTtlExecutorService(executorService);
//    }
//
//
//    private ExecutorService buildExecutor(ThreadFactory namedThreadFactory) {
//
//        // 通过ThreadPoolExecutor构造函数自定义参数创建
//        // 当任务添加到线程池中被拒绝时，会在线程池当前正在运行的Thread线程池中处理被拒绝的任务。
//        return CustomThreadPoolTaskExecutor.create().setCorePoolSize(getCorePoolSize())
//                .setMaxPoolSize(getMaxPoolSize())
//                .setKeepAliveTime(3L, TimeUnit.MINUTES)
//                .setWorkQueue(new ArrayBlockingQueue<>(getQueueCapacity()))
//                .setThreadFactory(namedThreadFactory)
//                .setHandler(new ThreadPoolExecutor.AbortPolicy())
//                .build();
//
//    }

}
