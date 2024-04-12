//: sfg6lab.config.SpringEnvironmentConfig.java


package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import sfg6lab.domain.service.SleepAndDreamService;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Executor;


@Slf4j
@EnableAsync
@EnableAutoConfiguration
@SpringBootConfiguration
@ComponentScan(
        //Indicates whether automatic detection of classes annotated with
        // @Component @Repository, @Service, or @Controller should be enabled
        useDefaultFilters = false,
        includeFilters = @ComponentScan.Filter({
                Component.class,
                Controller.class,
                Service.class }),
        basePackages = { "sfg6lab.service", "sfg6lab.domain.service"})
@ConfigurationPropertiesScan(basePackages = { "sfg6lab.config" })
public class Sfg6AppCfg implements AsyncConfigurer {

    @Autowired
    Sfg6TaskThreadPoolProperties threadPoolProperties;

    @Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {

        final var taskExecutor = new ThreadPoolTaskExecutor();

        taskExecutor.setCorePoolSize(threadPoolProperties.coreSize());
        taskExecutor.setMaxPoolSize(threadPoolProperties.maxSize());
        taskExecutor.setQueueCapacity(threadPoolProperties.queueCapacity());
        taskExecutor.setKeepAliveSeconds(threadPoolProperties.keepAliveSeconds());
        taskExecutor.setThreadNamePrefix(threadPoolProperties.threadNamePrefix());
        taskExecutor.setDaemon(threadPoolProperties.daemonThread());

        return taskExecutor;
    }

    @Bean
    SleepAndDreamService sleepAndDreamService() {
        return SleepAndDreamService.of();
    }

    @Override
    public Executor getAsyncExecutor() {
        return threadPoolTaskExecutor();
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new AsyncUncaughtExceptionHandler() {
            @Override
            public void handleUncaughtException(
                    Throwable throwable, Method method, Object... params) {
                log.error(">>> Error from a task is {}",
                        Arrays.toString(throwable.getStackTrace()));
            }
        };
    }

}///:~

