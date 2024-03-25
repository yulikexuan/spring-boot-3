//: springlab.config.GenericThing


package springlab.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.UUID;
import java.util.function.Supplier;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;


public interface ProfileThing {

    @Bean
    @Scope(SCOPE_PROTOTYPE)
    default Supplier<UUID> uuidSupplier() {
        return UUID::randomUUID;
    }


}///:~