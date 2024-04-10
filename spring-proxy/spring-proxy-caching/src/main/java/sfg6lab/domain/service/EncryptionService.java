//: sfg6lab.domain.service.EncryptionService.java


package sfg6lab.domain.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.NonNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Slf4j
@Service
@CacheConfig(cacheNames= { EncryptionService.ENCRYPTION_CACHE_NAME } )
@RequiredArgsConstructor
public class EncryptionService {

    public static final String ENCRYPTION_CACHE_NAME =
            "sfg6lab.domain.service.encryption";

    private final BCryptPasswordEncoder cryptEncoder;

    @Cacheable(
            key = "#data",
            condition = "#data != null",
            unless = "#result == null || #result.isEmpty()")
    public Optional<String> encrypt(String data) {

        if (data == null) {
            return Optional.empty();
        }

        log.debug(">>> Encrypting data: {}", data);

        return Optional.ofNullable(this.cryptEncoder.encode(data));
    }

    @CachePut(
            key = "#newData",
            condition = "#newData != null",
            unless = "#result == null || #result.isEmpty()")
    public String encryptNew(@NonNull String newData) {
        log.debug(">>> Encrypting new data: {}", newData);
        return this.cryptEncoder.encode(newData);
    }

    @CacheEvict
    public void remove(@NonNull String data) {
        log.debug(">>> Removing encryption of data: {}", data);
    }

    @CacheEvict(allEntries = true)
    public void removeAll() {
        log.debug(">>> Removing all encryption");
    }

}///:~