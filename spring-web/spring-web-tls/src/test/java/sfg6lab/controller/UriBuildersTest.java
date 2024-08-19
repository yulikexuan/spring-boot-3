//: sfg6lab.controller.UriBuilderTest.java

package sfg6lab.controller;


import org.junit.jupiter.api.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("Test UriBuilders - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class UriBuildersTest {

    @Nested
    class UriComponentsBuilderTest {

        @Test
        void able_To_Compose_Simple_Uri_With_Query_Parameters() {

            // Given
            UriComponents uriComponents = UriComponentsBuilder.newInstance()
                    .scheme("https")
                    .host("www.x.com")
                    .path("/home")
                    .query("lang={lang}")
                    .buildAndExpand("en");

            // When
            String uri = uriComponents.toUriString();

            // Then
            assertThat(uri).isEqualTo("https://www.x.com/home?lang=en");
        }

        @Test
        void able_To_Compose_Encoded_Uri() {

            // Given
            UriComponents uriComponents = UriComponentsBuilder.newInstance()
                    .scheme("https")
                    .host("docs.spring.io")
                    .path("/home")
                    .path("/junit 5")
                    .build()
                    .encode();

            // When
            String uriString = uriComponents.toUriString();

            // Then
            assertThat(uriString).isEqualTo("https://docs.spring.io/home/junit%205");
        }

        @Test
        void able_To_Compose_Uri_With_Path_Variables() {

            // Given
            String pathValue = "junit 5";
            UriComponents uriComponents = UriComponentsBuilder.newInstance()
                    .scheme("https")
                    .host("docs.spring.io")
                    .path("/home")
                    .path("/{category}")
                    .buildAndExpand(pathValue)
                    .encode();

            // When
            String uriString = uriComponents.toUriString();

            // Then
            assertThat(uriString).isEqualTo("https://docs.spring.io/home/junit%205");
        }

        @Test
        void able_To_Compose_Uri_With_Regular_Expressions() {

            String template = "/myurl/{name:[a-z]{1,5}}/show";
            UriComponents uriComponents = UriComponentsBuilder.fromUriString(template).build();
            uriComponents = uriComponents.expand(Collections.singletonMap("name", "test"));
        }

    }

} ///:~