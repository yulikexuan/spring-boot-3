//: springlab.domain.model.Parent.java


package springlab.domain.model;


import lombok.NonNull;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public record Parent(UUID name) {

    public Parent() {
        this(UUID.randomUUID());
    }

}///:~