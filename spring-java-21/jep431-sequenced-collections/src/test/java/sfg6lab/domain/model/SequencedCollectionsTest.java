//: sfg6lab.domain.model.SequencedCollectionsTest.java

package sfg6lab.domain.model;


import org.assertj.core.api.SoftAssertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;


@DisplayName("Test Sequenced Collections - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SequencedCollectionsTest {

    @Test
    void list_Can_Be_Reversed() {

        // Given
        List<Integer> numbers = List.of(1, 2, 3);

        // When & Then
        assertThat(numbers.getFirst()).isOne();
        assertThat(numbers.getLast()).isEqualTo(3);

        // When
        var reversedNumbers = numbers.reversed();

        // Then
        assertAll(() -> assertThat(reversedNumbers).containsExactly(3, 2, 1));

        assertThat(reversedNumbers).containsExactly(3, 2, 1);
        assertThat(reversedNumbers.getFirst()).isEqualTo(3);
        assertThat(reversedNumbers.getLast()).isOne();
    }

    @Test
    void able_To_Add_Elements_To_Both_Ends_Of_Sequence_Collection() {

        // Given
        List<Integer> numbers = Lists.newArrayList(1, 2, 3);

        // Then
        assertThat(numbers.getFirst()).isOne();
        assertThat(numbers.getLast()).isEqualTo(3);

        // When
        numbers.addFirst(0);
        numbers.addLast(4);

        // Then
        SoftAssertions softly = new SoftAssertions();

        assertAll(
                () -> assertThat(numbers.getFirst()).isZero(),
                () -> assertThat(numbers.getLast()).isEqualTo(4));

        // When
        numbers.removeFirst();
        numbers.removeLast();

        // Then
        assertAll(
                () -> assertThat(numbers.getFirst()).isOne(),
                () -> assertThat(numbers.getLast()).isEqualTo(3));
    }


} ///:~