//: sfg6lab.service.Sfg6Retryable


package sfg6lab.service;


import org.springframework.retry.support.RetryTemplate;

import java.time.Duration;
import java.util.function.Supplier;


public interface Sfg6Retryable {

    int MAX_ATTEMPTS = 3;

    String DATA_URL = "https://jsonplaceholder.typicode.com/todos/1";

    String receive();

    String read();

    Supplier<RetryTemplate> RETRY_TEMPLATE_SUPPLIER =
            () -> RetryTemplate.builder()
                    .maxAttempts(3)
                    .retryOn(RetryableException.class)
                    .exponentialBackoff(
                            Duration.ofMillis(500), 2,
                            Duration.ofMillis(7000))
                    .build();
}///:~