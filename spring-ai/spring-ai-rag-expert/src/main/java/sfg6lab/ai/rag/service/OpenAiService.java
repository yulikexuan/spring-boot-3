//: sfg6lab.ai.rag.service.OpenAiService.java

package sfg6lab.ai.rag.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.chat.prompt.SystemPromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import sfg6lab.ai.rag.domain.model.Answer;
import sfg6lab.ai.rag.domain.model.Question;

import java.util.List;
import java.util.Map;


public interface OpenAiService {

    Answer getAnswer(Question question);
}


@Service
@RequiredArgsConstructor(staticName = "of")
final class OpenAiServiceImpl implements OpenAiService {

    private final ChatModel chatModel;
    private final VectorStore vectorStore;

    @Value("classpath:/template/rag-prompt-template.st")
    private Resource ragPromptTemplate;

    @Value("classpath:/template/system-message.st")
    private Resource systemMessageTemplate;

    @Override
    public Answer getAnswer(Question question) {

        // Instructions Message
        final var systemMessagePromptTemplate =
                new SystemPromptTemplate(systemMessageTemplate);
        Message systemMessage = systemMessagePromptTemplate.createMessage();

        final String userQuestion = question.question();
        SearchRequest searchReq = SearchRequest.builder()
                .query(userQuestion)
                .topK(5)
                .build();

        // Search in Milvus & User Message
        List<String> contentList = vectorStore.similaritySearch(searchReq)
                .stream()
                .map(Document::getText)
                .toList();
        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Message userMessage = promptTemplate.createMessage
                (Map.of("input", userQuestion,
                        "documents", String.join("\n", contentList)));

        ChatResponse response = chatModel.call(
                new Prompt(List.of(systemMessage, userMessage)));

        return new Answer(response.getResult().getOutput().getText());
    }

}
