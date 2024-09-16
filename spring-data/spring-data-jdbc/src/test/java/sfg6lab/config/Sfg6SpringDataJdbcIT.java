//: sfg6lab.config.Sfg6AppCfgSpringDataJdbcFailed.java

package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.DataJdbcTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.context.annotation.Import;
import sfg6lab.repository.UserRepository;


@Slf4j
@DisplayName("Test Sfg6AppCfgSpringDataJdbcIT Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
@TestInstance(Lifecycle.PER_CLASS)
@DataJdbcTest
@Import(Sfg6AppCfg.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
abstract class Sfg6SpringDataJdbcIT {

    @Autowired
    UserRepository userRepository;

    @BeforeAll
    void beforeAll() {
        // Loaded all records by running _data.sql
    }

    @AfterAll
    void afterAll() {
        userRepository.deleteAll();
    }

}
