//: com.yulikexuan.ssia.controller.HelloControllerTest.java

package com.yulikexuan.ssia.controller;


import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.ManagedTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;


@WebMvcTest(HelloController.class)
@DisplayName("Test REST API Endpoint - HelloController Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @Test
    @WithMockUser
    void say_Hello() throws Exception{

        // Given
        var requestBuilder =
                MockMvcRequestBuilders.get("/ssia/hello")
                        .header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN)
                        .content("Hello!");

        ResultMatcher ok = MockMvcResultMatchers.status().isOk();

        // When
        this.mockMvc.perform(requestBuilder).andExpect(ok);
    }

}
