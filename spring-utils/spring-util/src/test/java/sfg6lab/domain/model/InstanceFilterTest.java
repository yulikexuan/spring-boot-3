//: sfg6lab.domain.model.InstanceFilterTest.java


package sfg6lab.domain.model;


import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.util.InstanceFilter;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Test InstanceFilter Util Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class InstanceFilterTest {

    // InstanceFilter:
    //   A simple instance filter that checks if a given instance match
    //   based on a collection of includes and excludes element

    private InstanceFilter<String> filter;

    private List<String> includes;

    private List<String> excludes;

    @ParameterizedTest
    @CsvSource({"A1, true", "B1, true", "C1, true",
            "A2, false", "B3, false", "C4, false"})
    void test_Includes_Only(String candidate, boolean expectedMatch) {

        // evaluation = matchIncludes

        // Given
        this.includes = List.of("A1", "B1", "C1");
        this.filter = new InstanceFilter<>(includes, null, false);

        // When
        boolean actualMatch = this.filter.match(candidate);

        // Then
        assertThat(actualMatch).isEqualTo(expectedMatch);
    }

    @ParameterizedTest
    @CsvSource({"A1, true", "B1, true", "C1, true",
            "A2, false", "B3, true", "C4, true"})
    void test_Excludes_Only(String candidate, boolean expectedMatch) {

        // evaluation = !matchExcludes;

        // Given
        this.excludes = List.of("A2", "B2", "C2");
        this.filter = new InstanceFilter<>(
                null, this.excludes, false);

        // When
        boolean actualMatch = this.filter.match(candidate);

        // Then
        assertThat(actualMatch).isEqualTo(expectedMatch);
    }

    @ParameterizedTest
    @CsvSource({"A1, false", "B1, true", "C1, true",
            "A2, false", "B2, false", "C2, false",
            "A3, false", "B3, false", "C3, false"})
    void test_Having_Both_Includes_And_Excludes(String candidate, boolean expectedMatch) {

        // evaluation = matchIncludes && !matchExcludes;

        // Given
        this.includes = List.of("A1", "B1", "C1");
        this.excludes = List.of("A1", "B2", "C3");
        this.filter = new InstanceFilter<>(
                this.includes, this.excludes, false);

        // When
        boolean actualMatch = this.filter.match(candidate);

        // Then
        assertThat(actualMatch).isEqualTo(expectedMatch);
    }

}///:~