//: sfg6lab.domain.model.PassingByValueTest.java

package sfg6lab.domain.model;


import static org.assertj.core.api.Assertions.assertThat;
import static sfg6lab.domain.model.PassingByValue.INITIAL_NUMBER;
import static sfg6lab.domain.model.PassingByValue.ORIGINAL_1;
import static sfg6lab.domain.model.PassingByValue.ORIGINAL_2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;


@DisplayName("Test Pass-By-Value - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class PassingByValueTest {

    @BeforeEach
    void setUp() {
    }
    
    @Test
    void num_Never_Changed() {
    
        // Given
        
        // When
        var actualNum = PassingByValue.number();
        
        // Then
        assertThat(actualNum).isEqualTo(INITIAL_NUMBER);
        
        // Given
        
        // When
        var newNums = PassingByValue.doSwap();
        
        // Then
        assertThat(newNums).containsExactly(ORIGINAL_1, ORIGINAL_2);
    }
    
    @Test
    void auto_Boxing() {
    
        // Given
        int num = 9;
        
        // When
        // Long longer = num;
    }
    
}
