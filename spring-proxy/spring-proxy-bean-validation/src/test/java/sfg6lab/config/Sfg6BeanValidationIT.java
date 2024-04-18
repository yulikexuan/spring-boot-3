//: sfg6lab.config.Sfg6BeanValidationIT.java

package sfg6lab.config;


import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import sfg6lab.domain.model.GeometryProperties;
import sfg6lab.domain.model.Photo;
import sfg6lab.domain.service.FileDownloadingService;
import sfg6lab.domain.service.PhotoService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import static org.mockito.BDDMockito.given;


@Slf4j
@SpringBootTest(
        classes = { Sfg6AppCfg.class },
        useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE)
@DisplayName("Test Spring Bean Validation - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class Sfg6BeanValidationIT {

    @Nested
    class ConfigurationPropertiesValidation {

        @Autowired
        private GeometryProperties geometryProperties;

        @Test
        void geometry_Properties_Is_Available() {
            assertThat(this.geometryProperties.circle()).isNotNull();
            assertThat(this.geometryProperties.rectangle()).isNotNull();
        }

    }

    @Nested
    class SpringValidationIT {

        @MockBean
        private FileDownloadingService fileDownloadingService;

        @Autowired
        private PhotoService photoService;

        @BeforeEach
        void setUp() {
        }

        @ParameterizedTest
        @CsvSource({
                "1111, 2222, yul, true, 2024-04-12T18:15:41",
                "-9999, 4444, berry, false, 2024-04-11T18:15:41"
        })
        void downloadPhoto_Should_Return_Optional_Of_Byte_Array_When_Photo_Is_Valid(
                Long id,
                long profile,
                String name,
                boolean isProfilePhoto,
                LocalDateTime created) {

            // Given
            if (id == -9999) id = null;
            Photo photo = new Photo(id, profile, name, isProfilePhoto, created);

            String uri = "https://example.com/photo.jpg";
            byte[] photoData = {0x01, 0x02, 0x03};

            given(this.fileDownloadingService.download(name))
                    .willReturn(Optional.of(photoData));

            // When
            var actualPhotoData = photoService.download(photo);

            // Then
            assertThat(actualPhotoData).hasValue(photoData);
        }

        @Test
        void downloadPhoto_Throw_ConstraintViolationException_When_Photo_IsInvalid() {

            // Given
            var id = 1111L;
            var name = "yul";
            int profile = 0;
            var isProfilePhoto = false;
            var createdTime = LocalDateTime.parse("2024-04-12T18:15:41");

            // When
            Photo photo = new Photo(id, profile, name, isProfilePhoto, createdTime);

            // Then
            String errMsg = "profile: must be greater than or equal to 1";
            assertThatThrownBy(() -> photoService.download(photo))
                    .isExactlyInstanceOf(ConstraintViolationException.class)
                    .hasMessageEndingWith(errMsg)
                    .extracting(
                            throwable -> ((ConstraintViolationException) throwable)
                                    .getConstraintViolations(),
                            as(InstanceOfAssertFactories.COLLECTION))
                    .hasSize(1)
                    .first(InstanceOfAssertFactories.type(ConstraintViolation.class))
                    .satisfies(vio -> {
                        assertThat(vio.getRootBeanClass()).isSameAs(PhotoService.class);
                        assertThat(vio.getLeafBean()).isExactlyInstanceOf(Photo.class);
                        assertThat(vio.getPropertyPath()).hasToString(
                                "download.photo.profile");
                        assertThat(vio.getInvalidValue()).isEqualTo(0L);
                    });

        }

    }

    @Nested
    class ManuallyValidationIT {

        @Autowired
        private Validator validator;

        @ParameterizedTest
        @CsvSource({
                "1111, 2222, yul, true, 2024-04-12T18:15:41, true",
                "-9999, 4444, berry, false, 2024-04-11T18:15:41, true",
                "1111, 0, yul, true, 2024-04-12T18:15:41, false"})
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
            final var violations = validator.validate(photo);
            log.debug(">>> Violations: {}", violations);
            boolean actuallyHasNoViolations = violations.isEmpty();

            // Then
            assertThat(actuallyHasNoViolations).isEqualTo(hasNoViolations);
        }
    }

}///:~