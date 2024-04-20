//: sfg6lab.service.RetryTemplateService.java


package sfg6lab.service;


import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@AllArgsConstructor
public class RetryTemplateService implements Sfg6Retryable {

    private final RetryTemplate retryTemplate;

    @Override
    public String receive() {
        return this.retryTemplate.execute(
                ctx -> read(),
                ctx -> ">>> Recovering receiving error: " +
                        ctx.getLastThrowable().getMessage());
    }

    @Override
    public String read() {
        log.debug(">>> Retry reading ...");
        throw new RetryableException(">>> There is nothing to read yet.");
    }

}///:~