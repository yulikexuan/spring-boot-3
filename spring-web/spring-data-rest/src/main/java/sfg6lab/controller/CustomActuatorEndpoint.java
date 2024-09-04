//: sfg6lab.controller.CustomActuatorEndpoint.java

package sfg6lab.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Slf4j
@Component
@Endpoint(id = "custom")
public class CustomActuatorEndpoint {

    @ReadOperation
    public ResponseEntity<?> randomUuid() {
        return ResponseEntity.ok(UUID.randomUUID().toString());
    }

    @WriteOperation
    public void writeValue(String value) {
        log.info(">>> Writing value : '{}'", value);
    }

} ///:~