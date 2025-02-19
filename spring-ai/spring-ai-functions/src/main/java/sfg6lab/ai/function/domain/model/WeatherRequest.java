//: sfg6lab.ai.service.domain.model.WeatherRequest.java

package sfg6lab.ai.function.domain.model;


import com.fasterxml.jackson.annotation.JsonClassDescription;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonClassDescription("Weather API Request Using Latitude and Longitude")
public record WeatherRequest(
        @JsonProperty(required = true, value = "latitude")
        @JsonPropertyDescription("Latitude of the desired location")
        Double latitude,
        @JsonProperty(required = true, value = "longitude")
        @JsonPropertyDescription("Longitude of the desired location")
        Double longitude ) {

}
