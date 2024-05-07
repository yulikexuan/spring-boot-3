//: sfg6lab.domain.model.NumberFormatterTest.java

package sfg6lab.domain.model;


import org.junit.jupiter.api.*;
import sfg6lab.domain.model.InventoryService.*;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;


@DisplayName("Test InventoryService Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class InventoryServiceTest {

    private static final int CORE_THREAD_POOL_SIZE = 16;

    private Inventory inventory;
    private ExecutorService threadPool;

    @BeforeEach
    void setUp() {
        this.inventory = new Inventory();
        this.threadPool = Executors.newFixedThreadPool(CORE_THREAD_POOL_SIZE);
    }

    @AfterEach
    void tearDown() {
        this.threadPool.shutdownNow();
        await().pollDelay(Duration.ofMillis(500)).until(() -> true);
    }

    @RepeatedTest(4)
    void should_Be_Thread_Safe() {

        // Given
        var futures = IntStream.rangeClosed(1, CORE_THREAD_POOL_SIZE)
                .mapToObj(i -> CompletableFuture.runAsync(
                        () -> this.changeInventory(i), threadPool))
                .toList();

        // When
        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();

        // Then
        assertThat(this.inventory.items()).isZero();
    }

    void changeInventory(int i) {
        if (i % 2 == 0) {
            InventoryIncrementingService.of(this.inventory).run();
        } else {
            InventoryDecrementingService.of(this.inventory).run();
        }
    }

} ///:~