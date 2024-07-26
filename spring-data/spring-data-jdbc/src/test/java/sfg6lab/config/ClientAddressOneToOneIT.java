//: sfg6lab.config.ClientAddressOneToOneIT.java

package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import sfg6lab.domain.model.Address;
import sfg6lab.domain.model.Client;
import sfg6lab.repository.ClientRepository;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@Slf4j
class ClientAddressOneToOneIT extends Sfg6SpringDataJdbcIT {

    private static final int EXPECTED_NUMBER_OF_RECORDS = 10;

    @Autowired
    private ClientRepository clientRepository;

    @BeforeEach
    void setUp() {
        clientRepository.saveAll(initializeDb());
    }

    @AfterEach
    void tearDown() {
        clientRepository.deleteAll();
    }

    @Test
    void have_Same_Number_Of_Records() {
        assertThat(clientRepository.count())
                .isEqualTo(EXPECTED_NUMBER_OF_RECORDS);
    }

    @ParameterizedTest
    @CsvSource({
            "'john@somedomain.com', '1 5th Avenue', 'New York'",
            "'james@somedomain.com', '3 5th Avenue', 'New York'",
            "'burk@somedomain.com', '10 5th Avenue', 'New York'"})
    void one_Client_Has_Only_One_Address_At_Least(String email, String street, String city) {

        // Given

        // When
        Optional<Client> clientOpt = clientRepository.findByEmail(email);

        // Then
        assertThat(clientOpt).isPresent();

        // Given
        var client = clientOpt.get();

        // When
        Address address = client.address();

        // Then
        assertAll(
                () -> assertThat(address)
                        .extracting("street", "city")
                        .containsExactly(street, city),
                () -> assertThat(client.email()).isEqualTo(email));
    }

    private List<Client> initializeDb() {

        return List.of(
                Client.of(
                        "john",
                        "john@somedomain.com",
                        1,
                        true,
                        LocalDateTime.of(2020, Month.APRIL, 13, 0, 0),
                        "1 5th Avenue",
                        "New York"),
                Client.of(
                        "mike",
                        "mike@somedomain.com",
                        3,
                        true,
                        LocalDateTime.of(2020, Month.JANUARY, 18, 0, 0),
                        "2 5th Avenue",
                        "New York"),
                Client.of(
                        "james",
                        "james@somedomain.com",
                        3,
                        false,
                        LocalDateTime.of(2020, Month.MARCH, 11, 0, 0),
                        "3 5th Avenue",
                        "New York"),
                Client.of(
                        "katie",
                        "katie@somedomain.com",
                        5,
                        true,
                        LocalDateTime.of(2021, Month.JANUARY, 5, 0, 0),
                        "4 5th Avenue",
                        "New York"),
                Client.of(
                        "beth",
                        "beth@somedomain.com",
                        2,
                        true,
                        LocalDateTime.of(2020, Month.AUGUST, 3, 0, 0),
                        "5 5th Avenue",
                        "New York"),
                Client.of(
                        "julius",
                        "julius@somedomain.com",
                        4,
                        true,
                        LocalDateTime.of(2021, Month.FEBRUARY, 9, 0, 0),
                        "6 5th Avenue",
                        "New York"),
                Client.of(
                        "darren",
                        "darren@somedomain.com",
                        2,
                        true,
                        LocalDateTime.of(2020, Month.DECEMBER, 11, 0, 0),
                        "7 5th Avenue",
                        "New York"),
                Client.of(
                        "marion",
                        "marion@somedomain.com",
                        2,
                        false,
                        LocalDateTime.of(2020, Month.SEPTEMBER, 23, 0, 0),
                        "8 5th Avenue",
                        "New York"),
                Client.of(
                        "stephanie",
                        "stephanie@somedomain.com",
                        4,
                        true,
                        LocalDateTime.of(2020, Month.JANUARY, 18, 0, 0),
                        "9 5th Avenue",
                        "New York"),
                Client.of(
                        "burk",
                        "burk@somedomain.com",
                        1,
                        true,
                        LocalDateTime.of(2020, Month.NOVEMBER, 28, 0, 0),
                        "10 5th Avenue",
                        "New York"));
    }

} ///:~