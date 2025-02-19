//: sfg6lab.ai.controller.web.service.OpenAiController.java

package sfg6lab.ai.image.web.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import sfg6lab.ai.image.domain.model.Question;
import sfg6lab.ai.image.service.OpenAiService;

import java.io.IOException;

import static org.springframework.http.MediaType.*;


@RestController
@RequestMapping("/spring-ai/images")
@RequiredArgsConstructor
class OpenAiController {

    private final OpenAiService openAiService;

    @PostMapping(value = "/ask", produces = IMAGE_PNG_VALUE)
    public byte[] generateImage(@RequestBody Question question) {

        return openAiService.getImage(question);
    }

    @PostMapping(
            value = "/vision",
            consumes = { MULTIPART_FORM_DATA_VALUE},
            produces = {APPLICATION_JSON_VALUE})
    public ResponseEntity<String> upload(
            @Validated @RequestParam("file") MultipartFile file) {

        return ResponseEntity.ok(openAiService.getDescription(file));
    }

} /// :~
