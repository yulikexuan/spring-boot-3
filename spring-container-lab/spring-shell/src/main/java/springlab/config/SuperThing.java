//: springlab.config.SuperThing.java


package springlab.config;


import org.springframework.context.annotation.Bean;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.IntSupplier;


abstract class SuperThing {

    ThreadLocalRandom random = ThreadLocalRandom.current();

    @Bean
    public IntSupplier randomInt() {
        return () -> random.nextInt(1, 100);
    }

}///:~