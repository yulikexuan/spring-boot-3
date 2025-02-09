//: sfg6lab.ai.controller.web.function.OpenAiController.java

package sfg6lab.ai.function.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sfg6lab.ai.function.domain.model.Answer;
import sfg6lab.ai.function.domain.model.Question;
import sfg6lab.ai.function.service.OpenAiService;


@RestController
@RequestMapping("/spring-ai/functions")
@RequiredArgsConstructor
class OpenAiController {

    private final OpenAiService openAiService;

    @PostMapping("/weather")
    Answer answerQuestion(@RequestBody Question question) {
        return openAiService.getAnswer(question);
    }

} /// :~
