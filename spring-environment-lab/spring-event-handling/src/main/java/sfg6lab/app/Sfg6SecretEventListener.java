//: sfg6lab.app.Sfg6SecretEventListener.java


package sfg6lab.app;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.PayloadApplicationEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;


@Slf4j
@Component
class Sfg6SecretEventListener {

    @EventListener
    public void onSecretEvent(PayloadApplicationEvent<String> secretEvent) {
        log.debug(">>> Handling sfg6.secret event: {}", secretEvent.getPayload());
    }

}///:~