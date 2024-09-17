//: sfg6lab.domain.model.Buyer.java

package sfg6lab.domain.model;


import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.InsertOnlyProperty;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;


@Table(value = "buyers")
// @Table(value = "spring-db-test\".\"buyers")
public record Buyer(@Id Long id,
                    String name,
                    String email,
                    int level,
                    boolean active,
                    @InsertOnlyProperty
                    LocalDateTime created,
                    @MappedCollection(idColumn = "buyer_id")
                    @NonNull
                    Set<BuyerContact> contacts) {

    public static Buyer of(@NonNull String name,
                           @NonNull String email,
                           int level,
                           boolean active,
                           @NonNull LocalDateTime created,
                           @NonNull Set<Contact> contacts) {

        Set<BuyerContact> contactsOfBuyer = contacts.stream()
                .map(BuyerContact::of)
                .collect(Collectors.toUnmodifiableSet());

        return new Buyer(null, name, email, level, active, created, contactsOfBuyer);
    }

}