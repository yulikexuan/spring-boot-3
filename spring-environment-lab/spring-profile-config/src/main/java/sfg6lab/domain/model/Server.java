//: sfg6lab.domain.model.Server.java


package sfg6lab.domain.model;


import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.convert.DurationUnit;
import org.springframework.boot.convert.PeriodUnit;
import org.springframework.util.unit.DataSize;
import org.springframework.util.unit.DataUnit;

import java.time.Duration;
import java.time.Period;
import java.time.temporal.ChronoUnit;


public record Server(
        @DefaultValue
        @DurationUnit(ChronoUnit.SECONDS)
        Duration sessionTimeout,
        @DefaultValue
        @PeriodUnit(ChronoUnit.DAYS)
        Period statisticPeriod,
        @DefaultValue
        @DataSizeUnit(DataUnit.MEGABYTES)
        DataSize maxFileSize) {

}///:~