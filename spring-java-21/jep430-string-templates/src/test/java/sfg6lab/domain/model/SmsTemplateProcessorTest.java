//: sfg6lab.domain.model.SmsTemplateProcessorTest.java

package sfg6lab.domain.model;


import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static java.lang.StringTemplate.RAW;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("Test sfg6lab.domain.model.SmsTemplateProcessor Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class SmsTemplateProcessorTest {


    @BeforeEach
    void setUp() {
    }

    @Test
    void sms_Should_Not_Have_More_Than_256_Charactors() {

        // Given
        String customerName = RandomStringUtils.randomAlphanumeric(7);
        String orderId = UUID.randomUUID().toString();
        String courier = RandomStringUtils.randomAlphanumeric(128);
        String url = RandomStringUtils.randomAlphanumeric(128);

        // When
        StringTemplate sms = RAW."""
                Hi \{customerName}, your order \{orderId} has shipped via \{courier};
                You can track your order here: \{url}.
                """;

        // Then
        assertThatThrownBy(() -> SmsTemplateProcessor.SMS.process(sms))
                .isExactlyInstanceOf(RuntimeException.class)
                .hasMessageContaining(
                        ">>> The length of sms is too long - ");
    }

}
