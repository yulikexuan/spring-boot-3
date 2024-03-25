//: sfg6lab.app.Sfg6ContextStartedEventListener.java


package sfg6lab.app;


import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;


@Slf4j
@Component
class Sfg6ContextStartedEventListener {

    @EventListener({ContextStartedEvent.class, ContextClosedEvent.class})
    public void onApplicationContextEvents(@NonNull ApplicationContextEvent event) {

        var time = LocalDateTime.ofInstant(Instant.ofEpochMilli(
                event.getTimestamp()), ZoneId.systemDefault());

        log.debug(">>> There was an {} raised at {} ",
                event.getClass().getSimpleName(), time.toString());
    }

}///:~