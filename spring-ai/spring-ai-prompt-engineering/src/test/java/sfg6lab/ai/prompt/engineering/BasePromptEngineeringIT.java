//: sfg6lab.ai.SpringAiPromptEngineeringAppTest.java

package sfg6lab.ai.prompt.engineering;


import lombok.NonNull;
import org.junit.jupiter.api.TestInstance;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;


@SpringBootTest
@TestInstance(PER_CLASS)
abstract class BasePromptEngineeringIT {

    @Autowired
    ChatModel chatModel;

    String chat(String prompt) {

        PromptTemplate promptTemplate = new PromptTemplate(prompt);
        Prompt promptToSend = promptTemplate.create();

        return getContent(chatModel.call(promptToSend));
    }

    private static String getContent(@NonNull final ChatResponse chatResponse) {
        return chatResponse.getResult().getOutput().getText();
    }

}
