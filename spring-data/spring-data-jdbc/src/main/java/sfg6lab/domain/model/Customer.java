//: sfg6lab.domain.model.Customer.java

package sfg6lab.domain.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.InsertOnlyProperty;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Set;


@Table("customers")
public record Customer(@Id Long id,
                       String name,
                       String email,
                       int level,
                       boolean active,
                       @InsertOnlyProperty LocalDateTime created,
                       @MappedCollection(idColumn = "customer_id")
                       Set<Delivery> deliveries) {

    public static Customer of(String name,
                              String email,
                              int level,
                              boolean active,
                              LocalDateTime created,
                              Set<Delivery> deliveries) {

        return new Customer(null, name, email, level, active, created, deliveries);
    }
}
