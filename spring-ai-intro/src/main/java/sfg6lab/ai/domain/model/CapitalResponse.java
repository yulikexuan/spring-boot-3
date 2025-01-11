//: sfg6lab.ai.domain.model.CapitalResponse.java

package sfg6lab.ai.domain.model;


import com.fasterxml.jackson.annotation.JsonPropertyDescription;


public record CapitalResponse(
        @JsonPropertyDescription("This is the city name")
        String capital) {

} /// :~