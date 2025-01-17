//: sfg6lab.ai.rag.service.OpenAiService.java

package sfg6lab.ai.rag.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;
import sfg6lab.ai.rag.domain.model.Answer;
import sfg6lab.ai.rag.domain.model.Question;


public interface OpenAiService {

    Answer getAnswer(Question question);
}


@Service
@RequiredArgsConstructor(staticName = "of")
final class OpenAiServiceImpl implements OpenAiService {

    private final ChatModel chatModel;

    @Override
    public Answer getAnswer(Question question) {

        PromptTemplate promptTemplate =
                new PromptTemplate(question.question());

        Prompt prompt = promptTemplate.create();

        ChatResponse chatResponse = chatModel.call(prompt);

        return new Answer(chatResponse.getResult().getOutput().getContent());
    }

}
