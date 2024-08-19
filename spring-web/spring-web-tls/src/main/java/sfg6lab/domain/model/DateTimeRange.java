//: sfg6lab.domain.model.DateTimeRange.java

package sfg6lab.domain.model;


import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


public record DateTimeRange(
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime start,
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime end) {

}
