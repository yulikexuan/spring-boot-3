//: sfg6lab.ai.controller.web.service.OpenAiController.java

package sfg6lab.ai.audio.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sfg6lab.ai.audio.service.OpenAiService;


@RestController
@RequestMapping("/spring-ai/images")
@RequiredArgsConstructor
class OpenAiController {

    private final OpenAiService openAiService;

} /// :~
