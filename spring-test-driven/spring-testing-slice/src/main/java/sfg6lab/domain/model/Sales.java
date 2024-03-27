//: sfg6lab.domain.model.Sales.java


package sfg6lab.domain.model;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;


public record Sales(List<SalesData> salesData) {

    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SalesData {

        public int year;
        public int month;
        public int count;
    }

}///:~