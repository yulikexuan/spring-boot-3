//: sfg6lab.controller.BuyerControllerTest.java

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
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import sfg6lab.Sfg6SpringDataRestApp;
import sfg6lab.controller.data.BuyerDto;
import sfg6lab.controller.data.DataMaper;
import sfg6lab.domain.model.Buyer;
import sfg6lab.domain.repository.BuyerRepository;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;


@WebMvcTest
@Import(BuyerController.class)
@ContextConfiguration(classes = { Sfg6SpringDataRestApp.class })
@ExtendWith(MockitoExtension.class)
@DisplayName("Test sfg6lab.controller.BuyerController Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class BuyerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataMaper<Buyer, BuyerDto> buyerMapper;

    @MockBean
    private BuyerRepository buyerRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void name() throws Exception {

        // Given
        Buyer buyer = mock(Buyer.class);
        BuyerDto buyerDto = mock(BuyerDto.class);

        Optional<Buyer> maybeBuyer = Optional.of(buyer);

        given(buyerRepository.findById(1L)).willReturn(maybeBuyer);
        given(buyerMapper.toDto(maybeBuyer.get())).willReturn(buyerDto);

        // When
        mockMvc.perform(get("/buyers/{id}", "1"))
                .andDo(print());
//                .andExpect(status().isOk())
//                .andExpect(content().string(expectedQuote));
    }


}