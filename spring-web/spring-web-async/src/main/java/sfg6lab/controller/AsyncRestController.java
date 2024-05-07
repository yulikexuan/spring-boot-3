//: sfg6lab.controller.AsyncRestController.java

package sfg6lab.controller;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.TimeUnit;


@Slf4j
@RestController
class AsyncRestController {

    @GetMapping("/hello/{test}")
    WebAsyncTask<String> greeting(@PathVariable("test") String test) {

        log.debug(">>> Service Starts ... ");

        WebAsyncTask<String> task = new WebAsyncTask<>(
                4000, () -> this.executeTask(test));

        task.onTimeout(() -> {
            log.debug(">>> onTimeout...");
            return "Request timed out...";
        });

        task.onError(() -> {
            log.debug(">>> onError...");
            return "Some error occurred...";
        });

        task.onCompletion(()->{
            log.debug(">>> onCompletion...");
        });

        log.debug(">>> Service ends ...");

        return task;
    }

    private String executeTask(@NonNull String test) {

        log.debug(">>> Task execution starts ...");

        int waitSeconds = 2;

        if ("timeout".equals(test)) {
            waitSeconds = 5;
        }

        try {
            TimeUnit.SECONDS.sleep(waitSeconds);
        } catch (InterruptedException e) {
            log.warn(">>> Task was interrupted");
            return ">>> Not Completed!";
        }

        if ("error".equals(test)) {
            throw new RuntimeException(">>> Manual exception at runtime!");
        }

        log.debug(">>> Task execution end...");

        return ">>> Welcome to Spring Async World, %s".formatted(test);
    }

} ///:~