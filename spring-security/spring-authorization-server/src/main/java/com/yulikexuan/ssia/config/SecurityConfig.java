//: com.yulikexuan.ssia.config.SsiaSecurityConfig.java

package com.yulikexuan.ssia.config;


import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import lombok.NonNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.core.oidc.OidcScopes;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.server.authorization.client.InMemoryRegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.List;
import java.util.UUID;


/**
 * Currently, this configuration does not work with the latest spring boot
 * The cause is "Fail when any filter chain declared after an AnyRequestMatcher filter chain"
 * https://github.com/spring-projects/spring-security/issues/15220
 */
@Configuration
class SecurityConfig {

    @Bean
    @Order(1)
    public SecurityFilterChain asFilterChain(
            @NonNull final HttpSecurity http) throws Exception {

        // This is deprecated for removal
        // OAuth2AuthorizationServerConfiguration.applyDefaultSecurity(http);

        // Defines a minimal set of configurations you can override later if needed
        http.with(
                OAuth2AuthorizationServerConfigurer.authorizationServer(),
                Customizer.withDefaults());

        // Enables the OpenID Connect protocol using the oidc() method of
        // the OAuth2AuthorizationServerConfigurer object
        http.getConfigurer(OAuth2AuthorizationServerConfigurer.class)
                .oidc(Customizer.withDefaults());

        // Specifies the authentication page to which the app needs to redirect
        // the user when asked to log in.
        // We need this configuration because we expect to enable
        // the authorization code grant type
        final var authenticationEntryPoint =
                new LoginUrlAuthenticationEntryPoint("/login");
        http.exceptionHandling(
                ehc -> ehc.authenticationEntryPoint(authenticationEntryPoint));

        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain defaultSecurityFilterChain(
            @NonNull final HttpSecurity http) throws Exception {

        http.formLogin(Customizer.withDefaults());
        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());

        return http.build();
    }

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

    @Bean
    public RegisteredClientRepository registeredClientRepository() {

        final var id = UUID.randomUUID().toString();
        final var registeredClient =
                // A unique internal ID that uniquely identifies the client
                // and only has a purpose in the internal app processes
                RegisteredClient.withId(id)
                      // A client ID: An external client identifier,
                      // similar to what a username is for the user
                    .clientId("client")
                      // Similar to what a password is for a user
                    .clientSecret("secret")
                      // How the authorization server expects the client to
                      // authenticate when sending requests for access tokens
                    .clientAuthenticationMethod(
                            ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
                      // A grant type allowed by the authorization server for
                      // this client, a client might use multiple grant types
                    .authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
                    .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                    .authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
                      // One of the URI addresses the authorization server allows
                      // the client to request a redirect for providing the
                      // authorization code in case of the authorization
                      // code grant type
                    .redirectUri("https://www.manning.com/authorized")
                      // Defines a purpose for the request of an access token
                      // The scope can be used later in authorization rules
                    .scope(OidcScopes.OPENID)
                    .build();

        return new InMemoryRegisteredClientRepository(registeredClient);
    }

    /*
     * Must configure key pair management if the authorization server uses
     * non-opaque tokens
     *
     * This method shows how to configure a JWKSource in the app’s context
     * Create a key pair programmatically and add it to the set of keys the
     * authorization server can use
     *
     * In a real-world app, the app would read the keys from a location where
     * they’re safely stored (such as a vault configured in the environment)
     *
     * However, remember that in a real app, it doesn't make sense to generate
     * new keys every time the app restarts (like in our case)
     * If that happens for a real app, every time a new deployment occurs,
     * the tokens that were already issued will not work anymore
     * (because they can’t be validated anymore with the existing keys)
     */
    @Bean
    public JWKSource<SecurityContext> jwkSource() throws NoSuchAlgorithmException {

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();

        RSAKey rsaKey = new RSAKey.Builder(publicKey)
                .privateKey(privateKey)
                .keyID(UUID.randomUUID().toString())
                .build();

        JWKSet jwkSet = new JWKSet(rsaKey);

        return new ImmutableJWKSet<>(jwkSet);
    }

    /**
     * This object allows customizing all the endpoints paths that the
     * authorization server exposes
     */
    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder().build();
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> jwtCustomizer() {
        return context -> {
            JwtClaimsSet.Builder claims = context.getClaims();
            claims.claim("priority", "HIGH");
        };
    }

} /// :~