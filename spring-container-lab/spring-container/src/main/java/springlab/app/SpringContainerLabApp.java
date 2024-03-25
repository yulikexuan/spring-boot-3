package springlab.app;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import springlab.config.ComponentExcludeFiltersConfig;
import springlab.domain.service.ConfigDemoInfo;
import springlab.service.FileSystemService;


@Slf4j
// @SpringBootApplication
public class SpringContainerLabApp {

    public static void main(String[] args) {

        log.info(">>> Instantiating SpringApplication Class ... ");

        quickRun(args);

        // instantiateSpringApplication(args);
        // instantiateSpringAppWithBuilderToRun(args);
    }

    static void quickRun(final String[] args) {

        final ConfigurableApplicationContext appCtx =
                SpringApplication.run(ComponentExcludeFiltersConfig.class, args);

//        final ConfigurableApplicationContext appCtx =
//                SpringApplication.run(SpringContainerLabApp.class, args);

        var fileSystemService = appCtx.getBean(FileSystemService.class);

        log.debug(">>> The current file system root is {}",
                fileSystemService.rootPath());

        log.debug(">>> The free diskspace is {} GB",
                fileSystemService.getFreeDiskSpaceInGb());

        // ConfigDemoInfo class is excluded by ComponentExcludeFiltersConfig
        // log.debug(">>> See ConfigDemoInfo {}", appCtx.getBean(ConfigDemoInfo.class));
        log.debug(">>> Is ConfigDemoInfo class excluded? {}",
                appCtx.getBeanNamesForType(ConfigDemoInfo.class).length == 0);

//        Arrays.stream(appCtx.getBeanDefinitionNames())
//                .sorted()
//                .forEach(
//                bn -> log.info(">>> Bean : {}", bn));
    }

    static void instantiateSpringApplicationToRun(final String[] args) {

        final var app = new SpringApplication(SpringContainerLabApp.class);

        app.setHeadless(false);
        app.setBannerMode(Banner.Mode.OFF);
        app.setLogStartupInfo(false);

        app.run(args);
    }

    static void instantiateSpringAppWithBuilderToRun(final String[] args) {

        new SpringApplicationBuilder(SpringContainerLabApp.class)
                .headless(false)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .run(args);
    }

}