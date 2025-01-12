//: sfg6lab.ai.service.OpenAiServiceImpl.java

package sfg6lab.ai.service;


import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import sfg6lab.ai.domain.model.*;

import java.util.Map;


@Service
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
final class OpenAiServiceImpl implements OpenAiService {

    static final String CAPITAL_PROMPT_PARAM = "countryOrState";

    @Value("classpath:template/capital-prompt.st")
    private Resource capitalPrompt;

    @Value("classpath:template/capital-prompt-with-details.st")
    private Resource capitalDetailsPrompt;

    @Value("classpath:template/capital-prompt-with-schema.st")
    private Resource capitalSchemaPrompt;

    private final ChatModel chatModel;

    @Override
    public String getAnswer(String question) {

        PromptTemplate promptTemplate = new PromptTemplate(question);
        Prompt prompt = promptTemplate.create();
        ChatResponse chatResponse = chatModel.call(prompt);

        return chatResponse.getResult().getOutput().getContent();
    }

    @Override
    public Answer getAnswer(Question question) {
        return new Answer(this.getAnswer(question.question()));
    }

    @Override
    public Answer getCapital(CapitalRequest capitalRequest) {
        return getCapital(capitalPrompt, capitalRequest, chatModel);
    }

    @Override
    public Answer getCapitalDetails(CapitalRequest capitalRequest) {
        return getCapital(capitalDetailsPrompt, capitalRequest, chatModel);
    }

    @Override
    public CapitalResponse requestCapital(CapitalRequest capitalRequest) {

        BeanOutputConverter<CapitalResponse> converter =
                new BeanOutputConverter<>(CapitalResponse.class);
        String format = converter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(capitalSchemaPrompt);
        Prompt prompt = promptTemplate.create(Map.of(
                CAPITAL_PROMPT_PARAM, capitalRequest.countryOrState(),
                "format", format));

        ChatResponse response = chatModel.call(prompt);

        var content = response.getResult().getOutput().getContent();

        return converter.convert(content);
    }

    @Override
    public CapitalDetailsResponse requestCapitalDetails(CapitalRequest capitalRequest) {

        BeanOutputConverter<CapitalDetailsResponse> converter =
                new BeanOutputConverter<>(CapitalDetailsResponse.class);

        String format = converter.getFormat();

        PromptTemplate promptTemplate = new PromptTemplate(capitalSchemaPrompt);
        Prompt prompt = promptTemplate.create(Map.of(
                CAPITAL_PROMPT_PARAM, capitalRequest.countryOrState(),
                "format", format));

        ChatResponse response = chatModel.call(prompt);

        var content = response.getResult().getOutput().getContent();

        return converter.convert(content);
    }

    static Answer getCapital(
            @NonNull final Resource promptTemplateResource,
            @NonNull final CapitalRequest capitalRequest,
            @NonNull final ChatModel chatModel) {

        final var promptTemplate = new PromptTemplate(promptTemplateResource);

        final var promptVal = capitalRequest.countryOrState();
        Prompt prompt = promptTemplate.create(
                Map.of(CAPITAL_PROMPT_PARAM, promptVal));

        ChatResponse chatResponse = chatModel.call(prompt);

        String answer = chatResponse.getResult().getOutput().getContent();

        return new Answer(answer);
    }

} /// :~