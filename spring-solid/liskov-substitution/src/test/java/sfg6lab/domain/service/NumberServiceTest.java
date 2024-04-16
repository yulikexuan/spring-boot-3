//: sfg6lab.domain.service.NumberServiceTest.java

package sfg6lab.domain.service;


import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test (PECS) Producer-Extends and Consumer-Super Principle - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class NumberServiceTest {

    private ThreadLocalRandom random;

    @BeforeEach
    void setUp() {
        this.random = ThreadLocalRandom.current();
    }

    @Test
    void verify_Consumer_Super_Principle_With_Collections() {

        // Given
        List<Number> numbers = Lists.newArrayList();
        final var numberService = NumberService.of(numbers);

        var numberContainer = numberService.prepareInput();

        int num_0 = this.random.nextInt();
        numberContainer.add(num_0);

        long num_1 = this.random.nextLong();
        numberContainer.add(num_1);

        double num_2 = this.random.nextDouble();
        numberContainer.add(num_2);

        // When
        var actualNumbers = numberService.prepareInput();
        Object element_0 = numberContainer.get(0);

        // Then
        assertThat(actualNumbers).contains(num_0, num_1, num_2);
        assertThat(element_0).isInstanceOf(Integer.class);

        // When
        var info = UUID.randomUUID().toString();
        numberContainer = Lists.newArrayList(info);

        // Then
        assertThat(numberContainer.get(0)).isEqualTo(info);
    }

    @Test
    void verify_Consumer_Super_Principle_With_Supplier() {

        // Given
        int number_0 = this.random.nextInt();
        Supplier<? super Number> dataSupplier = () -> number_0;

        // When
        Object actualData = dataSupplier.get();

        // Then
        assertThat(actualData).isExactlyInstanceOf(Integer.class);
    }

    @Test
    void verify_Producer_Extends_Principle_With_Supplier() {

        // Given
        int number_0 = this.random.nextInt();
        Supplier<? extends Number> dataSupplier = () -> number_0;

        // When
        Number actualData = dataSupplier.get();

        // Then
        assertThat(actualData).isExactlyInstanceOf(Integer.class);
    }

    @Test
    void verify_Consumer_Super_Principle_With_Consumer() {

        // Given
        Consumer<? super Number> dataConsumer =
                data -> assertThat(data).isInstanceOf(Number.class);

        // When & Then
        dataConsumer.accept(this.random.nextInt());
        dataConsumer.accept(this.random.nextFloat());
        dataConsumer.accept(this.random.nextDouble());
        dataConsumer.accept(this.random.nextLong());
    }

    @Test
    void verify_Producer_Extends_Principle_With_Consumer() {

        // Given
        Consumer<? extends Number> dataConsumer =
                data -> assertThat(data).isInstanceOf(Number.class);
//
//        // When
//        Number number = 123;
//
//        // Then
//        dataConsumer.accept(number);
    }

}///:~