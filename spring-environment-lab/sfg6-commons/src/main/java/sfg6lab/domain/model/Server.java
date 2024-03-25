//: sfg6lab.domain.model.Server.java


package sfg6lab.domain.model;


import org.springframework.util.unit.DataSize;

import java.time.Duration;
import java.time.Period;
import java.util.List;
import java.util.concurrent.TimeUnit;


public record Server(
        int port,
        int maxHttpRequestHeaderSize,
        String homePage,
        Duration sessionTimeout,
        Period statisticPeriod,
        DataSize maxFileSize,
        List<Integer> portsToTest,
        TimeUnit repetitionUnit,
        List<String> ips,
        List<Location> locations) {

}///:~