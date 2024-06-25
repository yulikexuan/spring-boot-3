//: sfg6lab.config.Sfg6JdbcRepositoryIT.java

package sfg6lab.config;


import sfg6lab.domain.model.User;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


class Sfg6JdbcRepositoryIT extends Sfg6AppCfgSpringDataJdbcIT{

    @Test
    void has_Repository_For_User_To_Know_All_Users() {

        // Given

        // When
        List<User> users = userRepository.findAll();

        // Then
        assertThat(users).hasSize(11);
    }

} ///:~