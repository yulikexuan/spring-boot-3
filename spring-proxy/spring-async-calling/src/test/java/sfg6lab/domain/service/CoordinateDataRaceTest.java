//: sfg6lab.domain.service.DataRaceTest.java

package sfg6lab.domain.service;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import sfg6lab.domain.model.Coordinate;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;


@DisplayName("Test sfg6lab.domain.model.DataRace Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class CoordinateDataRaceTest {

    private static final int CORE_THREAD_POOL_SIZE = 2;
    private static ExecutorService threadPool;

    private Coordinate coordinate;
    private CoordinateIncrementingService incrementingService;
    private CoordinateDataRaceCheckingService dataRaceCheckingService;

    @BeforeAll
    static void beforeAll() {
        threadPool = Executors.newFixedThreadPool(CORE_THREAD_POOL_SIZE);
    }

    @AfterAll
    static void afterAll() {
        threadPool.shutdownNow();
    }

    @BeforeEach
    void setUp() {
        this.coordinate = new Coordinate();
        this.incrementingService = CoordinateIncrementingService.of(coordinate);
        this.dataRaceCheckingService = CoordinateDataRaceCheckingService.of(coordinate);
    }

    @AfterEach
    void tearDown() {
        await().pollDelay(Duration.ofMillis(200)).until(() -> true);
    }

    @RepeatedTest(2)
    void inventory_Services_Should_Be_Thread_Safe() {

        // Given
        var incrementingFuture = CompletableFuture.runAsync(
                this.incrementingService::increment, threadPool);

        var checkingFuture = CompletableFuture.runAsync(
                this.dataRaceCheckingService::checkDataRace, threadPool);

        // When
        CompletableFuture.allOf(incrementingFuture, checkingFuture).join();

        // Then
        assertThat(CoordinateDataRaceCheckingService.count()).isZero();
    }

} ///:~