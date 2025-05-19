//: sfg6lab.domain.model.SpringPoolTest.java

package sfg6lab.domain.model;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.Test;


class StringPoolTest {
    
    @Test
    void compile_Time_Vs_Runtime() {
    
        // Given
        var name1 = "Java 21";
        var name2 = "Java 21";
        
        // When
        boolean isCompileTimeEqual = name1 == name2;
    
        // Then
        assertThat(isCompileTimeEqual).isTrue();
        
        // Given
        var name3 = " Java 21 \n".strip();
        
        // Then
        assertThat(name3.equals(name2)).isTrue();
        assertThat(name3 == name2).isFalse();
        assertThat(name3 == name1).isFalse();
        
        // Given
        var name4 = "Java";
        var name5 = " 21";
        
        // When
        name5 = name4 + name5;
        
        // Then
        assertThat(name5).isNotSameAs(name1);
        assertThat(name5 == name2).isFalse();
        
        // Given
        var name6 = new String(name1);
        
        // Then
        assertThat(name6 == name1).isFalse();
    }
    
    @Test
    void intern_Force_Using_String_Pool() {
    
        // Given
        var name1 = "Hello Java 21!";
        
        // Tell Java to create a new object using the constructor
        // but to intern it and use the string pool anyway
        var name2 = new String("Hello Java 21!").intern();
        
        // When & Then
        assertThat(name1 == name2).isTrue();
        assertThat(name1).isSameAs(name2);
    }
    
    @Test
    void tricky_Issue_Test() {
    
        // Given
        
        // variable first is from compile-time constant
        var first = "rat" + 1;
        
        // variable second is also from compile-time constant
        var second = "r" + "a" + "t" + "1";
        
        // variable third is from runtime constant as constructor of String class is used
        var third = "r" + "a" + "t" + new String("1");
        
        // When & Then
        assertAll(() -> {
            assertThat(first).isSameAs(second);
            assertThat(third).isNotSameAs(second).isNotSameAs(first);
            assertThat(first == second.intern()).isTrue();
            // intern call looks into Spring Pool
            assertThat(first == third.intern()).isTrue();
        });
    }
    
} /// :~