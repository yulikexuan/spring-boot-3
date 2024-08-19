//: sfg6lab.service.LoggingDemoService.java

package sfg6lab.service;


import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.stream.IntStream;


@Service
class LoggingDemoService {

    static final String DIRECT_LOGGING_MSG = ">>> ";
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(LoggingDemoService.class);

    public void doLogging() {

        log.info(">>> Logging in method doLogging() at {}", LocalDateTime.now());
    }

    public void doLoggingWithinLambda() {

        IntStream.range(0, 10).forEach(i ->
                log.debug(">>> Logging {} in lambda at {}", i, LocalDateTime.now()));
    }

} ///:~