//: sfg6lab.domain.model.Photo.java


package sfg6lab.domain.model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;


public record Photo(

        Long id,

        @Min(1) // the number must always be greater than or equal to 1
        long profile,

        @NotNull
        @Pattern(regexp = "[\\w_-]{1,200}")
        // The name must not be null, and any word character, underscore
        // character, or minus character is allowed
        // and the length must be between 1 and 200
        // The word characters include all digits and all uppercase/lowercase
        // letters—but only the letters of the English alphabet from A to Z
        // without special characters
        String name,

        boolean isProfilePhoto,

        @NotNull
        @Past
        // the value of created should not be null,
        // and must be in the past because photos can not be created in the future
        LocalDateTime created) {

}///:~