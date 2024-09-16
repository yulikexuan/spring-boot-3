//: sfg6lab.repository.BuyerContactManyToManyRepositoryTest.java

package sfg6lab.repository;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import sfg6lab.domain.model.Buyer;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@DataJdbcTest
@Import(sfg6lab.config.Sfg6AppCfg.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("Test BuyerContactManyToManyRepository Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class BuyerContactManyToManyRepositoryTest {

    @Autowired
    private BuyerRepository buyerRepository;

    @BeforeAll
    void beforeAll() {

    }

    @AfterAll
    void afterAll() {

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

} 
