//: sfg6lab.domain.model.zoo.SheepManager.java

package sfg6lab.domain.model.zoo;


import static java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;


class SheepManager {

    private AtomicInteger sheepCount = new AtomicInteger(0);
    
    void incrementAndReport() {
        synchronized (this) {
            System.out.print(sheepCount.incrementAndGet() + " ");
        }
    }
    
    void reset() {
        sheepCount = new AtomicInteger(0);
    }
    
    public static void main(String[] args) {
        
        try (var service = newVirtualThreadPerTaskExecutor()) {
            var manager = new SheepManager();
            for (int i = 0; i < 10; i++) {
                service.submit(manager::incrementAndReport);
            }
        }
        
        System.out.println();
    }
    
} /// :~