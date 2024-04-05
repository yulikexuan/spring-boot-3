//: sfg6lab.domain.model.TypeUtils.java


package sfg6lab.domain.model;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.util.TypeUtils;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DisplayName("Test SystemPropertyUtils Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class TypeUtilsTest {

    @Test
    void is_Assignable_To_The_Left() {
        assertThat(TypeUtils.isAssignable(Number.class, Long.class)).isTrue();
        assertThat(TypeUtils.isAssignableBound(Number.class, Long.class)).isTrue();
        assertThat(TypeUtils.isAssignableBound(Number.class, null)).isTrue();
        assertThat(TypeUtils.isAssignableBound(null, null)).isTrue();
        assertThat(TypeUtils.isAssignableBound(null, Long.class)).isFalse();
    }

}///:~