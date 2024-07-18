//: sfg6lab.domain.model.TypePatternMatchingSwitchTest.java

package sfg6lab.domain.model;


import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Test Generic Record Matching - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TypePatternMatchingSwitchTest {

    private ThreadLocalRandom random;

    @BeforeEach
    void setUp() {
        random = ThreadLocalRandom.current();
    }

    @Test
    void pattern_Matching_Simplified_By_Switch() {

        // Given
        String name = randomAlphanumeric(7);
        Integer number = random.nextInt(1000, 9999);
        Double pie = Math.PI;
        LocalDateTime dateTime = LocalDateTime.of(
                2024, 7, 9, 11, 58, 0);

        assertAll(
                () -> assertThat(formatDataSwitch(name)).isEqualTo("Name: " + name),
                () -> assertThat(formatDataSwitch(number)).isEqualTo("Number:     " + number),
                () -> assertThat(formatDataSwitch(pie)).isEqualTo("Double: 3.1416"),
                () -> assertThat(formatDataSwitch(dateTime)).isEqualTo("Start: 2024-07-09T11:58"),
                () -> assertThat(formatDataSwitch(null)).isEqualTo("[Other] : null"),
                () -> assertThat(formatDataSwitch(Duration.ofMillis(1000))).startsWith("[Default] : "));
    }

    // Concise !!!
    private String formatDataSwitch(Object input) {
        return switch (input) {
            case String s -> "Name: %s".formatted(input);
            case Integer i -> "Number: %8d".formatted(i);
            case Double d -> "Double: %.4f".formatted(d);
            case LocalDateTime dt -> "Start: %s".formatted(dt);
            case null -> "[Other] : %s".formatted(null);
            default -> "[Default] : %s".formatted(input.toString());
        };
    }

    // Verbose !!!
    private String formatData(Object data) {

        String template = null;

        if (data instanceof String) {
            template = "Name: %s";
        } else if (data instanceof Integer) {
            template = "Number: %8d";
        } else if (data instanceof Double) {
            template = "Double: %.4f";
        } else if (data instanceof LocalDateTime) {
            template = "Start: %s".formatted(data);
        } else {
            return "Unknown type";
        }

        return template.formatted(data);
    }

    @Test
    void able_To_Use_Guarded_Case_Label_To_Narrow_Down_Pattern_Matching() {

        // Given
        List<User> users = Lists.newArrayList(
                new User(randomAlphanumeric(7), 3, false),
                new User(randomAlphanumeric(7), 10, false),
                new User(randomAlphanumeric(7), 22, true),
                new User(randomAlphanumeric(7), 40, false),
                new User(randomAlphanumeric(7), 55, false),
                new User(randomAlphanumeric(7), 80, true),
                null);

        // When
        String userInfo = users.stream()
                .map(this::guardedPatternMatching)
                .collect(joining("; "));

        var count = StringUtils.countMatches(userInfo, "is VIP? true");

        // Then
        assertThat(count).isOne();
    }

    private String guardedPatternMatching(User user) {

        return switch (user) {
            case User u when u.age() < 5 -> "Baby %s is not VIP"
                    .formatted(u.username());
            case User u when u.age() >= 5 && u.age() < 18 -> "Kid %s is not VIP"
                    .formatted(u.username());
            case User u when u.age() >= 18 && u.age() < 55 -> "Adult %s is VIP? %b"
                    .formatted(u.username(), u.vip());
            case User u when u.age() >= 55 && u.age() < 75 -> "Senior %s is VIP? %b"
                    .formatted(u.username(), u.vip());
            case null, default -> "Unknown user is not allowed";
        };
    }

} ///:~