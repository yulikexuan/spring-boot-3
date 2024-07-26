//: sfg6lab.domain.model.Client.java

package sfg6lab.domain.model;


import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;


@Table("clients")
public record Client(
        @Id @Column("id") Long id,
        @Column("name") @NonNull String name,
        @Column("email") String email,
        @Column("level") int level,
        @Column("active") boolean active,
        @Column("created") @NonNull LocalDateTime created,
        @MappedCollection(idColumn = "client_id") Address address) {

    public static Client of(
            @NonNull String name,
            @NonNull String email,
            int level,
            boolean active,
            @NonNull LocalDateTime created,
            @NonNull String street,
            @NonNull String city) {

        return new Client(
                null, name, email, level, active, created,
                new Address(null, street, city));
    }

} ///:~