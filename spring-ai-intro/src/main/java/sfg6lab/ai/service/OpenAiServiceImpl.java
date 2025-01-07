//: sfg6lab.ai.service.OpenAiServiceImpl.java

package sfg6lab.ai.service;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class OpenAiServiceImpl implements OpenAiService {

    private final ChatModel chatModel;

    @Override
    public String getAnswer(String question) {

        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse chatResponse = chatModel.call(prompt);

        return chatResponse.getResult().getOutput().getContent();
    }

} /// :~