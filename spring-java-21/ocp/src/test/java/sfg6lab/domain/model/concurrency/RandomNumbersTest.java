//: sfg6lab.domain.model.concurrency.RandomNumbersTest.java

package sfg6lab.domain.model.concurrency;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.concurrent.ThreadLocalRandom;

import org.junit.jupiter.api.Test;


class RandomNumbersTest {
    
    @Test
    void limited_Random_Numbers() {
        
        // Given
        // When
        boolean hasNoMinusNum = ThreadLocalRandom.current().ints(
                100, 0, 100)
                .filter(i -> i < 0)
                .findAny()
                .isEmpty();
        
        // Then
        assertThat(hasNoMinusNum).isTrue();
    }
    
} /// :~