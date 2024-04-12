//: sfg6lab.domain.service.SleepAndDreamService


package sfg6lab.domain.service;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


public interface SleepAndDreamService {

    void sleep1Seconds() throws Exception;

    CompletableFuture<String> sleep1SecondWithDream() throws Exception;

    static SleepAndDreamService of() {
        return new SleepAndDreamServiceImpl();
    }
}


/*
 * Something else is important too: if the methods call each other, there is no
 * proxy in between
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class SleepAndDreamServiceImpl implements SleepAndDreamService {

    @Override
    @Async("threadPoolTaskExecutor")
    public void sleep1Seconds() throws Exception {

        log.debug(">>> Going to sleep: SNNNNOOORRRREEEEE ... ");

        TimeUnit.SECONDS.sleep(1);

        log.debug(">>> WOKE UP! <<< ");

        throw new RuntimeException(">>> Something wrong!");
    }

    @Override
    @Async("threadPoolTaskExecutor")
    public CompletableFuture<String> sleep1SecondWithDream() throws Exception {

        log.debug(">>> Starting to dream : ~ ~ ~ ~ ~ ~ ~" );

        TimeUnit.SECONDS.sleep( 1 );

        log.debug( ">>> Out of the Dream! <<<" );

        if (true) {
            throw new RuntimeException(">>> Failed to remember the dream");
        }

        return CompletableFuture.completedFuture("Amazing Dream in Memory");
    }

}

///:~