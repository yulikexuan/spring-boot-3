//: sfg6lab.domain.model.StringTemplatesTest.java

package sfg6lab.domain.model;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static java.lang.StringTemplate.RAW;
import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Test StringTemplate Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StringTemplatesTest {

    @ParameterizedTest
    @CsvSource({"20, 30, '20 + 30 = 50'",
            "10, 90, '10 + 90 = 100'",
            "2, 3, '2 + 3 = 5'"})
    void easy_String_Interpolation_With_STR(int num1, int num2, String expected) {

        // Given

        // When
        String printStr = STR."\{num1} + \{num2} = \{num1 + num2}";
        StringTemplate printStr_Raw = RAW."\{num1} + \{num2} = \{num1 + num2}";

        // Then
        assertThat(printStr).isEqualTo(expected);
        assertThat(STR.process(printStr_Raw)).isEqualTo(expected);
    }

    @Test
    void stringTemplate_Has_Fragments_And_Values() {

        // Given
        var name = "Eric";
        StringTemplate stringTemplate = RAW.">>> My name is \{name}";

        // When
        List<String> fragments = stringTemplate.fragments();
        List<Object> values = stringTemplate.values();

        // Then
        assertThat(fragments).containsExactly(">>> My name is ", "");
        assertThat(values).containsExactly("Eric");
    }


} ///:~