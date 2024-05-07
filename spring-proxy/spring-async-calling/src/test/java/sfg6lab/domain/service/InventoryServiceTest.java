//: sfg6lab.domain.service.InventoryServiceTest.java

package sfg6lab.domain.service;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import sfg6lab.domain.model.Inventory;

import java.time.Duration;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;


@Slf4j
@DisplayName("Test the Thread Safety of InventoryServices - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class InventoryServiceTest {

    private static final int CORE_THREAD_POOL_SIZE = 16;

    private static ExecutorService threadPool;

    private Inventory inventory;
    private InventoryService inventoryIncrementingService;
    private InventoryService inventoryDecrementingService;


    @BeforeEach
    void setUp() {
        this.inventory = Inventory.threadSafeInventory();
        this.inventoryIncrementingService =
                InventoryService.incrementingService(this.inventory);
        this.inventoryDecrementingService =
                InventoryService.decrementingService(this.inventory);
    }

    @AfterEach
    void tearDown() {
        await().pollDelay(Duration.ofMillis(200)).until(() -> true);
    }

    @BeforeAll
    static void beforeAll() {
        threadPool = Executors.newFixedThreadPool(CORE_THREAD_POOL_SIZE);
    }

    @AfterAll
    static void afterAll() {
        try {
            boolean terminated = threadPool.awaitTermination(1, TimeUnit.SECONDS);
            log.debug(">>> The executor's terminated? {}, but will be terminated anyway!",
                    terminated);
        } catch (InterruptedException e) {
            threadPool.shutdownNow();
        }
    }

    @RepeatedTest(4)
    void inventory_Services_Should_Be_Thread_Safe() {

        // Given
        var futures = IntStream.rangeClosed(1, CORE_THREAD_POOL_SIZE)
                .mapToObj(this::changeInventoryAsync)
                .toList();

        // When
        CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new)).join();

        // Then
        assertThat(this.inventory.items()).isZero();
    }

    private CompletableFuture<Void> changeInventoryAsync(int id) {

        InventoryService workingService = (id % 2 == 0) ?
                this.inventoryIncrementingService : this.inventoryDecrementingService;

        return CompletableFuture.runAsync(workingService::changeInventory, threadPool);
    }

} ///:~