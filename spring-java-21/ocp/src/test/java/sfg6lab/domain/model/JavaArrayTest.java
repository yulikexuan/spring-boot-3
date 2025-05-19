//: sfg6lab.domain.model.JavaArrayTest.java

package sfg6lab.domain.model;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Arrays;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;


@DisplayName("- Testing Array API of Java 21")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class JavaArrayTest {
    
    @Test
    void declaring_Multi_Arrays_In_Single_Line() {
    
        // Given
        int[] ids, types;
        ids = new int[] {};
        types = new int[] {};
        
        // When
        
        // Then
        assertThat(ids).isExactlyInstanceOf(int[].class);
        assertThat(types).isExactlyInstanceOf(int[].class);
    }
    
    @Test
    void different_Types_In_Single_Line_Declaration() {
    
        // Given
        int ids[], types;
        ids = new int[] {};
        types = 1;

        // When

        // Then
        assertThat(ids).isExactlyInstanceOf(int[].class);
        assertThat(types).isOne();
    }
    
    @Test
    void array_With_Reference_Variables() {
    
        // Given
        String[] strArray = { "String Value" };
        Object[] objects = strArray;
        String[] anotherStrArray = (String[])objects;
        
        // When
        
        // Compile Error
        // anotherStrArray[0] = new StringBuilder("String Value");
        
        assertThatThrownBy(() -> {
            objects[0] = new StringBuilder("String Value");
        }).isInstanceOf(ArrayStoreException.class);
    }
    
    @Test
    void sorting_For_Searching() {
    
        // Given
        int[] numbers = { 2, 4, 6, 8 };
        
        // When
        var find2 = Arrays.binarySearch(numbers, 2);
        var find4 = Arrays.binarySearch(numbers, 4);
        var find9 = Arrays.binarySearch(numbers, 9);
        
        // Then
        assertThat(find2).isEqualTo(0);
        assertThat(find4).isEqualTo(1);
        assertThat(find9).isEqualTo(-5);
    }
    
    @Test
    void equals_Between_Arrays() {
        assertThat(Arrays.equals(new int[] { 1 }, new int[] { 1 })).isTrue();
        assertThat(Arrays.equals(new int[] { 1 }, new int[] { 1, 2 })).isFalse();
    }
    
    @Test
    void comparing_With_Null() {

        // Given
        String[] names = { "1", "2", "3", "4"};
        String[] another = { "1", null, "3", "4" };
        int[] numbers = { 1, 2, 3, 4 };

        // When
        var result = Arrays.compare(names, another);
        
        // Compile Error
        // var result2 = Arrays.compare(numbers, names);

        // Then
        assertThat(result).isPositive();
    }
    
    @Test
    void mismatching() {
    
        // Given
        int[] num1 = { 1, 2, 3, 4, 5 };
        int[] num2 = { 2, 3, 4, 5 };
        int[] num3 = { 1, 2, 3, -1, -2, -3 };
        int[] num4 = { 1, 2, 3, 4, 5 };
        
        // When
        var result1 = Arrays.mismatch(num1, num2);
        var result2 = Arrays.mismatch(num1, num3);
        var result3 = Arrays.mismatch(num1, num4);
        
        // Then
        assertThat(result1).isEqualTo(0);
        assertThat(result2).isEqualTo(3);
        assertThat(result3).isEqualTo(-1);
        assertThat(Arrays.equals(num1, num4)).isTrue();
    }
    
} /// :~