//: sfg6lab.ai.service.service.OpenAiService.java

package sfg6lab.ai.function.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.model.function.FunctionCallback;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sfg6lab.ai.function.domain.model.Answer;
import sfg6lab.ai.function.domain.model.Question;
import sfg6lab.ai.function.domain.model.WeatherRequest;
import sfg6lab.ai.function.domain.model.WeatherResponse;
import sfg6lab.ai.function.domain.service.WeatherServiceFunction;

import java.util.List;
import java.util.function.Function;


public interface OpenAiService {

    Answer getAnswer(Question question);
}


@Service
@RequiredArgsConstructor(staticName = "of")
final class OpenAiServiceImpl implements OpenAiService {

    private final OpenAiChatModel openAiChatModel;
    private final WeatherServiceFunction weatherFunc;

    @Override
    public Answer getAnswer(Question question) {

        var promptOptions = OpenAiChatOptions.builder()
                .functionCallbacks(List.of(FunctionCallback.builder()
                        .function("CurrentWeather", weatherFunc)
                        .description("Get the current weather for a location")
                        .inputType(WeatherRequest.class)
                        .build()))
                .build();

        Message userMessage = new PromptTemplate(question.question()).createMessage();

        var response = openAiChatModel.call(
                new Prompt(List.of(userMessage), promptOptions));

        return new Answer(response.getResult().getOutput().getContent());
    }

}
