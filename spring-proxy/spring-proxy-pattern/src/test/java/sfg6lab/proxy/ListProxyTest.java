//: sfg6lab.proxy.ListProxyTest.java

package sfg6lab.proxy;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test ListProxyTest - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class ListProxyTest {

    private List<String> list;

    @BeforeEach
    void setUp() {
        this.list = Lists.newArrayList();
    }

    @Test
    void should_Ignore_Null_Arg_Of_Add_Method_Call() {

        // Given
        // String newElement = UUID.randomUUID().toString();

        // When
        boolean added = ListProxy.of(this.list).add(null);

        // Then
        assertThat(added).isFalse();
        assertThat(this.list).isEmpty();
    }

    @Test
    void should_Not_Ignore_Non_Null_Arg_Of_Add_Method_Call() {

        // Given
        String newElement = UUID.randomUUID().toString();

        // When
        boolean added = ListProxy.of(this.list).add(newElement);

        // Then
        assertThat(added).isTrue();
        assertThat(this.list).contains(newElement);
    }

}///:~