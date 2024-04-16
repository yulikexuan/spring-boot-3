//: sfg6lab.domain.model.SquareTest.java

package sfg6lab.domain.model;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;


@Slf4j
@DisplayName("Test Liskov Principle with Square  - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class SquareTest {


    @BeforeEach
    void setUp() {
    }

    @Test
    void the_Square_Must_Have_Same_Width_And_Height() {

        // Given
        int side = 5;
        int newWidth = 7;
        int newHeight = 10;
        Square square = new Square(side);

        assertThat(square.side()).isEqualTo(side);
        assertThat(square.area()).isEqualTo(side * side);
        assertThat(Square.validate(square)).isTrue();

        // When
        square.width(newWidth).height(newHeight);

        // Then
        assertThat(square.side()).isEqualTo(newHeight);
        assertThat(square.area()).isEqualTo(newHeight * newHeight);
        assertThat(Square.validate(square)).isTrue();
    }

}///:~