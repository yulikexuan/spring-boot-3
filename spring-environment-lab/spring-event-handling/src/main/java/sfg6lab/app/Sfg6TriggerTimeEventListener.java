//: sfg6lab.app.Sfg6EncodedSecretEventListener.java


package sfg6lab.app;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Slf4j
@Component
class Sfg6TriggerTimeEventListener {

    @EventListener
    public void onEncodedSecretEvent(
            PayloadApplicationEvent<LocalDateTime> event) {

        log.debug(">>> The next trigger time is {}", event.getPayload());
    }

}///:~