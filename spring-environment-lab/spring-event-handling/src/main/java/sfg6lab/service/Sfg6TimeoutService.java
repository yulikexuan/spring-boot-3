//: sfg6lab.service.Sfg6TimeoutService.java


package sfg6lab.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import sfg6lab.config.Sfg6ConfigurationProperties;


@Slf4j
@Service
public class Sfg6TimeoutService {

    @EventListener
    public void onTimeoutProperty(
            @NonNull Sfg6ConfigurationProperties timeoutProperty) {

        log.debug(">>> The current timeout is {} seconds",
                timeoutProperty.timeout().toSeconds());
    }

}///:~