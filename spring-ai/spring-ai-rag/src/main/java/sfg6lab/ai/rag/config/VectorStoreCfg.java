//: sfg6lab.ai.rag.config.VectorStoreCfg.java

package sfg6lab.ai.rag.config;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.File;
import java.util.List;


@Slf4j
@Configuration
@EnableConfigurationProperties({VectorStoreProperties.class})
class VectorStoreCfg {

    @Bean
    public SimpleVectorStore simpleVectorStore(
            EmbeddingModel embeddingModel,
            VectorStoreProperties vectorStoreProperties) {

        SimpleVectorStore store = SimpleVectorStore.builder(embeddingModel).build();
        File vectorStoreFile = new File(vectorStoreProperties.vectorStorePath());

        if (vectorStoreFile.exists()) {
            store.load(vectorStoreFile);
        } else {
            log.debug(">>> Loading documents into vector store ... ");
            vectorStoreProperties.documentsToLoad().stream()
                    .map(VectorStoreCfg::loadDocument)
                    .forEach(store::add);
            store.save(vectorStoreFile);
        }

        return store;
    }

    static List<Document> loadDocument(@NonNull final Resource resource) {

        log.debug(">>> Loading document '{}'", resource.getFilename());

        TikaDocumentReader documentReader = new TikaDocumentReader(resource);
        List<Document> docs = documentReader.get();
        TextSplitter textSplitter = new TokenTextSplitter();

        return textSplitter.apply(docs);
    }

} /// :~