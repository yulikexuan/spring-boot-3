//: sfg6lab.config.Sfg6AppCfgTest.java

package sfg6lab.config;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sfg6lab.domain.service.SleepAndDreamService;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;


@Slf4j
@SpringBootTest(
        classes = { Sfg6AppCfg.class },
        useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE)
@DisplayName("Test Sfg6AppCfg - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class Sfg6ProxyAsyncAppIT {

    @Autowired
    private SleepAndDreamService sleepAndDreamService;

    private SleepAndDreamClient client;

    @BeforeEach
    void setUp() {
        this.client = SleepAndDreamClient.of(this.sleepAndDreamService);
    }

    @Test
    void show_Async_Dreamer() throws Exception {
        this.client.goToSleepAndDream();
    }

    @Slf4j
    @RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
    static class SleepAndDreamClient {

        private final SleepAndDreamService dreamer;

        public void goToSleepAndDream() throws Exception {

            /* M */ log.debug(">>> Before sleep1Seconds()");

            /* M */ dreamer.sleep1Seconds();

            /* M1 */ log.debug(">>> After sleep1Seconds()");

            /* M1 */ log.debug(">>> Before sleep1SecondWithDream()");

            /* M1 */ final var future = dreamer.sleep1SecondWithDream();

            /* M12 */ log.debug(">>> After sleep1SecondWithDream()");

            /* M12 */ TimeUnit.SECONDS.sleep(2);

            try {
                /* M */ log.debug( ">>> What's your Dream? {}", future.join());
            } catch (Exception e) {
                log.error(">>> Caught exception {}",
                        Arrays.toString(e.getCause().getStackTrace()));
            }
        }

    }

}///:~