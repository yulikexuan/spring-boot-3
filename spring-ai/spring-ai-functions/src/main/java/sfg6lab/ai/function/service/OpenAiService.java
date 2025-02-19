//: sfg6lab.ai.service.service.OpenAiService.java

package sfg6lab.ai.function.service;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.model.ModelOptionsUtils;
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

    String RESPONSE_DATA_TEMPLATE = "%s\n%s";

    Answer getAnswer(Question question);

    static String convertResonseJson(@NonNull final Object response) {
        String schema = ModelOptionsUtils.getJsonSchema(
                WeatherResponse.class, false);
        String data = ModelOptionsUtils.toJsonString(response);
        return RESPONSE_DATA_TEMPLATE.formatted(schema, data);
    }

}


@Service
@RequiredArgsConstructor(staticName = "of")
final class OpenAiServiceImpl implements OpenAiService {

    static final String CURRENT_WEATHER_FUNC_NAME = "CurrentWeather";
    static final String CURRENT_WEATHER_FUNC_DESC = "Get the current weather for a location";
    static final String INSTRUCTIONS = """
            You are a weather service. 
            You receive weather information from a service which gives you the information based on the metrics system. 
            When answering the weather in an imperial system country, 
            you should convert the temperature to Fahrenheit and the wind speed to miles per hour.
            Also, please make each of your answer line 80 characters or less. 
            """;
    private final OpenAiChatModel openAiChatModel;
    private final WeatherServiceFunction weatherFunc;

    @Override
    public Answer getAnswer(Question question) {

        var promptOptions = OpenAiChatOptions.builder()
                .functionCallbacks(List.of(FunctionCallback.builder()
                        .function(CURRENT_WEATHER_FUNC_NAME, weatherFunc)
                        .description(CURRENT_WEATHER_FUNC_DESC)
                        .inputType(WeatherRequest.class)
                        .responseConverter(OpenAiService::convertResonseJson)
                        .build()))
                .build();

        Message userMessage = new PromptTemplate(question.question()).createMessage();

        Message systemInstructionMessage = new SystemPromptTemplate(INSTRUCTIONS)
                .createMessage();

        List<Message> messages = List.of(userMessage, systemInstructionMessage);

        Prompt weatherPrompt = new Prompt(messages, promptOptions);

        var response = openAiChatModel.call(weatherPrompt);

        return new Answer(response.getResult().getOutput().getText());
    }
}
