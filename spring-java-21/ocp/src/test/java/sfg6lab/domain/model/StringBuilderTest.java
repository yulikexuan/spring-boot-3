//: sfg6lab.domain.model.StringBuilderTest.java

package sfg6lab.domain.model;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;


@DisplayName("Test StringBuilder Class  - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StringBuilderTest {
    
    /*
     * substring()
     * indexOf()
     * length()
     * charAt()
     *
     * codePointAt()
     * codePointBefore()
     * codePointCount()
     */
    @Test
    void seven_Methods_Of_StringBuilder_Are_Same_As_String() {
    
        // Given
        StringBuilder strBuilder = new StringBuilder("animals");
        
        // When
        var sub = strBuilder.substring(
                strBuilder.indexOf("a"), strBuilder.indexOf("al"));
        int builderLen = strBuilder.length();
        char charAt6 = strBuilder.charAt(6);
        int codePointAt6 = strBuilder.codePointAt(6);
        int codePointBefore6 = strBuilder.codePointBefore(6);
        
        // Then
        assertAll(() -> {
            assertThat(sub).isEqualTo("anim");
            assertThat(builderLen).isEqualTo(7);
            assertThat(sub).isExactlyInstanceOf(String.class);
            assertThat(builderLen).isEqualTo(7);
            assertThat(charAt6).isEqualTo('s');
            assertThat(codePointAt6).isEqualTo(115);
            assertThat(codePointBefore6).isEqualTo(108);
            assertThat(strBuilder.codePointCount(0, 7)).isEqualTo(7);
        });
    }
    
    @Test
    void able_To_Add_Code_Points_To_StringBuilder() {
    
        // Given
        var titleBuilder = new StringBuilder("Hello Java 21!");
        
        // When
        titleBuilder.appendCodePoint("\u2000".codePointAt(0));
        
        // Then
        assertThat(titleBuilder.length()).isEqualTo(15);
        assertThat(titleBuilder.toString().strip().length()).isEqualTo(14);
    }
    
    @Test
    void able_To_Insert_Info_StringBuilder() {
    
        // Given
        var dataBuilder = new StringBuilder("animals");
        
        // When
        dataBuilder.insert(7, "_")
                .insert(0, "->")
                .insert(4, "-");

        // Then
        assertThat(dataBuilder).hasToString("->an-imals_");
    }
    
    @Test
    void able_To_Delete_Characters_From_StringBuilder() {
    
        // Given
        final var dataBuilder = new StringBuilder("abcdef");
        
        // When
        dataBuilder.delete(1, 3);
        
        // Then
        assertThat(dataBuilder).hasToString("adef");
        
        // When
        assertThatThrownBy(() -> dataBuilder.deleteCharAt(5))
                .isExactlyInstanceOf(StringIndexOutOfBoundsException.class);
    }
    
    @Test
    void different_Replace_Method_From_String_Class() {
    
        // Given
        var infoBuilder = new StringBuilder("pigeon dirty");
        
        // When
        infoBuilder.replace(3, 100, "");
        
        // Then
        assertThat(infoBuilder).hasToString("pig");
    }
    
    @Test
    void stringBuilder_Has_Not_Equals_Method_Overridden() {
        
        // Given
        
        String data = "1234567";
        var builder1 = new StringBuilder(data);
        var builder2 = new StringBuilder(data);
        
        // When
        
        // Then
        assertThat(builder1).isNotEqualTo(builder2);
        assertThat(builder1.toString()).isEqualTo(builder2.toString());
        assertThat(builder1.hashCode()).isNotEqualTo(builder2.hashCode());
    }
    
} /// :~