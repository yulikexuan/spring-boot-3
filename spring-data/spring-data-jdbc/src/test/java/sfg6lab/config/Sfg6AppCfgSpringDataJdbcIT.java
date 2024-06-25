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
import sfg6lab.domain.model.User;
import sfg6lab.repository.UserRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DisplayName("Test Sfg6AppCfgSpringDataJdbcIT Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
@TestInstance(Lifecycle.PER_CLASS)
@DataJdbcTest
@Import(Sfg6AppCfg.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
abstract class Sfg6AppCfgSpringDataJdbcIT {

    @Autowired
    UserRepository userRepository;

    @BeforeAll
    void beforeAll() {
        // Loaded all records by running data.sql
    }

    @AfterAll
    void afterAll() {
        userRepository.deleteAll();
    }

//    @Test
//    void has_Repository_For_User_To_Know_All_Users() {
//
//        // Given
//
//        // When
//        List<User> users = userRepository.findAll();
//
//        // Then
//        assertThat(users).hasSize(11);
//    }

}
