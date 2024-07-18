//: sfg6lab.domain.model.PairTest.java

package sfg6lab.domain.model;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Test Generic Record Matching - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class GenericRecordMatchingTest {

    @Test
    void able_To_Match_String_Pair() {

        // Given
        String name = "yulikexuan";
        String email = "yulikexuan@domain.com";
        String template = """
                name: %s
                email: %s
                """;

        // When
        String actualInfo = matchStringPair(
                new Pair<String, String>(name, email), template);

        // Then
        assertThat(actualInfo).isEqualTo(template.formatted(name, email));
    }

    private String matchStringPair(Pair<String, String> pair, String template) {
        if (pair instanceof Pair(String name, String email)) {
            return template.formatted(name, email);
        }
        throw new UnsupportedOperationException();
    }

    @Test
    void able_To_Match_Raw_Type_Pair() {

        // Given
        int x = 3;
        int y = 2;
        String mathTemplate = "%d + %d = %d";

        // When
        String actualInfo  = matchPair(new Pair(x, y), mathTemplate);

        // Then
        assertThat(actualInfo).isEqualTo("3 + 2 = 5");

        // Given
        String userTemplate = """
                name: %s
                email: %s
                """;

        // When
        String username = "yulikexuan";
        String email = "yulikexuan@domain.com";
        actualInfo = matchPair(new Pair<>(username, email), userTemplate);

        // Then
        assertThat(actualInfo).isEqualTo(userTemplate.formatted(username, email));
    }

    @Test
    void able_To_Match_Generic_Embeded_Record_Pattern() {

        // Given
        var start = LocalDateTime.now();
        var end = start.plusHours(1);

        String username = "yulikexuan";
        int age = 55;
        Pair<Pair<String, Integer>, Pair<LocalDateTime, LocalDateTime>> performance =
                new Pair<>(new Pair<>(username, age), new Pair<>(start, end));

        String template = """
                Performance
                  Username: %s
                  Age: %d
                  Start: %s
                  End: %s
                """;

        // When
        String info = matchNestedPair(performance, template);

        // Then
        assertThat(info).isEqualTo(template.formatted(username, age, start, end));
    }

    private String matchPair(Object obj, String template) {
        if (obj instanceof Pair(String param1, String param2)) {
            return template.formatted(param1, param2);
        } else if (obj instanceof Pair(Integer x, Integer y)) {
            return template.formatted(x, y, x + y);
        }
        throw new UnsupportedOperationException();
    }

    private String matchNestedPair(
            Pair<Pair<String, Integer>, Pair<LocalDateTime, LocalDateTime>> performance,
            String template) {

        if (performance instanceof Pair(
                Pair(var username, var age), Pair(var start, var end))) {

            return template.formatted(username, age, start, end);
        }

        throw new UnsupportedOperationException();
    }

} 
