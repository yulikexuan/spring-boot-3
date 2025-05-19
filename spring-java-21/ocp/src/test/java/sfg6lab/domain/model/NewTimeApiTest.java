//: sfg6lab.domain.model.NewTimeApiTest.java

package sfg6lab.domain.model;


import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;


@DisplayName("Test New Date and Time API of Java  - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class NewTimeApiTest {
    
    @Test
    void change_LocalDate_To_LocalDateTime() {
    
        // Given
        var today = LocalDate.now();
        
        // When
        var nowTime = today.atTime(LocalTime.now());
        
        // Then
        assertThat(nowTime).isExactlyInstanceOf(LocalDateTime.class);
    }
    
    @Test
    void change_LocalTime_To_LocalDateTime() {
    
        // Given
        var now = LocalTime.now();
        
        // When
        var today = now.atDate(LocalDate.now());
        
        // Then
        assertThat(today).isExactlyInstanceOf(LocalDateTime.class);
    }
    
    @Test
    void change_LocalDateTime_To_ZonedDateTime() {
    
        // Given
        var rightNow = LocalDateTime.now();
        
        // When
        var rightNowInTokyo =
                rightNow.atZone(ZoneId.of("Asia/Tokyo"));
    
        // Then
        assertThat(rightNowInTokyo).isExactlyInstanceOf(ZonedDateTime.class);
    }
    
    @Test
    void change_Month_of_A_Given_Date_Of_the_Last_Day_In_A_Month_Then_Still_In_Last_Day() {
    
        // Given
        var lastDayOfOct = LocalDate.of(2025, Month.OCTOBER, 31);
        
        // When
        var lastDayOfNov = lastDayOfOct.plusMonths(1);
        
        // Then
        assertThat(lastDayOfNov).hasDayOfMonth(30);
        assertThat(lastDayOfNov).hasMonth(Month.NOVEMBER);
    }
    
} /// :~