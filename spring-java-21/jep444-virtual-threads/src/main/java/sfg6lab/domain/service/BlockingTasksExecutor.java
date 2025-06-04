//: sfg6lab.domain.service.BlockingTasksExecutor.java

package sfg6lab.domain.service;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;


public interface BlockingTasksExecutor {

    void executeBlockingTasks(ExecutorService executorService);
    
    static BlockingTasksExecutor create(
            int numberOfTasks, long blockingTimeInMillis) {
    
        return BlockingTasksExecutorImpl.of(numberOfTasks, blockingTimeInMillis);
    }
    
    static void executeBlockingTasks(
            final int numberOfTasks,
            final long blockingTimeInMillis,
            @NonNull final ExecutorService executor) {
    
        create(numberOfTasks, blockingTimeInMillis).executeBlockingTasks(executor);
    }
    
}

@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
final class BlockingTasksExecutorImpl implements BlockingTasksExecutor {
    
    private final int count;
    private final long blockingTimeInMillis;
    
    @Override
    public void executeBlockingTasks(
            @NonNull final ExecutorService executor) {
        
        final var latch = new CountDownLatch(count);
        
        for (int i = 0; i < count; i++) {
            executor.submit(() -> {
                try {
                    // Simulate I/O blocking
                    Thread.sleep(blockingTimeInMillis);
                    latch.countDown();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }
        
        try {
            latch.await(2, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
    } // End of executeBlockingTasks()
    
}
