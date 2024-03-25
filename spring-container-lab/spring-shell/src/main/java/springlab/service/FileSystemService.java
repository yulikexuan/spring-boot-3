//: springlab.domain.model.FileSystem.java


package springlab.service;


import lombok.AccessLevel;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.unit.DataSize;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Slf4j
@Service

@RequiredArgsConstructor(
        staticName = "of",
        access = AccessLevel.PACKAGE,
        onConstructor_ = {@CryptographicallyStrong})
public class FileSystemService {

    static final String ROOT_NAME = "fs";

    private final Path root;

    private final Random random;

    public static Path root(@NonNull final String userHomePropertyName) {

        return Paths.get(System.getProperty(userHomePropertyName))
                .resolve(ROOT_NAME)
                .toAbsolutePath()
                .normalize();
    }

    public long getFreeDiskSpace() {
        return root.toFile().getFreeSpace();
    }

    public long getFreeDiskSpaceInGb() {
        return DataSize.ofBytes(this.getFreeDiskSpace()).toGigabytes();
    }

    public byte[] load(@NonNull final String filename) {
        try {
            Path path = resolve(filename);
            return Files.readAllBytes(path);
        } catch (IOException e) {
            log.error(">>> Failed to load file {} because {}",
                    filename, e.getMessage());
            return new byte[0];
        }
    }

    public void store(@NonNull final String filename, final byte[] bytes) {

        try {
            Files.write(resolve(filename), bytes);
        } catch (IOException e) {
            log.error(">>> Failed to store file {} because {}",
                    filename, e.getMessage());
        }
    }

    public Path rootPath() {
        return root;
    }

    public long randomLong() {
        return this.random.nextLong();
    }

    private Path resolve(String filename) {

        Path path = root.resolve(filename).toAbsolutePath().normalize();
        if (!path.startsWith(root))
            throw new SecurityException("Access to " + path + " denied");

        return path;
    }


    void close() {
        log.debug(">>> Will close the FileSystemService component for you.");
    }

}///:~