//: sfg6lab.ai.service.service.OpenAiService.java

package sfg6lab.ai.image.service;


import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.model.Media;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.OpenAiImageOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.multipart.MultipartFile;
import sfg6lab.ai.image.domain.model.Question;

import java.util.Base64;
import java.util.List;

import static org.springframework.http.MediaType.IMAGE_JPEG;


public interface OpenAiService {

    int IMAGE_HEIGHT = 1024;
    int IMAGE_WIDTH = 1792;

    String IMAGE_FORMAT = "b64_json";
    String IMAGE_MODEL = "dall-e-3";
    String IMAGE_HIGH_QUALITY_MODEL = "hd";
    String IMAGE_STYLE = "natural";

    byte[] getImage(Question question);

    String getDescription(MultipartFile file);
}


@Service
@RequiredArgsConstructor(staticName = "of")
final class OpenAiServiceImpl implements OpenAiService {

    private final ImageModel imageModel;
    private final ChatModel chatModel;

    @Override
    public byte[] getImage(Question question) {

        var options = OpenAiImageOptions.builder()
                .withHeight(IMAGE_HEIGHT)
                .withWidth(IMAGE_WIDTH)
                .responseFormat(IMAGE_FORMAT)
                .model(IMAGE_MODEL)
//                .quality(IMAGE_HIGH_QUALITY_MODEL)
//                .style(IMAGE_STYLE)
                .build();

        ImagePrompt imagePrompt = new ImagePrompt(question.question(), options);

        var imageResponse = imageModel.call(imagePrompt);

        var imageBits = imageResponse.getResult().getOutput().getB64Json();

        return Base64.getDecoder().decode(imageBits);
    }

    @Override
    public String getDescription(MultipartFile file) {

        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .model(OpenAiApi.ChatModel.GPT_4_O.getValue())
                .build();

        var userMessage = new UserMessage(
                "Pleas explain what you see in this picture?",
                List.of(new Media(IMAGE_JPEG, file.getResource())));

        ChatResponse response = chatModel.call(
                new Prompt(List.of(userMessage), options));

        return response.getResult().getOutput().toString();
    }

}
