//: sfg6lab.domain.model.SequenceMapTest.java

package sfg6lab.domain.model;


import com.google.common.collect.Maps;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.SequencedMap;

import static java.util.Map.entry;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Test SequenceMap - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class SequenceMapTest {

    @Test
    void linked_HashMap_Is_A_SequenceMap() {

        // Given
        LinkedHashMap<String, Integer> map = Maps.newLinkedHashMap();

        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);

        // When

        // Then
        assertThat(map).containsExactly(
                entry("a", 1),
                entry("b", 2),
                entry("c", 3));

        // When
        SequencedMap<String, Integer> smap = map.reversed();

        // Then
        assertThat(smap).containsExactly(
                entry("c", 3),
                entry("b", 2),
                entry("a", 1));

        // When
        assertThat(smap.firstEntry()).isEqualTo(entry("c", 3));
        assertThat(smap.lastEntry()).isEqualTo(entry("a", 1));

        // When
        smap.pollFirstEntry();
        smap.pollLastEntry();

        // Then
        assertThat(smap).containsExactly(entry("b", 2));

        // When
        smap.putFirst("z", 100);
        smap.putLast("a", 0);

        // Then
        assertThat(smap).containsExactly(
                entry("z", 100), entry("b", 2), entry("a", 0));

    }

} ///:~