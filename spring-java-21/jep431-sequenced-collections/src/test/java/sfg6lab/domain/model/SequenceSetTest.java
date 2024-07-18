//: sfg6lab.domain.model.SequenceSetTest.java

package sfg6lab.domain.model;


import com.google.common.collect.Sets;
import org.junit.jupiter.api.*;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.SequencedSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Test SequenceSet - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SequenceSetTest {

    private LinkedHashSet<Integer> set;

    @BeforeEach
    void setUp() {
        set = Sets.newLinkedHashSet(List.of(1, 2, 3));
    }

    @Test
    void linked_Hash_Set_Has_Sequence() {

        // Given

        // When
        SequencedSet<Integer> sset = set.reversed();

        // Then
        assertThat(sset).containsExactly(3, 2, 1);

        // When
        sset.removeFirst();
        sset.removeLast();

        // Then
        assertThat(sset).containsExactly(2);
    }

    @Test
    void reverse_Set() {

        // Given
        Set aset = Set.of(1, 2, 3, 4, 5);

        SequencedSet<Integer> initSet = Sets.newLinkedHashSet(aset);
        System.out.println(initSet);

        System.out.println(initSet.reversed());
        System.out.println(initSet.reversed());
        System.out.println(initSet.reversed());
    }

} ///:~