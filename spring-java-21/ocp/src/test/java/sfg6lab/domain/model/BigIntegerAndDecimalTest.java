//: sfg6lab.domain.model.BitIntegerAndDecimalTest.java

package sfg6lab.domain.model;


import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;


@DisplayName("Test BigInteger and BigDecimal  - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class BigIntegerAndDecimalTest {
    
    @Test
    void why_Use_BigDecimal() {
    
        // Given
        double amt = 64.1;
        double amtInCents = amt * 100;
        
        var amtInCentsVal = BigDecimal.valueOf(amt)
                .multiply(BigDecimal.valueOf(100));
        
        // When
        String integerPartFromDouble = Double.toString(amtInCents).substring(0, 4);
        String integerPartFromBigDecimal = amtInCentsVal.toString().substring(0, 4);
        
        // Then
        assertThat(Integer.parseInt(integerPartFromDouble)).isEqualTo(6409);
        assertThat(Integer.parseInt(integerPartFromBigDecimal)).isEqualTo(6410);
    }
    
} /// :~