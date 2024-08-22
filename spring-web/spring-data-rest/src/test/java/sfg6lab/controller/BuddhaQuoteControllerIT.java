//: sfg6lab.controller.BuddhaQuoteControllerIT.java

package sfg6lab.controller;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import sfg6lab.Sfg6SpringDataRestApp;
import sfg6lab.domain.service.QuoteService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest(BuddhaQuoteController.class)
@ContextConfiguration(classes={Sfg6SpringDataRestApp.class})
@ExtendWith(MockitoExtension.class)
@DisplayName("Test BuddhaQuoteController Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class BuddhaQuoteControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuoteService quoteService;

    @BeforeEach
    void setUp() {

    }

    @Test
    void the_First_Quote_Is_A_String() throws Exception {

        // Given
        String expectedQuote = "Be happy!";
        given(quoteService.quote(1)).willReturn(expectedQuote);

        // When
        mockMvc.perform(get("/quotes/1"))
                .andDo(print());
//                .andExpect(status().isOk())
//                .andExpect(content().string(expectedQuote));
    }
}
