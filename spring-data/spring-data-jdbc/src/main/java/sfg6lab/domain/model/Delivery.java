//: sfg6lab.domain.model.Delivery.java

package sfg6lab.domain.model;


import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("deliveries")
public record Delivery(@Id Long id,
                       @NonNull String street,
                       @NonNull String city) {

    public static Delivery of(@NonNull String street,
                              @NonNull String city) {

        return new Delivery(null, street, city);
    }
}
