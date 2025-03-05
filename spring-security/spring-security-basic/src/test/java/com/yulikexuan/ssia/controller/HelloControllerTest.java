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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.assertj.MvcTestResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.http.HttpHeaders.ACCEPT;
import static org.springframework.http.MediaType.TEXT_PLAIN;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(HelloController.class)
@DisplayName("Test REST API Endpoint - HelloController Class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class HelloControllerTest {

    @Autowired
    private MockMvcTester mvcTester;

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
     *
     * One main difference between MockMvc and MockMvcTester is that an
     * unresolved exception is not thrown directly when using MockMvcTester.
     *
     * Rather an MvcTestResult is available with an unresolved exception.
     * Both resolved and unresolved exceptions are considered a failure that
     * can be asserted as follows:
     * assertThat(mvc.get().uri("/boom"))
     *     .hasFailed()
     *     .failure()
     *     .hasMessage("Test exception");
     */
    @Test
    @SneakyThrows
    @WithMockUser
    void able_To_Have_Greeting_In_Response_If_Authenticated() throws Exception {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        assertThat(auth.getName()).isEqualTo("user");
        assertThat(auth.isAuthenticated()).isTrue();

        assertThat(mvcTester.get().uri("/ssia/hello")
                .header(ACCEPT, TEXT_PLAIN))
                .hasStatusOk()
                .hasBodyTextEqualTo(">>> Hello!");
    }

    @Test
    void no_Greeting_If_Unauthenticated() throws Exception {

        // Given
        // When
        MvcTestResult testResult = mvcTester.get()
                .uri("/ssiz/hello")
                .header(ACCEPT, TEXT_PLAIN)
                .exchange();

        // Then
        assertThat(testResult.getResponse().getStatus())
                .isEqualTo(HttpStatus.FOUND.value());
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void calling_Boom_Can_Be_Dangerous() {

        // Given
        var result = mvcTester.get()
                .uri("/ssia/boom").exchange();

        assertThat(result.getUnresolvedException())
                .hasMessageContaining(">>> Boom!");
    }

    @Test
    @SneakyThrows
    @WithMockUser
    void calling_Boom_Can_Be_Dangerous_2() {

        assertThat(mvcTester.get().uri("/ssia/boom"))
                .hasFailed()
                .failure()
                .hasMessageContaining(">>> Boom!");
    }
}