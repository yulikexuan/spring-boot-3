//: sfg6lab.config.Sfg6AppCfgTest.java

package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sfg6lab.service.RetryTemplateService;
import sfg6lab.service.RetryableService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;


@Slf4j
@SpringBootTest(
        classes = { Sfg6AppCfg.class },
        useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE)
@DisplayName("Test Spring Bean Validation - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class Sfg6AppCfgIT {

    @Autowired
    private RetryableService retryableService;

    @Autowired
    private RetryTemplateService retryTemplateService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void retry_3_Times_By_Default() {

        // Given
        log.debug(">>> Before RandomPhotoService::receive");
        int expectedRetriedTimes = 3;

        // When
        String json = this.retryableService.receive();
        System.out.println(json);

        // Then
        log.debug(">>> After RandomPhotoService::receive: {}", json);

        assertThat(this.retryableService.retriedTimes())
                .isEqualTo(expectedRetriedTimes);
    }

    @Test
    void recover_From_Unsuccessful_Retries() {

        // Given

        // When
        String actualRead = this.retryableService.read();

        // Then
        assertThat(actualRead).isEqualTo("Nothing has been read, sorry!");
    }

    @Test
    void retry_With_RetryTemplate() {

        // Given

        // When
        String data = this.retryTemplateService.receive();

        // Then
        assertThat(data).endsWith(">>> There is nothing to read yet.");
    }

}///:~