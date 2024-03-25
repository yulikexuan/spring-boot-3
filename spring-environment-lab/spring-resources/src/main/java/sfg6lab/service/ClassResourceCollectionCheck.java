//: sfg6lab.service.ClassResourceCollectionCheck.java


package sfg6lab.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor(staticName = "of",
        access = AccessLevel.PACKAGE,
        onConstructor = @__(@Autowired))
public class ClassResourceCollectionCheck {

    @Value("classpath:sfg6lab/domain/**/*.class")
    private final Resource[] classResources;

    public void check() {

        if (this.classResources == null) {
            log.debug(">>> There is no any class resource located in {}",
                    "classpath:sfg6lab/domain");
            return;
        }

        for (Resource clazzResource : this.classResources) {

            if (!clazzResource.exists()) {
                continue;
            }

            try (var inputStream = clazzResource.getInputStream()) {

                log.debug(">>> The length of {} is {} Bytes",
                        clazzResource.getFilename(), clazzResource.contentLength());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}///:~