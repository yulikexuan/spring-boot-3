//: sfg6lab.ai.controller.web.service.OpenAiController.java

package sfg6lab.ai.audio.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sfg6lab.ai.audio.domain.model.Question;
import sfg6lab.ai.audio.service.OpenAiService;


@RestController
@RequestMapping("/spring-ai/speech")
@RequiredArgsConstructor
class OpenAiController {

    private final OpenAiService openAiService;

    @PostMapping(produces = "audio/mpeg")
    public byte[] textToSpeech(@RequestBody Question question) {
        return openAiService.speech(question);
    }

} /// :~
