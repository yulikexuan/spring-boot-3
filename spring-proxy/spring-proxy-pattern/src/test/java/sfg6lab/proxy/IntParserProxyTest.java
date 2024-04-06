//: sfg6lab.proxy.IntParserProxyTest.java

package sfg6lab.proxy;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import sfg6lab.domain.model.IntParser;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test IntParserProxyTest - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class IntParserProxyTest {

    private IntParser intParser;

    @BeforeEach
    void setUp() {
        this.intParser = new IntParser();
    }

    @Test
    void proxy_Only_Works_On_Directly_Method_Call() {

        // Given
        IntParser proxy = IntParserProxy.of(this.intParser);

        // When
        int number = proxy.parseInt("  123  ", 10);

        // Then
        assertThat(number).isEqualTo(123);
    }

    @Test
    void proxy_Does_Not_Work_On_Indirectly_Method_Call() {

        // Given
        IntParser proxy = IntParserProxy.of(this.intParser);

        // When
        int number = proxy.parseInt("  123  ");

        // Then
        assertThat(number).isNotEqualTo(123);
    }

}///:~