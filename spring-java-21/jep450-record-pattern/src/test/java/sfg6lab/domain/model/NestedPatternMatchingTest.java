//: sfg6lab.domain.model.UserTest.java

package sfg6lab.domain.model;


import lombok.NonNull;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test Nested Pattern Matching - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class NestedPatternMatchingTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void able_To_Match_User_With_Nested_Records() {

        // Given
        String userTemplate = """
                Username: %s, 
                Full name: %s %s, 
                Address Line: %s, 
                City: %s, 
                Zip Code: %s
                """;
        String username = "yulikexuan";
        String firstName = "Mike";
        String lastName = "Lee";

        String homeLine1 = "265 New Streat";
        String homeCity = "New York City";
        String homeZip = "123abc";

        User user = new User(
                username,
                new Name(firstName, lastName, null, null),
                new Address(homeLine1, null, homeCity, homeZip),
                null);

        // When
        String userInfo = matchNestedRecord(user, userTemplate);

        // Then
        assertThat(userInfo).isEqualTo("""
                Username: yulikexuan,
                Full name: Mike Lee,
                Address Line: 265 New Streat,
                City: New York City,
                Zip Code: 123abc
                """);
    }

    private String matchNestedRecord(
            @NonNull Object obj, @NonNull String userTemplate) {
        if (obj instanceof User(
                String username,
                Name(String firstName,
                     String lastName,
                     String middleName,
                     String title),
                Address(String addressLine1,
                        String addressLine2,
                        String city,
                        String zip),
                Address workAddress)) {

            return userTemplate.formatted(
                    username, firstName, lastName, addressLine1, city, zip);
        }
        throw new UnsupportedOperationException();
    }
}