//: sfg6lab.domain.model.OptionalsTest.java


package sfg6lab.domain.model;


import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Optionals;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Stream;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;


@Slf4j
@ExtendWith(MockitoExtension.class)
@DisplayName("Test Optionals Util Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class OptionalsTest {

    @Mock
    private BiConsumer<String, Integer> noteConsumer;

    @Mock
    private BiConsumer<String, String> biStrConsumer;

    @Mock
    private BiFunction<String, Integer, String> mapFunc;

    private AlternativeJdkIdGenerator idGenerator;

    private ThreadLocalRandom random;

    @BeforeEach
    void setUp() {
        this.idGenerator = new AlternativeJdkIdGenerator();
        this.random = ThreadLocalRandom.current();
    }

    @Test
    void knows_If_Any_Of_Given_Optionals_Is_Available() {

        // Given
        Optional<String> unavailableOpt = Optional.empty();
        Optional<String> uuidOpt = Optional.of(
                this.idGenerator.generateId().toString());

        // When
        boolean isAnyPresent = Optionals.isAnyPresent(unavailableOpt, uuidOpt);

        // Then
        assertThat(isAnyPresent).isTrue();
        assertThat(Optionals.isAnyPresent(Optional.empty())).isFalse();
    }

    @Test
    void able_To_Collect_All_Available_Element_Into_Stream_From_Optionals() {

        // Given
        String[] uuids = {
                this.idGenerator.generateId().toString(),
                this.idGenerator.generateId().toString(),
                this.idGenerator.generateId().toString()};

        // When
        List<String> uuidStrings = Optionals.toStream(
                Optional.of(uuids[1]),
                Optional.of(uuids[0]),
                Optional.of(uuids[2]))
                .toList();

        // Then
        assertThat(uuidStrings).containsExactly(uuids);
    }

    @Test
    void able_To_Get_The_First_Available_Element() {

        // Given
        Function<String, Optional<Integer>> lengthFunc = str -> {
            if (str == null) {
                return Optional.empty();
            }
            return Optional.of(str.length());
        };

        int[] lengths = {
                this.random.nextInt(4, 10) + 1,
                this.random.nextInt(7, 17 + 2),
                this.random.nextInt(9, 21) + 3};

        log.debug(">>> Specified Lengths {}", Arrays.toString(lengths));

        List<String> notes = Lists.newArrayList(
                null,
                randomAlphanumeric(lengths[2]),
                randomAlphanumeric(lengths[1]),
                null,
                randomAlphanumeric(lengths[0]));

        // When
        Optional<Integer> firstLength = Optionals.firstNonEmpty(notes, lengthFunc);

        // Then
        assertThat(firstLength).hasValue(lengths[2]);
    }

    @Test
    void able_To_Get_The_First_Available_Element_From_Suppliers_Of_Optional() {

        // Given
        String firstName = randomAlphanumeric(10) + 1;
        Supplier<Optional<String>> supplier2 =() -> Optional.of(firstName);
        Supplier<Optional<String>> supplier1 = Optional::empty;
        Supplier<Optional<String>> supplier3 =
                () -> Optional.of(randomAlphanumeric(15) + 2);

        // When
        var actualFirst = Optionals.firstNonEmpty(
                List.of(supplier1, supplier2, supplier3));

        // Then
        assertThat(actualFirst).hasValue(firstName);
    }

    @Test
    void runs_Consumer_If_All_Are_Available() {

        // Given
        String note = randomAlphanumeric(7);
        Optional<String> noteOpt = Optional.of(note);

        int age = this.random.nextInt(10, 100);
        Optional<Integer> ageOpt = Optional.of(age);

        // When
        Optionals.ifAllPresent(noteOpt, ageOpt, this.noteConsumer);
        Optionals.ifAllPresent(noteOpt, Optional.empty(), this.noteConsumer);
        Optionals.mapIfAllPresent(noteOpt, ageOpt, this.mapFunc);

        // Then
        then(this.noteConsumer).should(times(1))
                .accept(note, age);
        then(this.mapFunc).should().apply(note, age);
    }

    @Test
    void run_Next_Element_With_Optional() {

        // Given
        int[] lengthConfig = {3, 5};
        List<String> notes = Lists.newArrayList(
                randomAlphanumeric(lengthConfig[0]),
                randomAlphanumeric(lengthConfig[1]));

        final var iterator = notes.iterator();

        // When
        Optionals.ifAllPresent(
                Optionals.next(iterator),
                Optionals.next(iterator),
                this.biStrConsumer);

        // Then
        then(this.biStrConsumer).should().accept(notes.get(0), notes.get(1));
    }

    @Test
    void able_To_Generate_A_Stream_Based_On_An_Array_Of_Optionals() {

        // Given
        String[] notes = {
                randomAlphanumeric(7),
                randomAlphanumeric(8),
                randomAlphanumeric(9)};

        // When
        Stream<String> strStream = Optionals.toStream(
                Optional.of(notes[0]), Optional.of(notes[1]), Optional.of(notes[2]));

        // Then
        assertThat(strStream).containsExactly(notes);
    }

}///:~