//: sfg6lab.config.Sfg6AppCfgIT.java

package sfg6lab.config;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import sfg6lab.service.AopService;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;


@Slf4j
@SpringBootTest(
        classes = { Sfg6AppCfg.class },
        useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE)
@DisplayName("Test Sfg6AppCfg - ")
@ExtendWith(MockitoExtension.class)
@DisplayNameGeneration(ReplaceUnderscores.class)
class Sfg6AppCfgIT {

    @Autowired
    private AopService aopService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void aopUtils_Is_Helpful() {

        // Given
        assertThat(this.aopService).isNotNull();

        // When
        boolean isProxy = AopUtils.isAopProxy(this.aopService);

        log.debug(">>> The Object is {}", this.aopService);

        // Then
        assertThat(isProxy).isFalse();
    }

}///:~