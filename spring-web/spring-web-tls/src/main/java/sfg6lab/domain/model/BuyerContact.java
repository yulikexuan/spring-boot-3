//: sfg6lab.domain.model.BuyerContact.java

package sfg6lab.domain.model;


import lombok.NonNull;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("buyers_contacts")
public record BuyerContact(@Column("contact_id") Long contactId) {

    public static BuyerContact of(@NonNull Contact contact) {

        return new BuyerContact(contact.id());
    }
}
