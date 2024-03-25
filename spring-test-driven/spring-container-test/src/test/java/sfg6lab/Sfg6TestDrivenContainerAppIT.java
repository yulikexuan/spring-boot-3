//: sfg6lab.app.Sfg6TestDrivenContainerAppTest.java


package sfg6lab;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import sfg6lab.config.*;
import sfg6lab.domain.model.Profile;
import sfg6lab.domain.service.MvnLocalRepositoryService;
import sfg6lab.service.TestDrivenMvnService;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.DisplayNameGenerator.ReplaceUnderscores;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;


@Slf4j
@ActiveProfiles("test")
@SpringBootTest(
        classes = { SpringEnvCfg.class },
        useMainMethod = SpringBootTest.UseMainMethod.WHEN_AVAILABLE)
@TestPropertySource(
        value = "classpath:tdd.yml",
        factory = YamlPropertySourceFactory.class)
@DisplayName("Test Sfg6TestDrivenContainerApp - ")
@DisplayNameGeneration(ReplaceUnderscores.class)
class Sfg6TestDrivenContainerAppIT {

    @MockBean
    private MvnLocalRepositoryService mvnLocalRepositoryService;

    @Autowired
    private FileSystemConfigProperties fileSystemConfigProperties;

    @SpyBean
    @Autowired
    private AuthorConfigProperties authorConfigProperties;

    @Autowired
    private ProfileConfigProperties profileConfigProperties;

    @Autowired
    private TestDrivenMvnService testDrivenMvnService;

    @BeforeEach
    void setUp() {
    }

    @Test
    void config_Properties_For_Test_Are_Loaded() {

        // Given
        Profile expectedProfile = new Profile("berry", "berry@gmail.com");

        given(this.mvnLocalRepositoryService.freeDiskSpace())
                .willReturn(3221225472L);

        Path expectedMvnRoot = Paths.get("/usr/mvn/repository");
        given(this.mvnLocalRepositoryService.root()).willReturn(expectedMvnRoot);

        String authorName = this.authorConfigProperties.name();
        String authorPhone = this.authorConfigProperties.phone();

        // When
        String actualFreeDiskSize = this.testDrivenMvnService.freeDiskSize();
        String mvnHome = this.testDrivenMvnService.mvnHome();

        // Then
        assertThat(actualFreeDiskSize).isEqualTo(
                ">>> The free disk size is 3 GB");
        assertThat(mvnHome).isEqualTo("\\usr\\mvn\\repository");

        assertThat(this.authorConfigProperties)
                .extracting("name", "phone")
                .contains("yulikexuan", "514-987-6543");

        assertThat(this.fileSystemConfigProperties.fileSystem())
                .extracting("os", "minimumFreeDiskSpace")
                        .contains("windows", 200);

        assertThat(this.profileConfigProperties)
                .extracting("profile").isEqualTo(expectedProfile);

        assertThat(authorName).isEqualTo("yulikexuan");
        assertThat(authorPhone).isEqualTo("514-987-6543");

        then(this.authorConfigProperties).should().name();
        then(this.authorConfigProperties).should().phone();
    }

}////:~