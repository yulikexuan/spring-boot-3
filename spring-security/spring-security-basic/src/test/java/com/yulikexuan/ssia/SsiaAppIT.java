package com.yulikexuan.ssia;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * - **`@AutoConfigureMockMvc` **is used to configure and enable a **fully initialized `MockMvc` instance** for testing and works in conjunction with **`@SpringBootTest` **. Unlike `@WebMvcTest`, it loads the **entire application context** (including services, repositories, etc.).
 */
@SpringBootTest
@AutoConfigureMockMvc
public class SsiaAppIT {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private MockMvcTester mvcTester;

    @Test
    @DisplayName("Test calling /hello endpoint without authentication returns unauthorized.")
    public void helloUnauthenticated() throws Exception {
        mvc.perform(get("/hello"))
              .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("Test calling /hello endpoint authenticated with a mock user returns ok.")
    @WithMockUser
    public void helloAuthenticated() throws Exception {
        mvc.perform(get("/hello"))
              .andExpect(content().string("Hello!"))
              .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Test calling /hello endpoint authenticated with a real user returns ok.")
    public void helloAuthenticatedWithUser() throws Exception {
        mvc.perform(get("/hello")
                    .with(user("mary")))
                .andExpect(content().string("Hello!"))
                .andExpect(status().isOk());
    }
}
