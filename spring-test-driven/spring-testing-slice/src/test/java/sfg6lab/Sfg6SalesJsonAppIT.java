//: sfg6lab.Sfg6SalesJsonAppTest.java

package sfg6lab;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.json.JsonContent;
import org.springframework.boot.test.json.JsonContentAssert;
import org.springframework.test.context.ContextConfiguration;
import sfg6lab.config.Sfg6ObjectMappingCfg;
import sfg6lab.domain.model.Sales;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import static sfg6lab.domain.model.Sales.SalesData;


@Slf4j
@JsonTest
@ContextConfiguration(classes = {Sfg6ObjectMappingCfg.class})
@DisplayName("Test SalesJsonSerializer - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class Sfg6SalesJsonAppIT {

    @Autowired
    private JacksonTester<Sales> jacksonTester;

    @BeforeEach
    void setUp() {
        
    }

    @Test
    void able_To_Serialize() throws Exception {

        // Given
        var sales = new Sales(List.of(
                new SalesData(2020, 1, 100),
                new SalesData(2022, 2, 300),
                new SalesData(2022, 9, 500)));

        // When
        JsonContent<Sales> result = this.jacksonTester.write(sales);
        log.debug(">>> JSON of Sales is : {}", result.getJson());

        // Then
        JsonContentAssert jsonContentAssert = assertThat(result);
        jsonContentAssert.hasJsonPathValue("$.salesData")
                .extractingJsonPathStringValue("$.salesData[1].x")
                .isEqualTo("2022-02");
    }

}///:~