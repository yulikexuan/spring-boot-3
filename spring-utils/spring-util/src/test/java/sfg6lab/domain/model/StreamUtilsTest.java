//: sfg6lab.domain.model.StreamUtilsTest.java


package sfg6lab.domain.model;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.StreamUtils;
import org.springframework.data.util.Streamable;

import java.util.function.BiFunction;
import java.util.stream.Stream;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@ExtendWith(MockitoExtension.class)
@DisplayName("Test StreamUtils Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StreamUtilsTest {

    private BiFunction<String, Integer, Profile> profileFunc;

    @BeforeEach
    void setUp() {
        this.profileFunc = Profile::new;
    }

    @Test
    void able_To_Zip_Two_Different_Streams_Into_One() {

        // Given
        String[] names = {
                randomAlphanumeric(7),
                randomAlphanumeric(8),
                randomAlphanumeric(9)};

        Integer[] ages = {20, 21, 22};

        Stream<String> nameStream = Streamable.of(names).stream();
        Stream<Integer> ageStream = Streamable.of(ages).stream();

        // When
        Stream<Profile> profiles = StreamUtils.zip(
                nameStream, ageStream, profileFunc);

        // Then
        assertThat(profiles).containsExactly(
                new Profile(names[0], ages[0]),
                new Profile(names[1], ages[1]),
                new Profile(names[2], ages[2]));
    }

    static record Profile(String name, int age) {
    }

}///:~