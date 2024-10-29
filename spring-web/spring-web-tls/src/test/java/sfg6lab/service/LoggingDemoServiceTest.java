//: sfg6lab.service.LoggingDemoServiceTest.java

package sfg6lab.service;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;


@Disabled
@ExtendWith(MockitoExtension.class)
@DisplayName("Test sfg6lab.service.LoggingDemoService Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class LoggingDemoServiceTest {

    @Mock
    Logger log;

    @InjectMocks
    private LoggingDemoService loggingDemoService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void should_Log_At_Info_Level_In_DoLogging() {

        // Given

        // When
        this.loggingDemoService.doLogging();

        // Then
        then(log).should().info(anyString(), any(LocalDateTime.class));
    }

    @Test
    void should_Also_Log_Msg_In_Lambda() {

        // Given
        // When
        this.loggingDemoService.doLoggingWithinLambda();

        // Then
        then(log).should(times(10)).debug(
                anyString(), anyInt(), any(LocalDateTime.class));
    }

}
