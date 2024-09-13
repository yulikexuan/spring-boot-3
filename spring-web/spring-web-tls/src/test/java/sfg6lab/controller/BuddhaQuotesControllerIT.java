//: sfg6lab.controller.BuddhaQuotesControllerIT.java

package sfg6lab.controller;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import sfg6lab.Sfg6SpringWebTlsApp;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@WebMvcTest
@Import(BuddhaQuotesController.class)
@ContextConfiguration(classes={Sfg6SpringWebTlsApp.class})
@ExtendWith(MockitoExtension.class)
@DisplayName("Test sfg6lab.controller.BuddhaQuotesController Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class BuddhaQuotesControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void mockMvc_Should_Be_Available() throws Exception {
        log.info(">>> Validating MockMvc availability");
        mockMvc.perform(get("https://localhost:8443/buddha-quotes?index=1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}
