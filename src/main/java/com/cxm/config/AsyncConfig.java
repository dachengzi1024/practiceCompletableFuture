package com.cxm.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {
    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncExceptionHandler() ;
    }
    public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
        private final Logger logger = LoggerFactory.getLogger(AsyncExceptionHandler.class);

        @Override
        public void handleUncaughtException(Throwable ex, Method method, Object... params) {
            logger.error("Unexpected asynchronous exception at : " + method.getDeclaringClass().getName() + "." + method.getName(), ex);
        }
    }
    @Bean(name = "asyncExecutor")
    public Executor asyncExecutor() {
        int core = Runtime.getRuntime().availableProcessors();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(core);
        executor.setMaxPoolSize(core);
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("PackAsync-");
        executor.initialize();
        return executor;
    }
}
