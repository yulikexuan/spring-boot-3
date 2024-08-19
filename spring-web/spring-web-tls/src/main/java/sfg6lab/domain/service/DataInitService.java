//: sfg6lab.domain.service.DataService.java

package sfg6lab.domain.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sfg6lab.domain.model.Buyer;
import sfg6lab.domain.model.Contact;
import sfg6lab.domain.repository.BuyerRepository;
import sfg6lab.domain.repository.ContactRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Set;


@Service
@RequiredArgsConstructor(staticName = "of")
public class DataInitService {

    private final BuyerRepository buyerRepository;
    private final ContactRepository contactRepository;

    public long initialize() {

        contactRepository.saveAll(List.of(
                Contact.of(11),
                Contact.of(22),
                Contact.of(33)));

        buyerRepository.saveAll(initData(contactRepository.findAll()));

        return buyerRepository.count();
    }

    private List<Buyer> initData(List<Contact> contacts) {

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