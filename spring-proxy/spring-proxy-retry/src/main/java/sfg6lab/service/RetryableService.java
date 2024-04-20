//: sfg6lab.service.RandomPhotoService.java


package sfg6lab.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;


@Slf4j
@Service
public class RetryableService implements Sfg6Retryable {

    private int attemptCounter = 0;

    @Override
    @Retryable(
            maxAttempts = 4,
            retryFor = { RetryableException.class },
            backoff = @Backoff(delay = 2000)
    )
    public String receive() {

        log.debug(">>> Attempts: {}", ++this.attemptCounter);

        if (attemptCounter <= MAX_ATTEMPTS) {
            throw new RetryableException(">>> Not yet ready to serve.");
        }

        try (var inputStream = URI.create(DATA_URL).toURL().openStream()) {
            return StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RetryableException(e);
        }
    }

    @Override
    @Retryable(
            maxAttempts = 4,
            retryFor = { RetryableException.class },
            backoff = @Backoff(delay = 1500))
    public String read() {
        throw new RetryableException(">>> Nothing to read.");
    }

    @Recover
    public String recoverReading(RetryableException retryableException) {
        log.error(">>> Recovering reading error {}", retryableException.getMessage());
        return "Nothing has been read, sorry!";
    }

    public int attemptCounter() {
        return this.attemptCounter;
    }

    public int retriedTimes() {
        return this.attemptCounter - 1;
    }

}///:~