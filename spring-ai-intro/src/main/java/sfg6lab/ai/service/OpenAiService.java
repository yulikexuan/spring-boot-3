//: sfg6lab.ai.service.OpenAiService.java

package sfg6lab.ai.service;


import sfg6lab.ai.domain.model.Answer;
import sfg6lab.ai.domain.model.CapitalRequest;
import sfg6lab.ai.domain.model.CapitalResponse;
import sfg6lab.ai.domain.model.Question;


public interface OpenAiService {

    String getAnswer(String question);

    Answer getAnswer(Question question);

    Answer getCapital(CapitalRequest capitalRequest);

    Answer getCapitalDetails(CapitalRequest capitalRequest);

    CapitalResponse requestCapital(CapitalRequest capitalRequest);
}
