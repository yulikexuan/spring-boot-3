//: sfg6lab.domain.model.CastUtilsTest.java


package sfg6lab.domain.model;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.data.util.CastUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Test CastUtils Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CastUtilsTest {

    @Test
    void easy_Cast() {

        // Given
        List<String> names = Lists.newArrayList("abc");

        // When
        Collection<String> info = CastUtils.cast(names);

        // Then
        assertThat(info).isExactlyInstanceOf(ArrayList.class);
    }

}///:~