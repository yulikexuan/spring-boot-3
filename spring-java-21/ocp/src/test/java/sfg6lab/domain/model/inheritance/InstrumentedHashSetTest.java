//: sfg6lab.domain.model.inheritance.InstrumentedHashSetTest.java

package sfg6lab.domain.model.inheritance;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test sfg6lab.domain.model.inheritance.InstrumentedHashSet Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class InstrumentedHashSetTest {

    private InstrumentedHashSet<String> instrumentedHashSet;

    @BeforeEach
    void setUp() {
        instrumentedHashSet = new InstrumentedHashSet<>();
    }

    @Test
    void will_Be_Double_Counted_With_addAll() {

        // Given
        List<String> newNames = List.of("Mike", "John", "Paul");
        int expectedCount = newNames.size() * 2;

        // When
        instrumentedHashSet.addAll(newNames);

        // Then
        assertThat(instrumentedHashSet.getAddCount()).isEqualTo(expectedCount);
    }

    @Test
    void counted_Properly_With_addAll_Of_FragileFixedInstrumentedHashSet() {

        // Given
        var instrumentedHashSet = new FragileFixedInstrumentedHashSet();
        List<String> newNames = List.of("Mike", "John", "Paul");
        int expectedCount = newNames.size();

        // When
        instrumentedHashSet.addAll(newNames);

        // Then
        assertThat(instrumentedHashSet.getAddCount()).isEqualTo(expectedCount);
    }

    @Test
    void counted_Properly_With_addAll_Of_PerfectlyFixedInstrumentedHashSet() {

        // Given
        var instrumentedHashSet = new PerfectlyFixedInstrumentedHashSet();
        List<String> newNames = List.of("Mike", "John", "Paul");
        int expectedCount = newNames.size();

        // When
        instrumentedHashSet.addAll(newNames);

        // Then
        assertThat(instrumentedHashSet.getAddCount()).isEqualTo(expectedCount);
    }

}
