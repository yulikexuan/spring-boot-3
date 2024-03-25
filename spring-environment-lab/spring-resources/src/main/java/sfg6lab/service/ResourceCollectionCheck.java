//: sfg6lab.service.ResourceCollectionCheck.java


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
public class ResourceCollectionCheck {

    @Value("classpath*:*.json")
    private final Resource[] jsonResources;

    public void check() {

        if (this.jsonResources == null) {
            log.debug(">>> There is no any JSON resource");
            return;
        }

        for (Resource json : this.jsonResources) {

            if (!json.exists()) {
                continue;
            }

            try (var inputStream = json.getInputStream()) {
                log.debug(">>> The length of {} is {} Bytes",
                        json.getFilename(), json.contentLength());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

}///:~