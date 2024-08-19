//: sfg6lab.controller.AsyncController.java

package sfg6lab.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
@RequestMapping("/async")
class AsyncController {

    @GetMapping("/callable")
    public Callable<ResponseEntity<?>> callableAsync() {
        return () -> {
            log.info(">>> The worker thead: {}", Thread.currentThread().getName());
            TimeUnit.SECONDS.sleep(10);
            return ResponseEntity.ok(">>> Sorry, I am late.");
        };
    }

} ///:~