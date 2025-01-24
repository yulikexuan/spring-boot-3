//: sfg6lab.ai.rag.service.OpenAiService.java

package sfg6lab.ai.rag.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
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
    private final SimpleVectorStore simpleVectorStore;

    @Value("classpath:/template/rag-prompt-template.st")
    private Resource ragPromptTemplate;

    @Override
    public Answer getAnswer(Question question) {

        SearchRequest searchReq = SearchRequest.builder()
                .query(question.question())
                .topK(5)
                .build();

        List<String> contentList = simpleVectorStore.similaritySearch(searchReq)
                .stream()
                .map(Document::getContent)
                .toList();

        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Prompt prompt = promptTemplate.create(Map.of(
                "input", question.question(),
                "documents", String.join("\n", contentList)));

        // contentList.forEach(System.out::println);

        ChatResponse response = chatModel.call(prompt);

        return new Answer(response.getResult().getOutput().getContent());
    }

}
