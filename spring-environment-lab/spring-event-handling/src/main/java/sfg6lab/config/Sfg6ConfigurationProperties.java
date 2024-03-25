//: sfg6lab.config.Sfg6TimeoutConfigurationProperties.java


package sfg6lab.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.boot.convert.DurationUnit;

import java.time.Duration;
import java.time.temporal.ChronoUnit;


@ConfigurationProperties("sfg6")
public record Sfg6ConfigurationProperties(
        @DefaultValue
        String secret,
        @DefaultValue
        @DurationUnit(ChronoUnit.SECONDS)
        Duration timeout) {

}///:~