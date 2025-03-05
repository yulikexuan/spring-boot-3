//: sfg6lab.ai.service.service.OpenAiService.java

package sfg6lab.ai.audio.service;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.openai.OpenAiAudioSpeechModel;
import org.springframework.ai.openai.OpenAiAudioSpeechOptions;
import org.springframework.ai.openai.audio.speech.SpeechPrompt;
import org.springframework.ai.openai.audio.speech.SpeechResponse;
import org.springframework.stereotype.Service;
import sfg6lab.ai.audio.domain.model.Question;

import static org.springframework.ai.openai.api.OpenAiAudioApi.SpeechRequest.AudioResponseFormat.MP3;
import static org.springframework.ai.openai.api.OpenAiAudioApi.SpeechRequest.Voice.FABLE;
import static org.springframework.ai.openai.api.OpenAiAudioApi.TtsModel.TTS_1;


public interface OpenAiService {

    byte[] speech(Question question);
}


@Service
@RequiredArgsConstructor(staticName = "of")
final class OpenAiServiceImpl implements OpenAiService {

    private final OpenAiAudioSpeechModel speechModel;

    @Override
    public byte[] speech(@NonNull final Question question) {

        OpenAiAudioSpeechOptions speechOpts = OpenAiAudioSpeechOptions.builder()
                .voice(FABLE)
                .speed(1.0f)
                .responseFormat(MP3)
                .model(TTS_1.value)
                .build();

        final var speechPrompt = new SpeechPrompt(question.question(), speechOpts);

        SpeechResponse response = speechModel.call(speechPrompt);

        return response.getResult().getOutput();
    }

}
