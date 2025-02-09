//: sfg6lab.ai.rag.bootstrap.LoadVectorStore.java

package sfg6lab.ai.rag.bootstrap;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.ai.vectorstore.milvus.MilvusVectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sfg6lab.ai.rag.config.VectorStoreProperties;
import sfg6lab.ai.rag.domain.service.AiDocumentsFactory;


@Slf4j
@Component
@RequiredArgsConstructor
class LoadVectorStore implements CommandLineRunner {

    static final String AI_SIMILARITY_CRITERIA_TEXT = "Sportsman";

    private final VectorStore vectorStore;
    private final VectorStoreProperties vectorStoreProperties;
    private final AiDocumentsFactory aiDocumentsFactory;

    @Override
    public void run(String... args) throws Exception {

        if (!vectorStore.similaritySearch(AI_SIMILARITY_CRITERIA_TEXT).isEmpty()) {
            log.debug(">>> Loading documents into Vector Store");
            vectorStoreProperties.documentsToLoad().forEach(
                    resource -> vectorStore.add(
                            aiDocumentsFactory.createDocuments(resource)));
        }
    }

} /// :~