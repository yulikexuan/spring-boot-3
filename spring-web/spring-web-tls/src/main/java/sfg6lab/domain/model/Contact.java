//: sfg6lab.domain.model.Contact.java

package sfg6lab.domain.model;


import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;


@Table("contacts")
public record Contact(@Id Long id,
                      @NonNull String street,
                      @NonNull String city) {

    public static final String CITY = "New York City";
    public static final String STREET_NAME_TEMPLATE = "%d, 5th Avenue";

    public static Contact of(int streetNumber) {
        return new Contact(
                null,
                STREET_NAME_TEMPLATE.formatted(streetNumber),
                CITY);
    }
}