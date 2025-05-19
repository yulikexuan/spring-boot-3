//: sfg6lab.domain.model.StringIndentationTest.java

package sfg6lab.domain.model;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


@DisplayName("Test Indentation of String  - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StringIndentationTest {
    
    private static final String LINE_BREAK = "\n";
    
    @ParameterizedTest
    @ValueSource(strings = {"Testing normalizing when Indenting\r",
            "Testing normalizing when Indenting"})
    void indentation_Can_Also_Do_Normalizing(String data) {

        // Given
        assertThat(data).doesNotEndWith(LINE_BREAK);

        // When
        var output = data.indent(4);

        // Then
        assertThat(output).endsWith(LINE_BREAK);
        assertThat(output.startsWith("    ")).isTrue();
    }
    
    @Test
    void strip_Indentation_Does_Do_Normalizing() {
    
        // Given
        String data = "       Will be stripped";
        
        // When
        String output = data.stripIndent();
        
        // Then
        assertThat(output).doesNotEndWith(LINE_BREAK);
        assertThat(output).startsWith("W");
    }
    
} /// :~