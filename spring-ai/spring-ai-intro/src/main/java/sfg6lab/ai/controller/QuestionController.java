//: sfg6lab.ai.controller.QuestionController.java

package sfg6lab.ai.controller;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sfg6lab.ai.domain.model.*;
import sfg6lab.ai.service.OpenAiService;


@RestController
@RequestMapping("/spring-ai")
@AllArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class QuestionController {

    private final OpenAiService openAiService;

    @PostMapping("/ask")
    Answer askQuestion(@RequestBody Question question) {
        return openAiService.getAnswer(question);
    }

    @PostMapping("/capital")
    public Answer getCapital(@RequestBody CapitalRequest capitalRequest) {
        return openAiService.getCapital(capitalRequest);
    }

    @PostMapping("/capital/details")
    public Answer getCapitalDetails(@RequestBody CapitalRequest capitalRequest) {
        return openAiService.getCapitalDetails(capitalRequest);
    }

    @PostMapping("/capital-schema")
    public CapitalResponse capitalRequest(
            @RequestBody CapitalRequest capitalRequest) {
        return openAiService.requestCapital(capitalRequest);
    }

    @PostMapping("/capital-details-schema")
    public CapitalDetailsResponse capitalDetailsResponse(
            @RequestBody CapitalRequest capitalRequest) {

        return openAiService.requestCapitalDetails(capitalRequest);
    }

} /// :~