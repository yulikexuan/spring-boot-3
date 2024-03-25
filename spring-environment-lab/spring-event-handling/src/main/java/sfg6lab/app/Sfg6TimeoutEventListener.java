//: sfg6lab.app.Sfg6TimeoutEventListener.java


package sfg6lab.app;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;


@Slf4j
@Component
class Sfg6TimeoutEventListener {

    @EventListener
    public PayloadApplicationEvent<LocalDateTime> onTimeoutEvent(
            PayloadApplicationEvent<Duration> timeoutEvent) {

        long timeoutInSecs = timeoutEvent.getPayload().toSeconds();
        log.debug(">>> Handling sfg6.timeout event: {}", timeoutInSecs);

        var newDataTime =  LocalDateTime.now().plusSeconds(timeoutInSecs);

        return new PayloadApplicationEvent<LocalDateTime>(
                this, newDataTime);
    }

}///:~