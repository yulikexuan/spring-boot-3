//: sfg6lab.domain.model.FormatProcessotTest.java

package sfg6lab.domain.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import java.util.FormatProcessor;

import static java.util.FormatProcessor.FMT;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Test FormatProcessor Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class FormatProcessotTest {

    @Test
    void format_Processor_Uses_Formatter() {

        // Given
        int x = 200;
        int y = 300;

        // When
        var result = FMT."'%4d\{x} + %4d\{y} = %4d\{x + y}'";

        // Then
        assertThat(result).isEqualTo("' 200 +  300 =  500'");
    }

} ///:~