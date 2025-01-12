//: sfg6lab.ai.domain.model.CapitalDetailsResponse.java

package sfg6lab.ai.domain.model;


import com.fasterxml.jackson.annotation.JsonPropertyDescription;


public record CapitalDetailsResponse(
        @JsonPropertyDescription("This is the name of the capital")
        String capital,
        @JsonPropertyDescription("This is the population of the city")
        int population,
        @JsonPropertyDescription("This is the region of the city")
        String region,
        @JsonPropertyDescription("This is the language people speak in the city")
        String language,
        @JsonPropertyDescription("This is the currency name used in the city")
        String currency) {

}
