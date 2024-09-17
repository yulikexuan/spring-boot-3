//: sfg6lab.repository.BuyerContactManyToManyRepositoryTest.java

package sfg6lab.repository;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import sfg6lab.domain.model.Buyer;
import sfg6lab.domain.model.Contact;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DataJdbcTest
// @Testcontainers
@Import(sfg6lab.config.Sfg6AppCfg.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Test BuyerContactManyToManyRepository Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BuyerContactManyToManyRepositoryTest {

    // @Container
    private static final PostgreSQLContainer SQL_CONTAINER;

    static {

        SQL_CONTAINER =
                (PostgreSQLContainer) (new PostgreSQLContainer("postgres:15")
                        .withDatabaseName("yulikexuan")
                        .withUsername("postgres")
                        .withPassword("tecsys")
                        .withUrlParam("currentSchema", "spring-db-test"));

        SQL_CONTAINER.start();
    }

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.username", SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", SQL_CONTAINER::getPassword);
    }

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private BuyerRepository buyerRepository;

    @BeforeAll
    void beforeAll() {
    }

    @AfterAll
    void afterAll() {
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void buyer_Repository_Should_Be_Available() {

        // Given
        List<Buyer> buyers = buyerRepository.findAll();

        // When
        Optional<Buyer> buyerOpt = buyers.stream()
                .filter(buyer -> buyer.contacts().size() != 2)
                .findAny();

        // Then
        assertThat(buyerOpt).isEmpty();
    }

    @Test
    void contact_Repository_Should_Be_Available() {

        // Given

        // When
        List<Contact> contacts = contactRepository.findAll();

        // Then
        assertThat(contacts).hasSize(3);
    }

}
