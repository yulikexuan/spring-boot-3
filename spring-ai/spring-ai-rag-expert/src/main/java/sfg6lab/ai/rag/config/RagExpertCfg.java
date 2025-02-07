//: sfg6lab.ai.rag.config.VectorStoreCfg.java

package sfg6lab.ai.rag.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import sfg6lab.ai.rag.domain.service.AiDocumentsFactory;


@Slf4j
@Configuration
@EnableConfigurationProperties( { VectorStoreProperties.class } )
class RagExpertCfg {

    @Bean
    AiDocumentsFactory aiDocumentsFactory() {
        return AiDocumentsFactory.defaultInstance();
    }

} /// :~