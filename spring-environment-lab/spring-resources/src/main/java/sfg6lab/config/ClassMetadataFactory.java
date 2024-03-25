//: sfg6lab.config.ClassMetadataFactory


package sfg6lab.config;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.type.ClassMetadata;
import org.springframework.core.type.classreading.MetadataReaderFactory;
import org.springframework.lang.NonNull;

import java.io.IOException;


@FunctionalInterface
public interface ClassMetadataFactory {

    ClassMetadata classMetaData(@NonNull Resource resource);

    static ClassMetadataFactory of(MetadataReaderFactory metadataReaderFactory) {
        return ClassMetaDataFactoryImpl.of(metadataReaderFactory);
    }
}


@Slf4j
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class ClassMetaDataFactoryImpl implements ClassMetadataFactory {

    private final MetadataReaderFactory metadataReaderFactory;

    @Override
    public ClassMetadata classMetaData(@NonNull final Resource resource) {

        try {
            var mdReader = metadataReaderFactory.getMetadataReader(resource);
            return mdReader.getClassMetadata();

        } catch (IOException ioe) {
            log.error(">>> Failed to fetch ClassMetadata from Resource {}",
                    resource);
            return null;
        }

    }
}

///:~