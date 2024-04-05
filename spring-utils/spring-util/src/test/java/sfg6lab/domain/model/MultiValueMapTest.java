//: sfg6lab.domain.model.MultiValueMapTest.java


package sfg6lab.domain.model;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;


@DisplayName("Test MultiValueMap Util Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class MultiValueMapTest {

    private MultiValueMap<String, Integer> multiIntMap;

    @BeforeEach
    void setUp() {
        this.multiIntMap = new LinkedMultiValueMap<>();
    }

    @Test
    void able_To_Have_Null_Key_And_Null_Value_In_List() {

        // Given
        List<Integer> temp = Lists.newArrayList();
        temp.add(null);

        // When
        this.multiIntMap.addAll("first", List.of(1, 2, 3));
        this.multiIntMap.add(null, null);

        var firstVal = this.multiIntMap.getFirst("first");

        // Then
        assertThat(this.multiIntMap).hasSize(2)
                .containsEntry("first", List.of(1, 2, 3))
                .containsEntry(null, temp);

        assertThat(firstVal).isEqualTo(1);
    }

    @Test
    void able_To_Quickly_Convert_Single_Value_Map_To_Multi_Value_Map() {

        // Given
        Map<String, Integer> singleValMap = Map.ofEntries(
                Map.entry("first", 9),
                Map.entry("second", 7),
                Map.entry("third", 5));

        // When
        this.multiIntMap.setAll(singleValMap);

        // Then
        assertThat(this.multiIntMap).hasSize(singleValMap.size()).contains(
                Map.entry("first", List.of(9)),
                Map.entry("second", List.of(7)),
                Map.entry("third", List.of(5)));

    }

}///:~