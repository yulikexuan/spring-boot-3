//: sfg6lab.domain.service.MemoryUsageMeasuringService.java

package sfg6lab.domain.service;


import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor;
import static sfg6lab.domain.service.BlockingTasksExecutor.executeBlockingTasks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import lombok.NonNull;
import org.springframework.util.StopWatch;


public class MemoryUsageMeasuringService {
    
    private static final long ONE_MEGABYTE = 1024 * 1024;
    
    private static final int THREAD_POOL_SIZE = 500;
    private static final int NUM_OF_TASKS = 10_000;
    private static final long BLOCKING_TIME_IN_MILLIS = 1000;
    
    private static final String OUTPUT_TEMPLATE =
            "%s Threads: %d MB";
    
    private static final String REDUCTION_RESULT_TEMPLATE =
            "Reduction of Memory Usage: %s %%";

    static long measureMemoryUsage(@NonNull final Runnable task) throws Exception {
        
        // Run GC before measurement
        // System.gc();
        // Thread.sleep(2000);
        
        Thread.sleep(1000);
        
        final var runtime = Runtime.getRuntime();
        
        long before = runtime.totalMemory() - runtime.freeMemory();
        
        task.run();
        
        long after = runtime.totalMemory() - runtime.freeMemory();
        
        return (after - before) / ONE_MEGABYTE;
    }
    
    public static void main(String[] args) throws Exception {
        
        final var stopWatch = new StopWatch("Memory-Usage-Measuring-Service");
        
        stopWatch.start("Measuring Platform Threads");
        long ptMemoryUsage = measureMemoryUsage(() -> {
            try (ExecutorService executor = newFixedThreadPool(THREAD_POOL_SIZE)) {
                executeBlockingTasks(NUM_OF_TASKS, BLOCKING_TIME_IN_MILLIS, executor);
            }
        });
        stopWatch.stop();
        
        Thread.sleep(1000);
        
        stopWatch.start("Measuring Virtual Threads");
        long vtMemoryUsage = measureMemoryUsage(() -> {
            try (ExecutorService executor = newVirtualThreadPerTaskExecutor()) {
                executeBlockingTasks(NUM_OF_TASKS, BLOCKING_TIME_IN_MILLIS, executor);
            }
        });
        stopWatch.stop();
        
        System.out.println("\nHeap: %d MB".formatted(1024));
        System.out.println("Number of blocking tasks: %d".formatted(NUM_OF_TASKS));
        System.out.println("Blocking time: %d millis".formatted(BLOCKING_TIME_IN_MILLIS));
        System.out.println("Thread Pool Size (Platform Threads): %d".formatted(THREAD_POOL_SIZE));
        
        System.out.println("\nTotal Time: " + stopWatch.getTotalTimeMillis() + " ms");
        System.out.println("\nDetailed Breakdown:\n" + stopWatch.prettyPrint());
        System.out.println("Memory Usage \n--------------------------------------------------------------");
        System.out.println(OUTPUT_TEMPLATE.formatted(
                "Platform", ptMemoryUsage));
        System.out.println(OUTPUT_TEMPLATE.formatted(
                "Virtual", vtMemoryUsage));
        var reduction = (100 - (vtMemoryUsage * 100 / ptMemoryUsage));
        System.out.println(REDUCTION_RESULT_TEMPLATE.formatted(reduction));
    }

}

