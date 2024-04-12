//: sfg6lab.domain.model.Sfg6TaskThreadPoolProperties.java


package sfg6lab.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.context.annotation.PropertySource;

import java.time.Duration;
import java.time.temporal.ChronoUnit;


@EnableConfigurationProperties
@ConfigurationProperties("sfg6.task.thread-pool")
@PropertySource(
        value = "classpath:task-thread-pool.yml",
        factory = YamlPropertySourceFactory.class)
public record Sfg6TaskThreadPoolProperties(
        int coreSize,
        int maxSize,
        int queueCapacity,
        @DefaultValue({ "1" })
        @DurationUnit(ChronoUnit.SECONDS)
        Duration keepAlive,
        boolean daemonThread,
        String threadNamePrefix) {

    public int keepAliveSeconds() {
        return (int) keepAlive.getSeconds();
    }

}///:~