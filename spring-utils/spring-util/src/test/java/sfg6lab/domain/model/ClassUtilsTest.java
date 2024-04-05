//: sfg6lab.domain.model.ClassUtilsTest.java


package sfg6lab.domain.model;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.util.ClassUtils;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DisplayName("Test ClassUtils Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ClassUtilsTest {

    private Class<?> clazz;
    private String clazzName;

    @BeforeEach
    void setUp() {
        this.clazz = sfg6lab.config.Sfg6AppCfg.class;
        this.clazzName = this.clazz.getName();
    }

    @Test
    void able_To_Get_Resource_Path_Of_A_Package_Of_A_Class() {

        // Given
        String expected = "sfg6lab/config";

        // When
        String actualPath = ClassUtils.classPackageAsResourcePath(clazz);

        // Then
        assertThat(actualPath).isEqualTo(expected);
    }

    @Test
    void able_To_Convert_Between_Resource_Path_And_Class_Name() {

        // Given
        String expectedResPath = "sfg6lab/config/Sfg6AppCfg";

        // When
        String actualPath = ClassUtils.convertClassNameToResourcePath(clazzName);
        String actualClassName = ClassUtils.convertResourcePathToClassName(actualPath);

        // Then
        assertThat(actualPath).isEqualTo(expectedResPath);
        assertThat(actualClassName).isEqualTo(this.clazzName);
    }

    @Test
    void able_To_Merge_Multi_Interfaces_Into_One_Concrete_Class() throws Exception {

        // Given
        Class<?>[] interfaces = new Class<?>[] { HasName.class, HasAge.class };

        // When
        Class<?> clazz = ClassUtils.createCompositeInterface(
                interfaces, this.clazz.getClassLoader());

        var constructor = ClassUtils.getConstructorIfAvailable(clazz);

        Set<String> interfaceSet = ClassUtils.getAllInterfacesForClassAsSet(clazz)
                .stream()
                .map(Class::getSimpleName)
                .collect(Collectors.toSet());

        // Then
        Method[] methods = clazz.getMethods();
        long count = Arrays.stream(methods).map(Method::getName)
                .filter(n -> n.equals("name") || n.equals("age"))
                .count();
        assertThat(count).isEqualTo(2);
        assertThat(constructor).isNull();

        assertThat(interfaceSet).contains("HasName", "HasAge", "Serializable");

        assertThat(ClassUtils.isAssignable(HasName.class, clazz)).isTrue();

        assertThat(ClassUtils.isPresent(
                "sfg6lab.domain.model.ClassUtilsTest.HasName",
                this.clazz.getClassLoader()))
                .isTrue();
    }

    static interface HasName {
        String name();
    }

    static interface HasAge {
        int age();
    }

}///:~