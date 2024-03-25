package sfg6lab.config;


import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.concurrent.TimeUnit;


public record Server(
        int port,
        int maxHttpRequestHeaderSize,
        String homePage,
        @DefaultValue
        @DurationUnit(ChronoUnit.SECONDS)
        Duration sessionTimeout,
        @DefaultValue
        @PeriodUnit(ChronoUnit.DAYS)
        Period statisticPeriod,
        @DefaultValue
        @DataSizeUnit(DataUnit.MEGABYTES)
        DataSize maxFileSize,
        @DefaultValue
        List<Integer> portsToTest,
        TimeUnit repetitionUnit,
        List<String> ips,
        List<Location> locations) {
}