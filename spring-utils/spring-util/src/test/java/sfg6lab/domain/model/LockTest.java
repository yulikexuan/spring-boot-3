//: sfg6lab.domain.model.LockTest.java


package sfg6lab.domain.model;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.data.util.Lock;

import java.util.concurrent.locks.ReentrantLock;


@Slf4j
@DisplayName("Test Lock Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LockTest {

    private ReentrantLock backend;

    private Lock lock;

    @BeforeEach
    void setUp() {
        this.backend = new ReentrantLock();
        this.lock = Lock.of(this.backend);
    }

    @Test
    void easy_Lock() {

        lock.executeWithoutResult(() -> {
            log.debug(">>> In executeWithoutResult ... ");
        });

        String result = lock.execute(() -> {
            log.debug(">>> In execute ... ");
            return "DONE";
        });
    }

}///:~