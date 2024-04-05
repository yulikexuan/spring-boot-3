//: sfg6lab.domain.model.AutoPopulatingListTest.java


package sfg6lab.domain.model;


import org.junit.jupiter.api.*;
import org.springframework.lang.NonNull;
import org.springframework.util.AutoPopulatingList;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Test AutoPopulatingList Util Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AutoPopulatingListTest {

    // AutoPopulatingList will create elements in the get() method
    // if they don't already exist

    private AutoPopulatingList<Long> list;

    @BeforeEach
    void setUp() {
        this.list = new AutoPopulatingList<>(new IndexBasedElementFactory());
    }

    @Test
    void able_To_Generate_Element_Value_When_Fetching_With_Index() {

        // Given
        int[] indexes = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        // When
        for (int i : indexes) {
            long element = this.list.get(i);
            assertThat(element).isEqualTo((long) Math.pow(2, i));
        }
    }

    static class IndexBasedElementFactory
            implements AutoPopulatingList.ElementFactory<Long> {

        @Override
        public @NonNull Long createElement(int index)
                throws AutoPopulatingList.ElementInstantiationException {

            return (long) Math.pow(2, index);
        }

    }

}///:~