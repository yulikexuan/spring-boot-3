//: sfg6lab.config.Sfg6JdbcRepositoryIT.java

package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import sfg6lab.domain.model.User;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


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

    @Test
    void able_To_Find_Users_By_Created_Time_Which_Is_In_Or_Out_Of_List() {

        // Given
        List<LocalDateTime> dates = List.of(
                LocalDateTime.of(
                        2020, Month.JANUARY, 18,
                        0, 0, 0),
                LocalDateTime.of(
                        2021, Month.JANUARY, 5,
                        0, 0, 0));

        // When
        List<User> includedUsers = userRepository.findByCreatedIn(dates);
        List<User> excludedUsers = userRepository.findByCreatedNotIn(dates);

        // Then
        assertAll(
                () -> assertThat(includedUsers).hasSize(3),
                () -> assertThat(excludedUsers).hasSize(8));
    }

    @Test
    void able_To_Get_The_First_User_Whose_Name_Is_At_First_In_Alphabetical_Order() {

        // Given

        // When
        Optional<User> firstUser = userRepository.findFirstByOrderByUsernameAsc();

        // Then
        assertThat(firstUser).hasValueSatisfying(
                user -> assertThat(user.getUsername())
                        .isEqualTo(ALL_USERNAMES_ASC.getFirst()));
    }

    @Test
    void able_To_Get_Users_On_Pages() {

        // Given

        // When
        Page<User> userPage = userRepository.findAll(PageRequest.of(0, 3));
        List<String> usernames = userPage.map(u -> u.getUsername()).stream().toList();

        // Then
        assertThat(usernames).containsExactly("yulikexuan", "john", "mike");
    }

    @Test
    void able_To_Find_Out_First_N_Users() {

        // Given
        int level = 2;
        Sort sort = Sort.by(Sort.Order.asc("username"));

        // When
        List<User> first2Users = userRepository.findFirst2ByLevel(level, sort);
        List<String> usernames = first2Users.stream().map(User::getUsername).toList();
        Set<Integer> levels = first2Users.stream().map(User::getLevel)
                .collect(Collectors.toSet());

        // Then
        assertAll(() -> assertThat(usernames).containsExactly("beth", "darren"),
                () -> assertThat(levels).containsExactly(2));
    }

    @ParameterizedTest
    @CsvSource({"true, 3", "false, 2"})
    void able_To_Find_Out_All_Users_By_Active_On_Pages(
            boolean active, long count) {

        // Given
        var pageNumber = 0;
        var pageSize = 3;

        // When
        List<User> users = userRepository.findByActive(
                active, PageRequest.of(pageNumber, pageSize));
        long c = users.stream().filter(u -> u.isActive() == active).count();
        long nc = users.stream().filter(u -> u.isActive() == !active).count();

        // Then
        assertAll(() -> assertThat(c).isEqualTo(count),
                () -> assertThat(nc).isZero());
    }

    @Test
    void able_To_Find_Out_Users_By_Specific_Level_Num_And_Sort() {

        // Given

        // When
        int level = 2;
        String propertyName = "username";
        List<User> users = userRepository.findByLevel(
                level, Sort.by(propertyName));
        String names = users.stream()
                .map(User::getUsername)
                .collect(joining(", "));

        // Then
        assertThat(names).isEqualTo("beth, darren, marion");
    }

    @Test
    void able_To_Find_Out_The_User_Created_Most_Recently() {

        // Given

        // When
        Optional<User> latestUserOpt = userRepository.findTopByOrderByCreatedDesc();

        // Then
        assertThat(latestUserOpt).hasValueSatisfying(
                u -> u.getUsername().equals("yulikexuan"));
    }

    @Test
    void able_To_Find_Out_Users_Whose_Email_Contains_Specific_Characters() {

        // Given
        String characters = "kexuan";

        // When
        String names = "";
        try (Stream<User> users = userRepository.findByEmailContaining(characters)
                .stream().distinct()) {

            names = users.map(User::getUsername).collect(joining(""));
        }

        // Then
        assertThat(names).isEqualTo("yulikexuan");
    }

    @Test
    void able_To_Be_Streamable_Of_Users_By_Level() {

        // Given
        var level = 1;

        // When
        Streamable<User> users = userRepository.findByLevel(level);

        try (Stream<User> userStream = users.stream().distinct()) {

            final long nc = userStream.filter(u -> u.getLevel() != level).count();

            // Then
            assertAll(() -> assertThat(nc).isZero(),
                    () -> assertThat(users).hasSize(3));
        }
    }

    @ParameterizedTest
    @CsvSource({"1, 3", "2, 3", "3, 2", "4, 2", "5, 1"})
    void able_To_Know_Number_Of_Users_For_Specific_Level(int level, long count) {

        // Given

        // When
        long actualCount = userRepository.findNumberOfUsersByLevel(level);

        // Then
        assertThat(actualCount).isEqualTo(count);
    }

    @ParameterizedTest
    @CsvSource({"true, 9", "false, 2"})
    void able_To_Know_Number_Of_Users_For_Active_Or_Inactive(
            boolean active, long count) {

        // Given

        // When
        long actualCound = userRepository.findNumberOfUsersByActivity(active);

        // Then
        assertThat(actualCound).isEqualTo(count);
    }

    @ParameterizedTest
    @CsvSource({
            "1, true, yulikexuanjohnburk",
            "1, false, ''",
            "2, true, bethdarren",
            "2, false, marion",
            "3, true, mike",
            "3, false, james",
            "4, true, juliusstephanie",
            "4, false, ''",
            "5, true, katie",
            "5, false, ''"})
    void able_To_Find_Users_By_Level_And_By_Active(
            int level, boolean active, String names) {

        // Given
        String actualNames = "";

        // When
        try (Stream<User> users = userRepository.findByLevelAndActive(level, active)
                .stream()) {

            actualNames = users.map(User::getUsername).collect(joining());
        }

        // Then
        assertThat(actualNames).isEqualTo(names);
    }

    @Test
    void able_To_Update_Level_By_Specific_Username() {

        // Given
        String username = "yulikexuan";

        // When
        int oldLevel = userRepository.findLevelByUsername(username);

        // Given
        int newLevel = oldLevel + 1;

        // When
        int linesChanged = userRepository.updateLevelByUsername(newLevel, username);

        // Then
        assertThat(linesChanged).isOne();

        // When
        int currentLevel = userRepository.findLevelByUsername(username);

        // Then
        assertThat(currentLevel).isEqualTo(newLevel);

        userRepository.updateLevelByUsername(oldLevel, username);
    }

    @Test
    void able_To_Delete_User_By_Username() {

        // Given
        assertThat(userRepository.findByUsername("yulikexuan")).isPresent();

        // When
        userRepository.deleteByUsername("yulikexuan");

        // Then
        assertThat(userRepository.findByUsername("yulikexuan")).isNotPresent();
    }

} ///:~