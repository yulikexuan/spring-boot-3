//: com.yulikexuan.ssia.config.SsiaSecurityConfig.java

package com.yulikexuan.ssia.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
class SecurityConfig {

    @Bean
    SecurityFilterChain configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.httpBasic(Customizer.withDefaults());

        httpSecurity.authorizeHttpRequests(c -> c.anyRequest().authenticated());

        return httpSecurity.build();
    }

} /// :~