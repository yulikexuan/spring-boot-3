//: sfg6lab.domain.service.MvnLocalRepositoryService.java


package sfg6lab.domain.service;


import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.nio.file.Path;


public interface MvnLocalRepositoryService {

    Path root();

    long freeDiskSpace();

    static MvnLocalRepositoryService of(@NonNull final Path root) {
        return MvnLocalRepositoryServiceImpl.of(root);
    }
}

@Slf4j
@Service
@RequiredArgsConstructor(staticName = "of", access = AccessLevel.PACKAGE)
class MvnLocalRepositoryServiceImpl implements MvnLocalRepositoryService {

    @NonNull
    private final Path root;

    @Override
    public Path root() {
        return root;
    }

    @Override
    public long freeDiskSpace() {
        return root.toFile().getFreeSpace();
    }

}

///:~