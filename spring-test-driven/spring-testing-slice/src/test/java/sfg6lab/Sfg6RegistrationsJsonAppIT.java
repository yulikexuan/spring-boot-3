//: sfg6lab.config.Sfg6ObjectMappingCfgTest.java

package sfg6lab;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.*;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.test.context.ContextConfiguration;
import sfg6lab.config.Sfg6ObjectMappingCfg;
import sfg6lab.domain.model.Registrations;

import java.time.YearMonth;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;


@Slf4j
@JsonTest
@ContextConfiguration(classes = { Sfg6ObjectMappingCfg.class })
@DisplayName("Test Sfg6TestingSliceJsonApp class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class Sfg6RegistrationsJsonAppIT {

    @Autowired
    private ApplicationContext appCtx;

    static final String EXPECTED_JSON_OUTPUT =
            "{\"data\":[{\"x\":\"2020-01\",\"y\":100},{\"x\":\"2022-02\",\"y\":300},{\"x\":\"2022-09\",\"y\":500}]}";

    @Autowired
    private JacksonTester<Registrations> jacksonTester;

    @Value("classpath:registrations.json")
    private Resource registrationsData;

    private List<Registrations.Data> data;

    @BeforeEach
    void setUp() {

        var beanNames = this.appCtx.getBeanDefinitionNames();
        log.debug(">>> {} bean names", beanNames.length);

        data = Arrays.asList(
                new Registrations.Data(YearMonth.of( 2020, 1 ),
                        100 ),
                new Registrations.Data(YearMonth.of( 2022, 2 ),
                        300 ),
                new Registrations.Data(YearMonth.of( 2022, 9 ),
                        500 )
        );
    }

    @Test
    void able_To_Serialize() throws Exception {

        // Given
        Registrations registrations = new Registrations(data);

        // When
        JsonContent<Registrations> result = jacksonTester.write(registrations);

        // Then
        JsonContentAssert jsonContentAssert = assertThat(result);
        jsonContentAssert.isEqualToJson(EXPECTED_JSON_OUTPUT);

        jsonContentAssert.hasJsonPathArrayValue("$.data")
                .extractingJsonPathStringValue("$.data[1].x")
                .isEqualTo("2022-02");

        jsonContentAssert.extractingJsonPathNumberValue("$.data[2].y")
                .isEqualTo(500);
    }

    @Test
    void able_To_Deserialize() throws Exception {

        // Given
        final ObjectContent<Registrations> registrations =
                this.jacksonTester.read(this.registrationsData);

        ObjectContentAssert<Registrations> objAssert = assertThat(registrations);

        objAssert.hasFieldOrProperty("data")
                .extracting("data")
                .isEqualTo(this.data);
    }

}///:~