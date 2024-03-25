//: sfg6lab.service.ResourceInitializingService


package sfg6lab.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;


public interface ResourceCheckingService {

    long check();

    default long validateResource(Resource resource) {

        boolean exist = resource.exists();

        if (!exist) {
            return -1;
        }

        try (var inputStream = resource.getInputStream()) {
            return resource.contentLength();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

}

@Service("newingClassPathResource")
class NewingClassPathResource implements ResourceCheckingService {

    static final String RESOURCE_LOCATION = "author.json";

    @Override
    public long check() {
        return this.validateResource(new ClassPathResource(RESOURCE_LOCATION));
    }

}

@Service("usingResourceLoader")
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class UsingResourceLoader implements ResourceCheckingService {

    static final String RESOURCE_CLASS_PATH = "classpath:author.json";

    private final ResourceLoader resourceLoader;

    @Override
    public long check() {
        return this.validateResource(resourceLoader.getResource(RESOURCE_CLASS_PATH));
    }

}


///:~