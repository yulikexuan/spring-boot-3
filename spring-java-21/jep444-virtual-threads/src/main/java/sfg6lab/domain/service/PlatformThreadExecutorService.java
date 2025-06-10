//: sfg6lab.domain.service.PlatformThreadExecutorService.java

package sfg6lab.domain.service;


import java.util.concurrent.Executors;
import java.util.stream.DoubleStream;


class PlatformThreadExecutorService {
    
    public static void main(String[] args) {
        
        var s = Executors.newFixedThreadPool(1);
        
        DoubleStream.of(3.14159, 2.71828)
                .forEach(d -> s.submit(() -> System.out.println(10 * d)));
        
        s.execute(() -> System.out.println(">>> Printed!"));
        
        // Hang forever
    }
    
} /// :~