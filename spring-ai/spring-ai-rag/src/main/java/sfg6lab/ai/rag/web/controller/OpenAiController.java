//: sfg6lab.ai.rag.web.controller.OpenAiController.java

package sfg6lab.ai.rag.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sfg6lab.ai.rag.domain.model.Answer;
import sfg6lab.ai.rag.domain.model.Question;
import sfg6lab.ai.rag.service.OpenAiService;


@RestController
@RequestMapping("/spring-ai/rag")
@RequiredArgsConstructor
class OpenAiController {

    private final OpenAiService openAiService;

    @PostMapping("/ask")
    Answer answerQuestion(@RequestBody Question question) {
        return openAiService.getAnswer(question);
    }

} /// :~