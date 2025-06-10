//: sfg6lab.domain.model.ParallelStreamFndingFirstTest.java

package sfg6lab.domain.model;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;


class ParallelStreamFindingFirstTest {
    
    private static List<List<Integer>> data;
    
    @BeforeAll
    static void beforeAll() {
        data = List.of(
                List.of(1, 2),
                List.of(3, 4),
                List.of(5, 6));
    }
    
    @RepeatedTest(10)
    void always_Return_1_From_FindFirst() {
        
        // Given
        
        // When
        int firstElement = findFirst().get();
        
        // Then
        assertThat(firstElement).isOne();
    }
    
    private static Optional<Integer> findFirst() {
        
        /*
         * Stream::findFirst will always return the first element
         * in the original order of the source data, regardless of parallelism
         */
        return data.parallelStream()
                .flatMap(List::stream)
                .findFirst();
    }
    
} /// :~