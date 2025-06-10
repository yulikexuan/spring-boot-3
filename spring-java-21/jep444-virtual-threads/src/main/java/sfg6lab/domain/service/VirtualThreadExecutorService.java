//: sfg6lab.domain.service.VirtualThreadExecutorService.java

package sfg6lab.domain.service;


import java.util.concurrent.Executors;
import java.util.stream.DoubleStream;


class VirtualThreadExecutorService {
    
    public static void main(String[] args) {
        
        var s = Executors.newVirtualThreadPerTaskExecutor();
        
        DoubleStream.of(3.14159, 2.71828)
                .forEach(d -> s.submit(() -> System.out.println(10 * d)));
        
        s.execute(() -> System.out.println(">>> Printed!"));
    }
    
} /// :~