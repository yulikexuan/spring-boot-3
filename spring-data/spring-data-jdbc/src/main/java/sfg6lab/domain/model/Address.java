//: sfg6lab.domain.model.Address.java

package sfg6lab.domain.model;


import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("addresses")
public record Address(

        // Should not include attributes in entities to hold the actual value
        // of a back reference, nor of the key column of maps or lists
        // @Column("clients") Long clients;

        // All references in an aggregate result in a foreign key relationship
        // in the opposite direction in the database;
        // By default, the name of the foreign key column is the table name
        // of the referencing entity;

        @Column("street") String street,
        @Column("city") String city) {

} ///:~