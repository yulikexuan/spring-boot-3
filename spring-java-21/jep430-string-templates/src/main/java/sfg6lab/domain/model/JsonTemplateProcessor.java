package sfg6lab.domain.model;


import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.NonNull;


public enum JsonTemplateProcessor implements
        StringTemplate.Processor<JsonNode, JacksonException> {

    JSON;

    private final ObjectMapper objectMapper;

    JsonTemplateProcessor() {
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public JsonNode process(@NonNull final StringTemplate stringTemplate)
            throws JacksonException {

        return objectMapper.readTree(stringTemplate.interpolate());
    }
}
