//: sfg6lab.domain.service.FileDownloadingService


package sfg6lab.domain.service;


import org.springframework.lang.NonNull;

import java.util.Optional;


public interface FileDownloadingService {

    Optional<byte[]> download(@NonNull String uri);

    static FileDownloadingService of() {
        return new FileDownloadingServiceImpl();
    }
}

final class FileDownloadingServiceImpl implements FileDownloadingService {

    @Override
    public Optional<byte[]> download(@NonNull String uri) {
        return Optional.of(new byte[] {0x01, 0x02, 0x03});
    }

}

///:~