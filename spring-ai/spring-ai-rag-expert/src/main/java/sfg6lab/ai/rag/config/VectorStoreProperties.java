//: sfg6lab.ai.rag.config.VectorStoreProperties.java

package sfg6lab.ai.rag.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.io.Resource;

import java.util.List;


@ConfigurationProperties(prefix = "sfg.aiapp")
public record VectorStoreProperties(
        String vectorStorePath, List<Resource> documentsToLoad) {

} /// :~