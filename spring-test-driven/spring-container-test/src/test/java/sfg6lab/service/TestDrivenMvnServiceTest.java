//: sfg6lab.command.TestDrivenControllerTest.java

package sfg6lab.service;


import org.assertj.core.util.Files;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import sfg6lab.domain.model.Profile;
import sfg6lab.domain.service.MvnLocalRepositoryService;

import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@ExtendWith(MockitoExtension.class)
@DisplayName("Test TestDrivenMvnCommand class - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class TestDrivenMvnServiceTest {

    @Mock
    private MvnLocalRepositoryService mvnLocalRepositoryService;

    @Spy
    private Profile profile = new Profile("yul", "yu.li@tecsys.com");

    @InjectMocks
    private TestDrivenMvnService controller;

    @BeforeEach
    void setUp() {
    }

    @Test
    void able_To_Know_The_Root_Path() {

        // Given
        Path path = Files.currentFolder().toPath();

        given(this.mvnLocalRepositoryService.root()).willReturn(path);

        // When
        String actualPath = this.controller.mvnHome();

        // Then
        assertThat(actualPath).isEqualTo(path.toString());
        then(this.profile).shouldHaveNoInteractions();
    }

    @Test
    void has_Profile() {

        // Given

        // When
        var actualProfile = (Profile) (ReflectionTestUtils.getField(
                this.controller, "profile"));

        // Then
        assertThat(actualProfile)
                .extracting("username", "email")
                .contains("yul", "yu.li@tecsys.com");
    }

    @Test
    void able_To_Set_Profile_With_ReflectionTestUtils() {

        // Given
        Profile newProfile = new Profile("berry", "berry@gmail.com");

        // When
        ReflectionTestUtils.setField(
                this.controller,
                "profile",
                newProfile,
                Profile.class);

        Profile actualNewProfile = (Profile) ReflectionTestUtils.getField(
                this.controller, "profile");

        // Then
        assertThat(actualNewProfile)
                .extracting("username", "email")
                .contains("berry", "berry@gmail.com");
    }

}///:~