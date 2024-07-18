//: sfg6lab.domain.model.VirtualThreadExecutorServiceTest.java

package sfg6lab.domain.model;


import org.junit.jupiter.api.*;
import org.springframework.util.StopWatch;

import java.time.Duration;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor;


@DisplayName("ExecutorService for Virtual Threads Test - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class VirtualThreadExecutorServiceTest {

    private StopWatch stopWatch;

    @BeforeEach
    void setUp() {
        stopWatch = new StopWatch("Virtual Thread Performance Test - With 1 Sec. Tasks");
    }

    @Test
    void show_Me_The_Power_Of_Virtual_Threads() {

        stopWatch.start("Run 1 Million");
        runVT(1_000_000);
        stopWatch.stop();

        stopWatch.start("Run 100K");
        runVT(100_000);
        stopWatch.stop();

        stopWatch.start("Run 10K");
        runVT(10_000);
        stopWatch.stop();

        stopWatch.start("Run 1K");
        runVT(1_000);
        stopWatch.stop();

        stopWatch.start("Run 100");
        runVT(100);
        stopWatch.stop();

        System.out.println(stopWatch.prettyPrint());
    }

    private void runVT(int count) {
        try (var executor = newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, count).forEach(i ->
                    executor.submit(() -> {
                        Thread.sleep(Duration.ofSeconds(1));
                        return i;
                    }));
        }
    }

} ///:~