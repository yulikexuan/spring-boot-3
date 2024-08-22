//: sfg6lab.controller.data.BuyerDto.java

package sfg6lab.controller.data;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;


public record BuyerDto(Long id,
                       @Length(min = 3, max = 64) String name,
                       @NotNull @Length(min = 5, max = 128) String email,
                       @Positive int level,
                       boolean active,
                       @Past LocalDateTime created) {

} ///:~