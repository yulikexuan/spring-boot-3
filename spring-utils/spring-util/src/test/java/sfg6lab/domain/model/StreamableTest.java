//: sfg6lab.domain.model.StreamableTest.java


package sfg6lab.domain.model;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Streamable;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@Slf4j
@ExtendWith(MockitoExtension.class)
@DisplayName("Test Streamable Util Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StreamableTest {

    @Test
    void streamable_Can_Be_Empty() {

        // Given
        Streamable<?> emptyStreamable = Streamable.empty();

        // When
        boolean isEmpty = emptyStreamable.isEmpty();

        // Then
        assertThat(isEmpty).isTrue();
    }

    @Test
    void able_To_Combine_Two_Iterables_Into_One_Stream() {

        // Given
        Iterable<String> names_1 = List.of("A", "B", "C");
        Iterable<String> names_2 = List.of("X", "Y", "Z");

        // When
        List<String> names = Streamable
                .of(names_1)
                .and(names_2)
                .stream()
                .toList();

        // Then
        assertThat(names).containsExactly(
                "A", "B", "C", "X", "Y", "Z");
        assertThrows(UnsupportedOperationException.class, () -> { names.add("D"); });
    }

}///:~