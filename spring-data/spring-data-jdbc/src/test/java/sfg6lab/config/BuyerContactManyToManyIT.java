//: sfg6lab.config.BuyerContactManyToManyIT.java

package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sfg6lab.domain.model.Buyer;
import sfg6lab.domain.model.Contact;
import sfg6lab.repository.BuyerContactManyToManyRepository;
import sfg6lab.repository.BuyerRepository;
import sfg6lab.repository.ContactRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
class BuyerContactManyToManyIT extends Sfg6SpringDataJdbcIT {

    private static final int AMOUNT_OF_BUYERS = 10;
    private static final int AMOUNT_OF_CONTACTS = 3;
    private static final int AMOUNT_OF_RELATIONSHIPS = 20;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @Autowired
    private BuyerContactManyToManyRepository buyerContactRepository;

    private List<Buyer> buyers;
    private List<Contact> contacts;

    @BeforeAll
    void beforeAll() {

        contactRepository.saveAll(List.of(
                Contact.of(11),
                Contact.of(22),
                Contact.of(33)));

        contacts = contactRepository.findAll();
        buyerRepository.saveAll(initData());
    }

    @AfterAll
    void afterAll() {
        buyerContactRepository.deleteAll();
        contactRepository.deleteAll();
        buyerRepository.deleteAll();
    }

    @Test
    void buyer_And_Contact_Have_Many_To_Many_Relationship() {

        // Given

        // When
        buyers = buyerRepository.findAll();
        contacts = contactRepository.findAll();

        // Then
        assertThat(buyers).hasSize(AMOUNT_OF_BUYERS);
        assertThat(contacts).hasSize(AMOUNT_OF_CONTACTS);
        assertThat(buyerContactRepository.count()).isEqualTo(AMOUNT_OF_RELATIONSHIPS);
    }

    @Test
    void id_Reloaded() {

        // Given
        Contact contact = Contact.of(852);

        // When
        contact = contactRepository.save(contact);

        // Then
        assertThat(contact.id()).isNotNull();
    }

    private List<Buyer> initData() {

        return List.of(
                Buyer.of(
                        "john",
                        "john@somedomain.com",
                        1,
                        true,
                        LocalDateTime.of(2020, Month.APRIL, 13, 0, 0),
                        Set.of(contacts.get(0), contacts.get(1))),
                Buyer.of(
                        "mike",
                        "mike@somedomain.com",
                        3,
                        true,
                        LocalDateTime.of(2020, Month.JANUARY, 18, 0, 0),
                        Set.of(contacts.get(0), contacts.get(2))),
                Buyer.of(
                        "james",
                        "james@somedomain.com",
                        3,
                        false,
                        LocalDateTime.of(2020, Month.MARCH, 11, 0, 0),
                        Set.of(contacts.get(1), contacts.get(2))),
                Buyer.of(
                        "katie",
                        "katie@somedomain.com",
                        5,
                        true,
                        LocalDateTime.of(2021, Month.JANUARY, 5, 0, 0),
                        Set.of(contacts.get(0), contacts.get(1))),
                Buyer.of(
                        "beth",
                        "beth@somedomain.com",
                        2,
                        true,
                        LocalDateTime.of(2020, Month.AUGUST, 3, 0, 0),
                        Set.of(contacts.get(0), contacts.get(2))),
                Buyer.of(
                        "julius",
                        "julius@somedomain.com",
                        4,
                        true,
                        LocalDateTime.of(2021, Month.FEBRUARY, 9, 0, 0),
                        Set.of(contacts.get(1), contacts.get(2))),
                Buyer.of(
                        "darren",
                        "darren@somedomain.com",
                        2,
                        true,
                        LocalDateTime.of(2020, Month.DECEMBER, 11, 0, 0),
                        Set.of(contacts.get(0), contacts.get(1))),
                Buyer.of(
                        "marion",
                        "marion@somedomain.com",
                        2,
                        false,
                        LocalDateTime.of(2020, Month.SEPTEMBER, 23, 0, 0),
                        Set.of(contacts.get(0), contacts.get(2))),
                Buyer.of(
                        "stephanie",
                        "stephanie@somedomain.com",
                        4,
                        true,
                        LocalDateTime.of(2020, Month.JANUARY, 18, 0, 0),
                        Set.of(contacts.get(1), contacts.get(2))),
                Buyer.of(
                        "burk",
                        "burk@somedomain.com",
                        1,
                        true,
                        LocalDateTime.of(2020, Month.NOVEMBER, 28, 0, 0),
                        Set.of(contacts.get(0), contacts.get(1))));
    }

} ///:~