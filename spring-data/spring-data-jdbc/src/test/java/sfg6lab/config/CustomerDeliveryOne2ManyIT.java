//: sfg6lab.config.CustomerDeliveryManyToOneIT.java

package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import sfg6lab.domain.model.Customer;
import sfg6lab.domain.model.Delivery;
import sfg6lab.repository.CustomerRepository;
import sfg6lab.repository.DeliveryRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@Slf4j
class CustomerDeliveryOne2ManyIT extends Sfg6SpringDataJdbcIT {

    private static final String CITY = "Montréal";
    private static final int CUSTOMER_COUNT = 10;
    private static final int DELIVERIES_PER_CUSTOMER = 2;
    private static final int DELIVERY_COUNT =
            CUSTOMER_COUNT * DELIVERIES_PER_CUSTOMER;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @BeforeAll
    void beforeAll() {
        customerRepository.saveAll(initData());
    }

    @AfterAll
    void afterAll() {
        customerRepository.deleteAll();
    }

    @Test
    void one_Customer_Have_2_Deliveries() {

        // Given

        // When & Then
        assertAll(
                () -> assertThat(customerRepository.count())
                        .isEqualTo(CUSTOMER_COUNT),
                () -> assertThat(deliveryRepository.count())
                        .isEqualTo(DELIVERY_COUNT));

        List<Integer> deliveriesPerCustomer = null;

        // When
        try (Stream<Customer> customerStreamable =
                     customerRepository.findAllByOrderByIdAsc().stream()) {

            deliveriesPerCustomer = customerStreamable
                    .map(Customer::id)
                    .map(deliveryRepository::countByCustomerId)
                    .distinct()
                    .toList();
        }

        // Then
        assertThat(deliveriesPerCustomer)
                .hasSize(1)
                .containsExactly(DELIVERIES_PER_CUSTOMER);
    }

    private List<Customer> initData() {

        return List.of(
                Customer.of(
                        "john",
                        "john@somedomain.com",
                        1,
                        true,
                        LocalDateTime.of(2020, Month.APRIL, 13, 0, 0),
                        Set.of(Delivery.of(street(11), CITY),
                                Delivery.of(street(12), CITY))),
                Customer.of(
                        "mike",
                        "mike@somedomain.com",
                        3,
                        true,
                        LocalDateTime.of(2020, Month.JANUARY, 18, 0, 0),
                        Set.of(Delivery.of(street(21), CITY),
                                Delivery.of(street(22), CITY))),
                Customer.of(
                        "james",
                        "james@somedomain.com",
                        3,
                        false,
                        LocalDateTime.of(2020, Month.MARCH, 11, 0, 0),
                        Set.of(Delivery.of(street(31), CITY),
                                Delivery.of(street(32), CITY))),
                Customer.of(
                        "katie",
                        "katie@somedomain.com",
                        5,
                        true,
                        LocalDateTime.of(2021, Month.JANUARY, 5, 0, 0),
                        Set.of(Delivery.of(street(41), CITY),
                                Delivery.of(street(42), CITY))),
                Customer.of(
                        "beth",
                        "beth@somedomain.com",
                        2,
                        true,
                        LocalDateTime.of(2020, Month.AUGUST, 3, 0, 0),
                        Set.of(Delivery.of(street(51), CITY),
                                Delivery.of(street(52), CITY))),
                Customer.of(
                        "julius",
                        "julius@somedomain.com",
                        4,
                        true,
                        LocalDateTime.of(2021, Month.FEBRUARY, 9, 0, 0),
                        Set.of(Delivery.of(street(61), CITY),
                                Delivery.of(street(62), CITY))),
                Customer.of(
                        "darren",
                        "darren@somedomain.com",
                        2,
                        true,
                        LocalDateTime.of(2020, Month.DECEMBER, 11, 0, 0),
                        Set.of(Delivery.of(street(71), CITY),
                                Delivery.of(street(72), CITY))),
                Customer.of(
                        "marion",
                        "marion@somedomain.com",
                        2,
                        false,
                        LocalDateTime.of(2020, Month.SEPTEMBER, 23, 0, 0),
                        Set.of(Delivery.of(street(81), CITY),
                                Delivery.of(street(82), CITY))),
                Customer.of(
                        "stephanie",
                        "stephanie@somedomain.com",
                        4,
                        true,
                        LocalDateTime.of(2020, Month.JANUARY, 18, 0, 0),
                        Set.of(Delivery.of(street(91), CITY),
                                Delivery.of(street(92), CITY))),
                Customer.of(
                        "burk",
                        "burk@somedomain.com",
                        1,
                        true,
                        LocalDateTime.of(2020, Month.NOVEMBER, 28, 0, 0),
                        Set.of(Delivery.of(street(101), CITY),
                                Delivery.of(street(102), CITY))));
    }

    private static String street(int number) {
        return number + ", 51th Avenue";
    }

} ///:~