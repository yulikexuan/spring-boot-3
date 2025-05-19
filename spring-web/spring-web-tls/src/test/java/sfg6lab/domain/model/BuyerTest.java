//: sfg6lab.domain.model.BuyerTest.java

package sfg6lab.domain.model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Test Buyer record - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class BuyerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void test_Ternaries() {

        int stripes = 7;
        int animal = stripes < 9 ? 3 : "Horse".length();

        assertThat(animal).isEqualTo(3);
    }

}
