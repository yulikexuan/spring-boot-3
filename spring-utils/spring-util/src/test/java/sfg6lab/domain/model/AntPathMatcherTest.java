//: sfg6lab.domain.model.AntPathMatcherTest.java


package sfg6lab.domain.model;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.util.AntPathMatcher;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DisplayName("Test AntPathMatcher Util Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class AntPathMatcherTest {

    private AntPathMatcher matcher;

    @BeforeEach
    void setUp() {
        this.matcher = new AntPathMatcher();
    }

    @Test
    void able_To_Validate_A_Pattern() {

        // Given
        String pattern = "/c/dev/projects/spring-boot-3/**/*.java";

        // When
        boolean valid = this.matcher.isPattern(pattern);

        // Then
        assertThat(valid).isTrue();
    }

    @Test
    void extract_Empty_Path_When_Full_Matched() {

        // Given
        String pattern = "/c/docs/cvs/commit.html";
        String path = "/c/docs/cvs/commit.html";

        // When
        String extracted = this.matcher.extractPathWithinPattern(pattern, path);

        // Then
        assertThat(extracted).isEmpty();
    }

    @Test
    void extract_Path_When_Partial_Matched() {

        // Given
        String pattern = "/c/dev/projects/spring-boot-3/**/*.java";
        String path = "/c/dev/projects/spring-boot-3/spring-utils/**/*.java";
        String expectedExtracted = "spring-utils/**/*.java";

        // When
        String extracted = this.matcher.extractPathWithinPattern(pattern, path);
        boolean isMatched = this.matcher.match(pattern, path);

        // Then
        assertThat(extracted).isEqualTo(expectedExtracted);
        assertThat( this.matcher.matchStart("/c/dev/**/*.java", path)).isTrue();
        assertThat( this.matcher.matchStart("/c/dev/projects/**/*.java", path)).isTrue();
        assertThat( this.matcher.match("/c/dev/projects/**/*.java", path)).isTrue();
        assertThat( this.matcher.matchStart(pattern, path)).isTrue();
        assertThat(isMatched).isTrue();
    }

}///:~