//: sfg6lab.domain.model.AlternativeJdkIdGeneratorTest.java


package sfg6lab.domain.model;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DisplayName("Test AlternativeJdkIdGenerator Util Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AlternativeJdkIdGeneratorTest {

    // AlternativeJdkIdGenerator provides a better balance between
    // securely random ids and performance

    private AlternativeJdkIdGenerator idGenerator;

    @BeforeEach
    void setUp() {
        this.idGenerator = new AlternativeJdkIdGenerator();
    }

    @Test
    void able_To_Generate_New_UUID() {

        // When
        UUID id = this.idGenerator.generateId();

        log.debug(">>> New ID: {}", id);

        // Then
        assertThat(id).isNotNull();
    }

}///:~