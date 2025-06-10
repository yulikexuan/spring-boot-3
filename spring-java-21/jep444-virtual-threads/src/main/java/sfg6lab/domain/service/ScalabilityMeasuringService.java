//: sfg6lab.domain.service.ScalabilityMeasuringService.java

package sfg6lab.domain.service;


import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import lombok.NonNull;


class ScalabilityMeasuringService {
    
    private static final int MINIMUM_THREAD_COUNT = 500;
    
    private static final int[] THREAD_COUNTS =
            { 1000, 5000};
    
    private static final String OUTPUT_MSG_TEMPLATE = "%11d,%23d,%22d,%16.2f %%";
    
    public static void main(String[] args) {
        
        System.out.println("Performance and Scalability");
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println("Task Count   | Platform Threads (ms) | Virtual Threads (ms)  | Improvement (%) ");
        System.out.println("-------------------------------------------------------------------------------");
        
        for (int count : THREAD_COUNTS) {
            
            // Platform threads
            long platformDuration = testPlatformThreads(count);
            
            // Virtual threads
            long virtualDuration = testVirtualThreads(count);
            
            double improvement = 100 - (virtualDuration * 100.0 / platformDuration);
            
            var output = OUTPUT_MSG_TEMPLATE.formatted(
                    count, platformDuration, virtualDuration, improvement);
            
            System.out.println(output);
        }
    }
    
    private static long testPlatformThreads(int threadCount) {
        
        try {
            // Practical platform thread limit
            int poolSize = Math.min(threadCount, MINIMUM_THREAD_COUNT);
            
            Instant start = Instant.now();
            
            try (ExecutorService executor = Executors.newFixedThreadPool(poolSize)) {
                executeTasks(executor, threadCount);
            }
            
            return Duration.between(start, Instant.now()).toMillis();
        
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    private static long testVirtualThreads(int threadCount) {
        
        try {
            Instant start = Instant.now();
            
            try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
                executeTasks(executor, threadCount);
            }
            
            return Duration.between(start, Instant.now()).toMillis();
            
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
    
    private static void executeTasks(
            @NonNull final ExecutorService executor,
            final int taskCount) throws Exception {
        
        CountDownLatch latch = new CountDownLatch(taskCount);
        
        for (int i = 0; i < taskCount; i++) {
            executor.submit(() -> {
                try {
                    simulateWork();
                    latch.countDown();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        
        latch.await(5, TimeUnit.MINUTES);
    }
    
    private static void simulateWork() throws Exception {
        // Simulate I/O-bound work (blocking operations)
        Thread.sleep(300); // Simulate network/DB call
    }
    
} /// :~