//: sfg6lab.domain.model.PointTest.java

package sfg6lab.domain.model;


import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Test Pattern Matching of Java 21 - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class PatternMatchingTest {

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @CsvSource({"2, 3, 'Point(2, 3)'",
            "20, 30, 'Point(20, 30)'",
            "12, 23, 'Point(12, 23)'"})
    void able_To_Match_Point_With_Record_Pattern(int x, int y, String info) {

        // Given
        Object object = new Point(x, y);

        // When
        String actualInfo = generateInfo(object);

        // Then
        assertThat(actualInfo).isEqualTo(info);
    }

    @Test
    void record_Pattern_Composes_Type_Patterns() {

        // Given
        String leftStr = randomAlphanumeric(7);
        String rightStr = randomAlphanumeric(17);

        ObjectPair stringPair = new ObjectPair(leftStr, rightStr);

        // When
        String actualObjInfo = generateObjInfo(stringPair);

        // Then
        assertThat(actualObjInfo).isEqualTo(leftStr + " " + rightStr);

        // Given
        int x = 10;
        int y = 20;
        String expected = "10 + 20 = 30";

        ObjectPair intPair = new ObjectPair(x, y);

        // When
        actualObjInfo = generateObjInfo(intPair);

        // Then
        assertThat(actualObjInfo).isEqualTo(expected);

        // Given
        String name = randomAlphanumeric(7);
        int age = 20;
        expected = name + " : " + age;

        // When
        actualObjInfo = generateObjInfo(new ObjectPair(name, age));

        // Then
        assertThat(actualObjInfo).isEqualTo(expected);
    }

    private String generateInfo(Object object) {
        if (object instanceof Point(int field1, int field2)) {
            return "Point(%d, %d)".formatted(field1, field2);
        }
        throw new UnsupportedOperationException();
    }

    private String generateObjInfo(ObjectPair objectPair) {
        if (objectPair instanceof ObjectPair(String left, String right)) {
            return "%s %s".formatted(left, right);
        } else if (objectPair instanceof ObjectPair(Integer x, Integer y)) {
            return "%d + %d = %d".formatted(x, y, x + y);
        } else if (objectPair instanceof ObjectPair(String name, Integer age)) {
            return "%s : %d".formatted(name, age);
        }
        throw new UnsupportedOperationException();
    }
}
