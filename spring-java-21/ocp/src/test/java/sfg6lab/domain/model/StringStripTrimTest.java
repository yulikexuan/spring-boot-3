//: sfg6lab.domain.model.StringStripTrimTest.java

package sfg6lab.domain.model;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


class StringStripTrimTest {
    
    private static final String DATA_DISPLAY_TEMPLATE = """
            Testing Data: "%s"
            """;
    
    @ParameterizedTest
    @CsvSource({
            "'  Hello World  ', 'Hello World', true",
            "'\t   a b c \n', 'a b c', true",
            "'\t   d e f \n\r', 'd e f', true",
            "'\t   d e f \u2000', 'd e f', false",
    })
    void removing_Whitespace_From_String(
            String source, String expected, boolean asExpected) {
    
        // Given
        System.out.println(DATA_DISPLAY_TEMPLATE.formatted(source));
        
        // When
        var outputWithStrip = source.strip();
        
        // String.trim() Only removes characters that are ≤ U+0020 (space character).
        var outputWithTrim = source.trim();
        boolean actuallyAsExpected = outputWithStrip.equals(expected) &&
                outputWithTrim.equals(expected);
        
        System.out.println("Is '\u2000' whitespace for Character? " +
                Character.isWhitespace('\u2000'));
        
        System.out.println("Is '\u0000' whitespace for Character? " +
                Character.isWhitespace('\u0000'));
        
        // Then
        assertThat(actuallyAsExpected).isEqualTo(asExpected);
    }
    
    @Test
    void null_Is_Not_Whitespace_By_Character_But_Removed_By_Trim() {
    
        // Given
        String data = "Having null in the end " + "\u0000";
        
        // When
        var outputWithStrip = data.strip();
        var outputWithTrim = data.trim();
        
        // Then
        assertThat(outputWithStrip).isNotEqualTo(outputWithTrim);
    }
    
} /// :~