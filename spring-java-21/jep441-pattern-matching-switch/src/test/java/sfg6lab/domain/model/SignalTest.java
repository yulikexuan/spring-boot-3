//: sfg6lab.domain.model.SignalTest.java

package sfg6lab.domain.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static sfg6lab.domain.model.TrafficLight.*;


@DisplayName("Test sfg6lab.domain.model.Signal Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class SignalTest {


    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @CsvSource({"GREEN, '>>> Go!'", "YELLOW, '>>> Wait!'", "RED, '>>> Stop!'",})
    void explain_Traffic_Lights(TrafficLight signal, String explanation) {

        // Given

        // When
        String actualExplanation = explainSignal(signal);

        // Then
        assertThat(actualExplanation).isEqualTo(explanation);
    }

    private String explainSignal(Signal signal) {

        return switch (signal) {
            case GREEN -> ">>> Go!";
            case YELLOW -> ">>> Wait!";
            case RED -> ">>> Stop!";
            case null, default -> ">>> Traffic Lights are Unfunctional!";
        };
    }

}
