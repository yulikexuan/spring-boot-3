//: sfg6lab.ai.service.OpenAiServiceImplTest.java

package sfg6lab.ai.service;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("Test OpenAiServiceImpl Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class OpenAiServiceImplIT {

    private final static String QUESTION_1 = """
            Create JSON for the following: There are 3 people, two males. 
            One is named Mark. Another is named Joe. 
            And a third person is a woman named Sam. 
            The woman is age 20 and the two men are both 19.
            """;

    @Autowired
    private OpenAiService openAiService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void able_To_Get_An_Answer_From_OpenAi() {

        // Given

        // When
        String answer = openAiService.getAnswer(QUESTION_1);

        // Then
        assertThat(answer).isNotBlank();
        log.info(">>> The Question: {}", QUESTION_1);
        log.info(">>> The Answer: {}", answer);
    }
}
