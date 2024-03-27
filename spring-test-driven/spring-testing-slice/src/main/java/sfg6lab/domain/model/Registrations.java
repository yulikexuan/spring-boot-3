//: sfg6lab.domain.model.Registrations.java


package sfg6lab.domain.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.YearMonth;
import java.util.List;


public record Registrations(List<Data> data) {

    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Data {

        @JsonProperty("x")
        public YearMonth yearMonth;

        @JsonProperty("y")
        public int count;
    }

}///:~