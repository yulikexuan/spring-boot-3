//: sfg6lab.controller.InvalidDateTimeRangeArgumentsException.java

package sfg6lab.controller;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;


@Getter
@ToString
@AllArgsConstructor(staticName = "of")
public class InvalidDateTimeRangeArgumentsException extends IllegalArgumentException {

    private final LocalDateTime from;
    private final LocalDateTime to;
    private final String message;

} ///:~