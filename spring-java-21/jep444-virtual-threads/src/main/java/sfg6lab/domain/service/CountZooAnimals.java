//: sfg6lab.domain.service.CountZooAnimals.java

package sfg6lab.domain.service;


import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


class CountZooAnimals {
    
    public static void performCount(int animal) {
        // IMPLEMENTATION OMITTED
    }
    public static void printResults(Future<?> f) {
        try {
            System.out.println(f.get(1, TimeUnit.DAYS)); // o1
        } catch (Exception e) {
            System.out.println("Exception!");
        }
    }
    public static void main(String[] args) throws Exception {
        final var r = new ArrayList<Future<?>>();
        try (var s = Executors.newSingleThreadExecutor()) {
            for (int i = 0; i < 10; i++) {
                final int animal = i;
                r.add(s.submit(() -> performCount(animal))); // o2
            }
            r.forEach(f -> printResults(f));
        }
    }
    
} /// :~