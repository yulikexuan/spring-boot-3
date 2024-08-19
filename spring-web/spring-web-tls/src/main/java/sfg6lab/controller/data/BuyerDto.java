//: sfg6lab.controller.data.BuyerDto.java

package sfg6lab.controller.data;


import java.time.LocalDateTime;


public record BuyerDto(Long id,
                       String name,
                       String email,
                       int level,
                       boolean active,
                       LocalDateTime created) {

} ///:~