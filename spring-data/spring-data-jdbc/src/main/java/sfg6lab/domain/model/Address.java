//: sfg6lab.domain.model.Address.java

package sfg6lab.domain.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("addresses")
public record Address(
        @Id @Column("client_id") Long clientId,
        @Column("street") String street,
        @Column("city") String city) {
} ///:~