//: sfg6lab.domain.model.InstantApiTest.java

package sfg6lab.domain.model;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;


@DisplayName("Test Instant class API of Java 21  - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class InstantApiTest {
    
    @Test
    void instant_Works_With_Duration() {
    
        // Given
        var now = Instant.now();
        var afterAWhile = now.plus(Duration.ofMinutes(1));
        
        // When
        var duration = Duration.between(now, afterAWhile);
        
        // Then
        assertThat(duration).hasToString("PT1M");
    }
    
    @Test
    void able_To_Turn_ZonedDateTime_To_Instant() {
    
        // Given
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        ZoneId zoneId = ZoneId.of("US/Eastern");
        
        var rightNow = ZonedDateTime.of(today, now, zoneId);
        var rightNowInfo = rightNow.toString();
        
        // When
        var rightNowInstant = rightNow.toInstant();
        var rightNowGmtInfo = rightNowInstant.toString();
        
        // Then
        assertThat(rightNowGmtInfo)
                .doesNotEndWith("-04:00[US/Eastern]")
                .endsWith("Z");
    }
} /// :~