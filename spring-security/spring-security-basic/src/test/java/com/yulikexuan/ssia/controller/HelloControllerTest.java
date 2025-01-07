//: com.yulikexuan.ssia.controller.HelloControllerTest.java

package com.yulikexuan.ssia.controller;


import lombok.SneakyThrows;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.data.domain.ManagedTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(HelloController.class)
@DisplayName("Test REST API Endpoint - HelloController Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    /*
     * The @WithMockUser annotation helps us mock a user with
     *   - a default name of user
     *   - a default password of password
     *   - a default role of USER
     * in the Spring Security security context
     *
     * When testing method uses this annotation, we can get information
     * about the simulated user by using the following code, which “pretends”
     * that the user user is currently logged in
     *
     * Authentication authentication =
     *         SecurityContextHolder.getContext().getAuthentication();
     */
    @Test
    @SneakyThrows
    @WithMockUser
    void able_To_Have_Greeting_In_Response_If_Authenticated() throws Exception{

        // Given
        var requestBuilder =
                MockMvcRequestBuilders.get("/ssia/hello")
                        .header(HttpHeaders.ACCEPT, MediaType.TEXT_PLAIN);

        ResultMatcher ok = status().isOk();
        ResultMatcher greeting = content().string("Hello!");

        // When
        this.mockMvc.perform(requestBuilder)
                .andExpect(ok)
                .andExpect(greeting);
    }

    @Test
    void no_Greeting_If_Unauthenticated() throws Exception{

        // Given
        var requestBuilder =
                MockMvcRequestBuilders.get("/ssia/hello");

        ResultMatcher unauthorized = status().isUnauthorized();

        // When
        this.mockMvc.perform(requestBuilder).andExpect(unauthorized);
    }

}
