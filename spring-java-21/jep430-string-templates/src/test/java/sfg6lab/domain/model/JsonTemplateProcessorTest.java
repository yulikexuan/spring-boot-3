//: sfg6lab.domain.model.JsonTemplateProcessorTest.java

package sfg6lab.domain.model;


import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static sfg6lab.domain.model.JsonTemplateProcessor.JSON;


@DisplayName("Test sfg6lab.domain.model.JsonTemplateProcessor Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class JsonTemplateProcessorTest {


    @BeforeEach
    void setUp() {
    }

    @Test
    void able_To_Generate_JsonNode_By_Processing_Json_Template()
            throws Exception {

        // Given
        var name = RandomStringUtils.randomAlphanumeric(7);
        var email = STR."\{name}@example.com";

        // When
        var node = JSON."""
                {
                    "name": "\{name}",
                    "email": "\{email}"
                }
                """;

        // Then
        assertThat(node.get("name").asText()).isEqualTo(name);
        assertThat(node.get("email").asText()).isEqualTo(email);
    }

}
