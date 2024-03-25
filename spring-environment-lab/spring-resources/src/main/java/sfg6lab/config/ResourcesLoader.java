//: sfg6lab.config.ResourcesLoader


package sfg6lab.config;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.lang.NonNull;

import java.util.Arrays;
import java.util.List;


@FunctionalInterface
public interface ResourcesLoader {

    List<Resource> resources(@NonNull String pathPattern);

    static ResourcesLoader of(ApplicationContext appCtx) {
        return ResourcesLoaderImpl.of(appCtx);
    }
}


@Slf4j
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class ResourcesLoaderImpl implements ResourcesLoader {

    private final ApplicationContext appCtx;

    @Override
    public List<Resource> resources(@NonNull String pathPattern) {
        try {
            return Arrays.stream(appCtx.getResources(pathPattern)).toList();
        } catch (Exception e) {
            log.error(">>> Failed to find out resources with {}", pathPattern);
            return List.of();
        }
    }

}///:~