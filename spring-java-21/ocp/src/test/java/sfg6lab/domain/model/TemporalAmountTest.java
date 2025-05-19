//: sfg6lab.domain.model.PeriodTest.java

package sfg6lab.domain.model;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.time.temporal.UnsupportedTemporalTypeException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("Test Temporal Amount Classes  - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TemporalAmountTest {
    
    @Nested
    @DisplayName("Test Period Class  - ")
    class PeriodTest {
        
        @Test
        void not_Able_To_Chain_Method_Calls() {
            
            // Given
            Period period = Period.of(1, 0, 7);
            var expectedPeriod = "P1Y7D";
            var expectedWrongPeriod = "P7D";
            
            // When
            var wrong = Period.ofYears(1).ofWeeks(1);
            
            // Then
            assertThat(period).isNotEqualTo(wrong);
            assertThat(period.toString()).isEqualTo(expectedPeriod);
            assertThat(wrong.toString()).isEqualTo(expectedWrongPeriod);
        }
        
        @Test
        void period_Is_Not_For_Time() {
            
            // Given
            var period = Period.ofDays(3);
            var now = LocalTime.now();
            
            // When & Then
            assertThatThrownBy(() -> now.plus(period))
                    .isExactlyInstanceOf(UnsupportedTemporalTypeException.class);
        }
    }
    
    @Nested
    @DisplayName("Test Duration Class - ")
    class DurationTest {
        
        @Test
        void class_ChronoUnit_Works_With_Duration() {
            
            // Given
            var one = LocalTime.of(5, 15);
            var two = LocalTime.of(6, 55);
            
            // When
            long durationInHours = ChronoUnit.HOURS.between(one, two);
            long durationInMinutes = ChronoUnit.MINUTES.between(one, two);
            
            // Then
            
            // <between call> truncates rather than rounds
            assertThat(durationInHours).isOne();
            assertThat(durationInMinutes).isEqualTo(100);
        }
        
        @Test
        void truncate_LocalTime() {
        
            // Given
            var time = LocalTime.of(3, 12, 45);
            
            // When
            var roughTime = time.truncatedTo(ChronoUnit.MINUTES);
            
            // Then
            assertThat(roughTime).hasToString("03:12");
        }
        
    }

} /// :~