//: com.yulikexuan.ssia.config.UserManagementConfig.java

package com.yulikexuan.ssia.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.List;


@Configuration
class UserManagementConfig {

    @Bean
    UserDetailsService userDetailsService() {

        List<UserDetails> users = List.of(
                User.withUsername("yul")
                        .password("1234567")
                        .authorities("read")
                        .build(),
                User.withUsername("weil")
                        .password("7654321")
                        .authorities("read")
                        .build());

        return new InMemoryUserDetailsManager(users);
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

} /// :~