//: sfg6lab.service.ClassLoadingService.java


package sfg6lab.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.type.ClassMetadata;
import org.springframework.stereotype.Service;
import sfg6lab.config.ClassMetadataFactory;
import sfg6lab.config.ResourcesLoader;
import sfg6lab.config.Sfg6ClassLoader;

import java.net.URL;


@Slf4j
@Service
@RequiredArgsConstructor(staticName = "of",
        access = AccessLevel.PACKAGE,
        onConstructor = @__(@Autowired))
public class ClassLoadingService {

    static final String LOCATION = "sfg6lab/domain/**/*.class";

    private final Sfg6ClassLoader sfg6ClassLoader;
    private final ResourcesLoader resourcesLoader;
    private final ClassMetadataFactory classMetadataFactory;

    public void check() throws Exception {

        log.debug(">>> Loading class at {} ", LOCATION);

        resourcesLoader.resources(LOCATION).stream()
                .map(classMetadataFactory::classMetaData)
                .map(ClassMetadata::getClassName)
                .map(sfg6ClassLoader::classForName)
                .forEach(clazz -> log.debug(">>> Class '{}' has been loaded",
                        clazz.getName()));
    }

    URL classUrl(Resource resource) {
        try {
            return resource.getURL();
        } catch (Exception e) {
            return null;
        }
    }

}///:~