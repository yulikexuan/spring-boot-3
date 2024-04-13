//: sfg6lab.config.Sfg6BeanValidationIT.java

package sfg6lab.config;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sfg6lab.domain.model.Photo;

import java.time.LocalDateTime;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;


@Slf4j
@SpringBootTest(
        classes = { Sfg6AppCfg.class },
        useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE)
@DisplayName("Test Spring Bean Validation - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class Sfg6BeanValidationIT {

    @Autowired
    private Validator validator;

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @CsvSource({"1111, 2222, yul, true, 2024-04-12T18:15:41, true",
            "-9999, 4444, berry, false, 2024-04-11T18:15:41, true"})
    void validate_Photo_Instance(
            Long id,
            long profile,
            String name,
            boolean isProfilePhoto,
            LocalDateTime created,
            boolean hasNoViolations) {

        // Given
        if (id == -9999) id = null;
        Photo photo = new Photo(id, profile, name, isProfilePhoto, created);

        // When
        Set<ConstraintViolation<Photo>> violations =
                this.validator.validate(photo);
        boolean actuallyHasNoViolations = violations.isEmpty();

        // Then
        assertThat(actuallyHasNoViolations).isEqualTo(hasNoViolations);
    }

}///:~