//: sfg6lab.controller.BuyersControllerIT.java

package sfg6lab.controller;


import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import sfg6lab.config.Sfg6AppCfg;
import sfg6lab.domain.model.Buyer;
import sfg6lab.domain.repository.BuyerContactManyToManyRepository;
import sfg6lab.domain.repository.BuyerRepository;
import sfg6lab.domain.repository.ContactRepository;

import java.util.List;
import java.util.function.Supplier;

import static org.mockito.BDDMockito.given;


@WebMvcTest(BuyersController.class)
@ExtendWith(MockitoExtension.class)
@DisplayName("Test sfg6lab.controller.BuyersController Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
@ContextConfiguration(classes = { Sfg6AppCfg.class })
class BuyersControllerIT {

    @MockitoBean
    private BuyerContactManyToManyRepository buyerContactManyToManyRepository;

    @MockitoBean
    private BuyerRepository buyerRepository;

    @MockitoBean
    private ContactRepository contactRepository;

    @Autowired
    private Supplier<List<Buyer>> buyerSamplesSupplier;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    void can_Be_No_Buyer_At_All() throws Exception {

        // Given
        given(this.buyerRepository.findAll()).willReturn(List.of());

        // When
        MvcResult mvcResult = this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/buyers/all")
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(
                        MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",
                        Matchers.is(0)))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();

    }

    @Test
    void should_Not_Return_XML() throws Exception {

        // Given
        given(this.buyerRepository.findAll()).willReturn(List.of());

        // When
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/buyers/all")
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_XML))
                .andExpect(MockMvcResultMatchers.status().isNotAcceptable());
    }

    @Test
    void able_To_Get_All_Buyers_Info() throws Exception {

        // Given
        List<Buyer> allBuyers = this.buyerSamplesSupplier.get();
        given(this.buyerRepository.findAll()).willReturn(allBuyers);

        // When
        this.mockMvc.perform(MockMvcRequestBuilders
                        .get("/buyers/all")
                        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(
                        MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()",
                        Matchers.is(allBuyers.size())))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$[0].email", Matchers.is("john@somedomain.com")))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$[1].email", Matchers.is("mike@somedomain.com")))
                .andExpect(MockMvcResultMatchers.jsonPath(
                        "$[0].id").doesNotExist())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }


}
