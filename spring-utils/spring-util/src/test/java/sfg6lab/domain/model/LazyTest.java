//: sfg6lab.domain.model.LazyTest.java


package sfg6lab.domain.model;


import org.junit.jupiter.api.*;
import org.springframework.data.util.Lazy;
import org.springframework.util.AlternativeJdkIdGenerator;

import java.util.UUID;
import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


@DisplayName("Test Lazy Util Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class LazyTest {

    private Supplier<UUID> idSupplier;
    private AlternativeJdkIdGenerator idGenerator;

    @BeforeEach
    void setUp() {
        this.idGenerator = new AlternativeJdkIdGenerator();
        this.idSupplier = idGenerator::generateId;
    }

    @Test
    void has_Empty_Lazy() {

        // Given

        // When & Then
        assertThatThrownBy(() -> Lazy.empty().get())
                .isExactlyInstanceOf(IllegalStateException.class);
    }

    @Test
    void has_Null_From_Empty_Lazy() {

        // Given

        // When & Then
        assertThat(Lazy.empty().getNullable()).isNull();
        assertThat(Lazy.empty().getOptional()).isEmpty();
    }

    @Test
    void lazy_Is_Mappable() {

        // Given
        UUID id = idSupplier.get();
        var lazyId = Lazy.of(() -> id);
        int expectedHash = id.hashCode();

        // When
        String uuidStr = lazyId.map(UUID::toString).get();
        int uuidHash = lazyId.map(UUID::hashCode).get();

        // Then
        assertThat(uuidHash).isEqualTo(expectedHash);
    }

}///:~