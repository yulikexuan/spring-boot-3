//: sfg6lab.domain.model.ResourceUtilsTest.java


package sfg6lab.domain.model;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import sfg6lab.config.Sfg6AppCfg;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DisplayName("Test ResourceUtils Class - ")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class ResourceUtilsTest {

    @ParameterizedTest
    @CsvSource({"classpath:sfg6lab.domain, true",
            "file:sfg6lab/domain, true",
            "sfg6lab/domain/model, false",
            "jar:file:/sfg6lab/domain/model/utils, false",
            "jar:file:/C:/proj/parser/jar/parser.jar!/test.xml, true"})
    void able_To_Validate_Url(String location, boolean isUrl) {

        // Given

        // When
        boolean isActuallyUrl = ResourceUtils.isUrl(location);

        // Then
        assertThat(isActuallyUrl).isEqualTo(isUrl);
    }

    @Test
    void able_To_Fetch_Url_With_Resource_Location() throws Exception {

        // Given
        String location = "classpath:data.json";

        // When
        URL url = ResourceUtils.getURL(location);
        Path path = Path.of(url.toURI());
        Path relative = Path.of("test-classes/data.json");

        // Then
        assertThat(path).endsWith(relative);
    }

    @Test
    void able_To_Get_A_File_With_Resource_Location() throws Exception {

        // Given
        String location = "classpath:sfg6lab/config/Sfg6AppCfg.class";

        String resPath = ClassUtils.convertClassNameToResourcePath(
                Sfg6AppCfg.class.getName());
        log.debug(">>> resPath? {}", resPath);
        String resPath2 = ClassUtils.classPackageAsResourcePath(Sfg6AppCfg.class);
        log.debug(">>> resPath2? {}", resPath2);

        ClassPathResource cps = new ClassPathResource(resPath);
        log.debug(">>> CPS Exists? {}", cps.exists());

        // When
        File cfgFile = ResourceUtils.getFile(location);
        Path cfgPath = cfgFile.toPath();
        log.debug(">>> cfg path: {}", cfgPath.toString());

        // Then
        assertThat(cfgFile).exists();
    }

}///:~