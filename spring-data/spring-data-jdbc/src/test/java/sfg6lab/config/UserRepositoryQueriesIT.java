//: sfg6lab.config.Sfg6JdbcRepositoryIT.java

package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import sfg6lab.domain.model.User;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Slf4j
class UserRepositoryQueriesIT extends Sfg6SpringDataJdbcIT {

    static final int USER_AMT = 11;
    static final List<String> ALL_USERNAMES_ASC = List.of(
            "beth",
            "burk",
            "darren",
            "james",
            "john",
            "julius",
            "katie",
            "marion",
            "mike",
            "stephanie",
            "yulikexuan");

    @Test
    void has_Repository_For_User_To_Know_All_Users() {

        // Given

        // When
        List<User> users = userRepository.findAll();

        // Then
        assertThat(users).hasSize(USER_AMT);
    }

    @ParameterizedTest
    @ValueSource(strings = {"yulikexuan", "john", "mike", "james", "katie", "beth", "julius", "darren", "marion", "stephanie", "burk"})
    void able_To_Find_User_By_Name(String username) {

        // Given

        // When
        final Optional<User> userOpt = userRepository.findByUsername(username);

        // Then
        assertThat(userOpt).isPresent().hasValueSatisfying(
                obj -> assertThat(obj.getUsername()).isEqualTo(username));
    }

    @Test
    void able_To_Find_All_Users_By_Order_By_Username_ASC() {

        // Given
        List<User> users = userRepository.findAllByOrderByUsernameAsc();

        // When
        List<String> usernames = users.stream()
                .map(User::getUsername)
                .toList();

        // Then
        assertThat(usernames).containsExactlyElementsOf(ALL_USERNAMES_ASC);
    }

    @Test
    void able_To_Find_Users_Who_Is_Created_Between_A_Period() {

        // Given
        LocalDateTime start = LocalDateTime.of(
                2020, Month.JULY, 1, 0, 0, 0);
        LocalDateTime end = LocalDateTime.of(
                2020, Month.DECEMBER, 31, 0, 0, 0);

        // When
        List<User> users = userRepository.findByCreatedBetween(start, end);

        // Then
        assertThat(users).hasSize(4);
    }

    @ParameterizedTest
    @CsvSource({"mike, mike@somedomain.com, 1",
            "mike, beth@somedomain.com, 0",
            "beth, beth@somedomain.com, 1"})
    void able_Find_Users_By_Username_And_Email(String username, String email, int count) {

        // Given

        // When
        List<User> users = userRepository.findByUsernameAndEmail(username, email);

        // Then
        assertThat(users).hasSize(count);
    }

    @ParameterizedTest
    @CsvSource({"mike, mike@somedomain.com, 1",
            "mike, beth@somedomain.com, 2",
            "beth, beth@somedomain.com, 1"})
    void able_Find_Users_By_Username_Or_Email(String username, String email, int count) {

        // Given

        // When
        List<User> users = userRepository.findByUsernameOrEmail(username, email);

        // Then
        assertThat(users).hasSize(count);
        log.warn(">>> {}", users.toString());
    }

    @ParameterizedTest
    @ValueSource(strings = {"YULIKEXUAN", "JOHN", "MIKE", "JAMES", "KATIE", "BETH", "JULIUS", "DARREN", "MARION", "STEPHANIE", "BURK"})
    void able_To_Find_Users_By_Username_And_Ignore_Case(String username) {

        // Given

        // When
        final List<User> users = userRepository.findByUsernameIgnoreCase(username);

        // Then
        assertThat(users).hasSize(1)
                .extracting(User::getUsername)
                .containsExactly(username.toLowerCase());
    }

    @ParameterizedTest
    @CsvSource({"1, 3, 'yulikexuan,john,burk'", "2, 3, 'marion,darren,beth'"})
    void able_To_Find_All_Users_Having_Specific_Level_And_Ordered_By_Username(
            int level, int size, String names) {

        // Given
        String[] namesArray = names.split(",");

        // When
        List<User> users = userRepository.findByLevelOrderByUsernameDesc(level);

        // Then
        assertThat(users).hasSize(size)
                .extracting(User::getUsername)
                .containsExactly(namesArray);
    }

    @Test
    void able_To_Find_All_Users_Having_Level_Not_Less_Than() {

        // Given
        int level = 3;

        // When
        List<User> users = userRepository.findByLevelGreaterThanEqual(level);

        // Then
        assertThat(users).hasSize(5)
                .extracting(User::getUsername)
                .containsExactly("mike", "james", "katie", "julius", "stephanie");

    }

    @Test
    void able_To_Find_User_By_Username() {

        // Given

        // When
        List<User> usernameContaining =
                userRepository.findByUsernameContaining("ar");
        List<User> usernameLike =
                userRepository.findByUsernameLike("%ar%");
        List<User> usernameStarting =
                userRepository.findByUsernameStartingWith("b");
        List<User> usernameEnding =
                userRepository.findByUsernameEndingWith("ie");

        // Then
        assertThat(usernameContaining).hasSize(2)
                .extracting(User::getUsername)
                .containsExactly("darren", "marion");
        assertThat(usernameLike).hasSize(2)
                .extracting(User::getUsername)
                .containsExactly("darren", "marion");
        assertThat(usernameStarting).hasSize(2)
                .extracting(User::getUsername)
                .containsExactly("beth", "burk");
        assertThat(usernameEnding).hasSize(2)
                .extracting(User::getUsername)
                .containsExactly("katie", "stephanie");
    }

    @Test
    void able_To_Find_Users_By_Active() {

        // Given

        // When
        List<User> activeUsers = userRepository.findByActive(true);
        List<User> inactiveUsers = userRepository.findByActive(false);

        // Then
        assertThat(activeUsers).hasSize(9);
        assertThat(inactiveUsers).hasSize(2);
    }

} ///:~