//: sfg6lab.ai.rag.domain.service.AiDocumentsFactory.java

package sfg6lab.ai.rag.domain.service;


import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.core.io.Resource;

import java.util.List;


public interface AiDocumentsFactory {

    List<Document> createDocuments(Resource docResource);

    static AiDocumentsFactory defaultInstance() {
        return new AiDocumentsFactoryImpl();
    }
}


@Slf4j
final class AiDocumentsFactoryImpl implements AiDocumentsFactory {

    @Override
    public List<Document> createDocuments(@NonNull final Resource docResource) {

        log.debug(">>> Loading documents from resource: {}",
                docResource.getFilename());

        final var documentReader = new TikaDocumentReader(docResource);
        List<Document> documents = documentReader.get();
        TextSplitter textSplitter = new TokenTextSplitter();

        return textSplitter.apply(documents);
    }

}